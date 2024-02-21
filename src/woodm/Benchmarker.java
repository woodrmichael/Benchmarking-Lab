/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 6 - Benchmarking
 * Name: Michael Wood
 * Created: 2/21/2024
 */
package woodm;


/**
 * This is the class to run the benchmarks.
 */
public class Benchmarker {
    public static void main(String[] args) {
        final int argsCount = 5;
        if(args.length != argsCount) {
            System.out.println(ListBenchmark.getHelp());
        }
        long[] values = ListBenchmark.runBenchmarks(args[0], args[1],
                Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
    }
}
