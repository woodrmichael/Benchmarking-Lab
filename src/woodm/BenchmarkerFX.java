package woodm;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Map;

public class BenchmarkerFX extends Application {
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
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
