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
        String help = ListBenchmark.getHelp();
        final int argsCount = 5;
        if(args.length != argsCount) {
            System.out.println(help);
        } else {
            try {
                String listType = args[0];
                String operation = args[1];
                int size = Integer.parseInt(args[2]);
                int multiplier = Integer.parseInt(args[3]);
                int numberOfTests = Integer.parseInt(args[4]);
                long[] values = ListBenchmark.runBenchmarks(listType, operation, size,
                        multiplier, numberOfTests);
                for(int i = 0; i < values.length; i++) {
                    System.out.format("[%,d ns]  ", values[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println(help);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(help);
            }
        }
    }
}
