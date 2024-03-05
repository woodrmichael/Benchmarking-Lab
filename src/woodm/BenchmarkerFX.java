/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 7 - Benchmarking Continued
 * Name: Michael Wood
 * Created: 2/29/2024
 */
package woodm;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This is the class to run the benchmarks and display plots.
 */
public class BenchmarkerFX extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private static final String OUTPUT_FOLDER = "plots/";
    @Override
    public void start(Stage stage) {
        final String help = ListBenchmark.getHelp();
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setWidth(WIDTH);
        alert.setHeight(HEIGHT);
        try {
            final Map<String, String> params = getParameters().getNamed();
            final int argsCount = 6;
            if(params.size() != argsCount) {
                throw new IllegalArgumentException("Please ensure all arguments are present");
            }
            final String implementation = params.get("implementation");
            final String operation = params.get("operation");
            final int startSize = Integer.parseInt(params.get("startSize"));
            final int multiplier = Integer.parseInt(params.get("multiplier"));
            final int numberOfSamples = Integer.parseInt(params.get("numberOfSamples"));
            final String output = params.get("output");
            if(!output.endsWith(".png")) {
                throw new IllegalArgumentException("Please ensure the output file is a PNG file");
            }
            final LineChart<Number, Number> lineChart = makeLineChart(
                    implementation + " for " + operation);
            final long[] times = ListBenchmark.runBenchmarks(implementation,
                    operation, startSize, multiplier, numberOfSamples);
            final double[][] coordinates = getCoordinatesArray(times, startSize, multiplier);
            lineChart.getData().add(makeLine(coordinates));
            Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.setTitle(implementation + " for " + operation);
            stage.show();
            saveScreenShot(output, scene);
        } catch (IOException e) {
            alert.setTitle("Error Saving Plot");
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (NumberFormatException e) {
            alert.setTitle("Error Parsing Integers");
            alert.setContentText("Please ensure 'startSize', 'multiplier', and " +
                    "'numberOfSamples' are positive integers\n" + help);
            alert.show();
        } catch (IllegalArgumentException e) {
            alert.setTitle("Error, Invalid Arguments");
            alert.setContentText(e.getMessage() + "\n" + help);
            alert.show();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Returns a 2D array containing the coordinates to be plotted on a line chart.
     * @param times An array of longs containing the times it took to benchmark the operation
     *              on the list.
     * @param startSize the original size of the list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @return a 2D array containing the coordinates of list sizes in x and runtimes in y.
     */
    private static double[][] getCoordinatesArray(long[] times, int startSize, int multiplier) {
        final double msConversionFactor = 1_000_000;
        final int x = 0;
        final int y = 1;
        double[][] coordinates = new double[times.length][2];
        int size = startSize;
        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i][x] = size;
            coordinates[i][y] = times[i] / msConversionFactor;
            size *= multiplier;
        }
        return coordinates;
    }

    /**
     * Creates a line chart
     * @param title The title for the chart
     * @return a line chart with title and axes labelled
     */
    private static LineChart<Number, Number> makeLineChart(String title) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("List Size");
        yAxis.setLabel("Time in Milliseconds");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false);
        lineChart.setTitle(title);
        return lineChart;
    }

    /**
     * Creates a series that represents a single line on to be added to a line chart.
     * @param values the coordinates of the line represented by the series
     * @return a series that represents a single line on to be added to a line chart
     */
    private static XYChart.Series<Number, Number> makeLine(double[][] values) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        final int x = 0;
        final int y = 1;
        for (double[] value : values) {
            series.getData().add(new XYChart.Data<>(value[x], value[y]));
        }
        series.setName("Time to Completion");
        return series;
    }

    /**
     * Saves a screenshot of the specified scene
     * @param filename name of the file for the image to be saved
     * @param scene the scene to be captured and saved
     * @throws IOException if an error is encountered while writing the file
     */
    private static void saveScreenShot(String filename, Scene scene) throws IOException {
        WritableImage image = scene.snapshot(null);
        File file = new File(filename == null ? OUTPUT_FOLDER + "plot.png" : filename);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
    }
}
