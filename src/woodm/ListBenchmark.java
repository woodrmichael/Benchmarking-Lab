/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 6 - Benchmarking
 * Name: Michael Wood
 * Created: 2/21/2024
 */
package woodm;

/**
 * A class used to benchmark different kinds of list methods.
 */
public class ListBenchmark {
    /**
     * Runs the benchmarks a specified amount of time for a specific list.
     * @param listType The type of list to benchmark
     * @param operation The method to be benchmarked on the list
     * @param size the size of the list
     * @param multiplier how much the size should multiply by between each test
     * @param numberOfTests the amount of tests
     * @return an array of longs containing the time, in nanoseconds,
     * required for each benchmark to complete
     */
    public long[] runBenchmarks(String listType, String operation, int size,
                                int multiplier, int numberOfTests) {
        return null;
    }

    /**
     * Returns a string that describes the required command line arguments to the user
     * @return a string with text describing the required command line arguments
     */
    public String getHelp() {
        return null;
    }
}
