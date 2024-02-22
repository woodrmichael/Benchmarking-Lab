/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 6 - Benchmarking
 * Name: Michael Wood
 * Created: 2/21/2024
 */
package woodm;


import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A class used to benchmark different kinds of list methods.
 */
public class ListBenchmark {
    /**
     * Runs the benchmarks a specified amount of time for a specific list.
     * @param listType The type of list to benchmark.
     * @param operation The method to be benchmarked on the list.
     * @param size the size of the list.
     * @param multiplier how much the size should multiply by between each test.
     * @param numberOfTests the amount of tests.
     * @return an array of longs containing the time, in nanoseconds,
     * required for each benchmark to complete.
     *
     * @throws IllegalArgumentException Thrown if listType, operation, size, multiplier,
     * or numberOfTests aren't valid.
     */
    public static long[] runBenchmarks(String listType, String operation, int size,
                                int multiplier, int numberOfTests) throws IllegalArgumentException{
        for(int i = 0; i < numberOfTests; i++) {
            List<Integer> list = generateList(listType, size);
        }

        return null;
    }

    /**
     * Returns a string that describes the required command line arguments to the user.
     * @return a string with text describing the required command line arguments.
     */
    public static String getHelp() {
        return null;
    }

    /**
     * Fills an array of integers with a specified size with a random integer at each index.
     * @param size the size of the array.
     * @return an array of integers with random integers at each index.
     */
    private static Integer[] fillList(int size) {
        Random generator = new Random();
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++) {
            arr[i] = generator.nextInt();
        }
        return arr;
    }

    /**
     * Generates a filled list of random integers of a specified list type and size.
     * @param listType the type of list.
     * @param size the size of the list.
     * @return a list of integers with random integers at each index.
     *
     * @throws IllegalArgumentException thrown if size is less than 0 or if listType isn't
     * a valid type. Valid options are 'java.util.ArrayList', 'java.util.LinkedList',
     * 'datastructures.ArrayList', 'datastructures.LinkedList', or 'datastructures.LinkedListTurbo'
     */
    private static List<Integer> generateList(String listType, int size) {
        Integer[] arr = fillList(size);
        return switch (listType) {
            case "java.util.ArrayList" -> new ArrayList<>(Arrays.stream(arr).toList());
            case "java.util.LinkedList" -> new LinkedList<>(Arrays.stream(arr).toList());
            case "datastructures.ArrayList" -> new datastructures.ArrayList<>(arr);
            case "datastructures.LinkedList" -> new datastructures.LinkedList<>(arr);
            case "datastructures.LinkedListTurbo" -> new datastructures.LinkedListTurbo<>(arr);
            default -> throw new IllegalArgumentException("Invalid listType, please try again");
        };
    }

    /**
     * Runs the specified operation on the list.
     * @param list the list of integers.
     * @param operation the method to be called on the list.
     * @return the amount of time in ns to run the operation.
     *
     * @throws IllegalArgumentException thrown if operation isn't a valid operation.
     * Valid options are 'addToFront', 'contains', or 'indexedContains'.
     */
    private static long runOperation(List<Integer> list, String operation) {
        return -1;
    }


    /**
     * Calculates the elapsed time to execute an operation in ns.
     * @param startTime the time the operation was started.
     * @return the elapsed time it takes to execute an operation.
     */
    private static long getElapsedTime(long startTime) {
        return -1;
    }




}
