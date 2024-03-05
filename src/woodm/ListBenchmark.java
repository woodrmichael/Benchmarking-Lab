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
import java.util.Objects;

/**
 * A class used to benchmark different kinds of list methods.
 */
public class ListBenchmark {
    /**
     * Runs the benchmarks a specified amount of time for a specific list.
     * @param listType The type of list to benchmark.
     * @param operation The method to be benchmarked on the list.
     * @param size the size of the list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @param numberOfTests the amount of tests.
     * @return an array of longs containing the time, in nanoseconds,
     * required for each benchmark to complete.
     *
     * @throws IllegalArgumentException Thrown if listType, operation, size, multiplier,
     * or numberOfTests aren't valid.
     */
    public static long[] runBenchmarks(String listType, String operation, int size, int multiplier,
                                       int numberOfTests) throws IllegalArgumentException {
        checkInputNumbers(size, multiplier, numberOfTests);
        long[] elapsedTimes = new long[numberOfTests];
        for(int i = 0; i < numberOfTests; i++) {
            Integer[] arr = fillList(size);
            List<Integer> list = generateList(arr, listType);
            elapsedTimes[i] = runOperation(list, operation);
            size *= multiplier;
        }
        return elapsedTimes;
    }

    /**
     * Returns a string that describes the required command line arguments to the user.
     * @return a string with text describing the required command line arguments.
     */
    public static String getHelp() {
        return "\nThis program is able to generate benchmark results that generate graphs that " +
                "show the amount of time it takes for the desired list implementations to " +
                "complete the desired operation as a function of the number of elements" +
                " stored in the list\n" +
                "\n--implementation=[implementation] --operation=[operation] " +
                "--startSize=[startSize] "
                + "--multiplier=[multiplier] --numberOfSamples=[numberOfSamples]\n" +
                "implementation: The type of list to benchmark.\n" +
                "Valid Implementations: 'java.util.ArrayList', 'java.util.LinkedList', " +
                "'datastructures.ArrayList', 'datastructures.LinkedList', " +
                "or 'datastructures.LinkedListTurbo'.\n" +
                "operation: The method to be benchmarked on the list.\n" +
                "Valid Operations: 'addToFront', 'contains', or 'indexedContains'.\n" +
                "startSize: the size of the list.\n" +
                "multiplier: how much the size of the list should multiply by between each test.\n"
                + "numberOfSamples: the amount of tests.\n" +
                "Valid startSize, multiplier, numberOfSamples: x >= 1";
    }

    /**
     * Fills an array of integers with a specified size with
     * a random positive integer at each index.
     * @param size the size of the array.
     * @return an array of integers with random integers from 0 to 2^31 -1.
     */
    private static Integer[] fillList(int size) {
        Random generator = new Random();
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++) {
            arr[i] = generator.nextInt(Integer.MAX_VALUE);
        }
        return arr;
    }

    /**
     * Generates a specific type of list using a given array of random integers.
     * @param arr the array to be converted into a list
     * @param listType the type of list.
     * @return a list of integers with random integers at each index.
     *
     * @throws IllegalArgumentException thrown if size is less than 0 or if listType isn't
     * a valid type. Valid options are 'java.util.ArrayList', 'java.util.LinkedList',
     * 'datastructures.ArrayList', 'datastructures.LinkedList', or 'datastructures.LinkedListTurbo'
     */
    private static List<Integer> generateList(Integer[] arr, String listType) {
        return switch (listType) {
            case "java.util.ArrayList" -> new ArrayList<>(Arrays.stream(arr).toList());
            case "java.util.LinkedList" -> new LinkedList<>(Arrays.stream(arr).toList());
            case "datastructures.ArrayList" -> new datastructures.ArrayList<>(arr);
            case "datastructures.LinkedList" -> new datastructures.LinkedList<>(arr);
            case "datastructures.LinkedListTurbo" -> new datastructures.LinkedListTurbo<>(arr);
            default -> throw new IllegalArgumentException("Invalid listType, please try again.\n");
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
        Integer value = new Random().nextInt(Integer.MAX_VALUE) * -1;
        long startTime;
        long endTime;
        switch (operation) {
            case "addToFront":
                startTime = System.nanoTime();
                list.add(0, value);
                endTime = System.nanoTime();
                break;
            case "contains":
                startTime = System.nanoTime();
                list.contains(value);
                endTime = System.nanoTime();
                break;
            case "indexedContains":
                startTime = System.nanoTime();
                indexedContains(list, value);
                endTime = System.nanoTime();
                break;
            default:
                throw new IllegalArgumentException("Ensure the operation is valid. " +
                    "Valid options are 'addToFront', 'contains', or 'indexedContains'.\n");
        }
        return endTime - startTime;
    }

    /**
     * Iterates through the list using an index (calls get()) to find a match.
     * @param list the list of integers.
     * @param value the value to search for.
     */
    private static void indexedContains(List<Integer> list, Integer value) {
        boolean found = false;
        for(int i = 0; !found && i < list.size(); i++) {
            if(Objects.equals(value, list.get(i))) {
                found = true;
            }
        }
    }

    /**
     * Checks if the input numbers taken from the command line arguments are valid
     * @param size the size of the list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @param numberOfTests the amount of tests.
     *
     * @throws IllegalArgumentException Thrown if either size, multiplier,
     * or numberOfTests isn't positive.
     */
    private static void checkInputNumbers(int size, int multiplier, int numberOfTests)
            throws IllegalArgumentException {
        StringBuilder message = new StringBuilder();
        if(size < 1) {
            message.append("Please ensure size is >= 1\n");
        }
        if(multiplier < 1) {
            message.append("Please ensure multiplier is >= 1\n");
        }
        if(numberOfTests < 1) {
            message.append("Please ensure numberOfTests is >= 1\n");
        }
        if(!message.isEmpty()) {
            throw new IllegalArgumentException(message.toString());
        }
    }
}
