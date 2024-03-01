/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 7 - Benchmarking Continued
 * Name: Michael Wood
 * Created: 2/29/2024
 */
package woodm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Map;

/**
 * This is the class to run the benchmarks and display plots.
 */
public class BenchmarkerFX extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    @Override
    public void start(Stage stage) throws NumberFormatException {
        final Map<String, String> params = getParameters().getNamed();
        final int argsCount = 6;
        if(params.size() != argsCount) {
            throw new IllegalArgumentException();
        }
        final String implementation = params.get("implementation");
        final String operation = params.get("operation");
        final int startSize = Integer.parseInt(params.get("startSize"));
        final int multiplier = Integer.parseInt(params.get("multiplier"));
        final int numberOfSamples = Integer.parseInt(params.get("numberOfSamples"));
        final String output = params.get("output");
        final LineChart<Number, Number> lineChart = makeLineChart(
                implementation + " for " + operation);
        double[][] coordinates = getCoordinatesArray(
                implementation, operation, startSize, multiplier, numberOfSamples);
        lineChart.getData().add(makeLine(coordinates));

        Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle(implementation + " for " + operation);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Returns a 2D array containing the coordinates to be plotted on a line chart.
     * @param implementation The type of list to benchmark.
     * @param operation The method to be benchmarked on the list.
     * @param startSize the size of the list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @param numberOfSamples the amount of tests.
     * @return a 2D array containing the coordinates of list sizes in x and runtimes in y.
     */
    private static double[][] getCoordinatesArray(String implementation, String operation,
                                                  int startSize, int multiplier,
                                                  int numberOfSamples) {
        final double msConversionFactor = 1_000_000;
        long[] times = ListBenchmark.runBenchmarks(implementation, operation, startSize,
                multiplier, numberOfSamples);
        double[][] coordinates = new double[times.length][2];
        int size = startSize;
        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i][0] = size;
            coordinates[i][1] = times[i] / msConversionFactor;
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
}
