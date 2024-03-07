# CSC1120 Labs 6 and 7
# Use woodm.jar to run program

## [Link to Lab 6 assignment](https://csse.msoe.us/csc1120/lab6)

* [x] Commit 1 "Rename package"
    - Rename the package to your MSOE woodm
    - Add the `ListBenchmark` class
    - Stub out the two required methods and write JavaDoc comments for each
* [x] Commit 2 "Command Line Input"
  Implement the `main()` method so that it parses the command line input and calls
  the `ListBencmark.runBenchmarks()` method. You do not need to handle exceptions yet.
* [x] Commit 3 "Private methods for ListBenchmark"
    - Identify private methods for the `ListBenchmark` class needed to complete the assignment
      (used as helper methods to complete the implementation of the two required public methods)
    - Stub out the methods and provide JavaDoc for the methods.
* [x] Commit 4 "Benchmark Parameters for java.util benchmarks"
    - Implement the remaining requirements for the lab assignment.
    - Fill in values for the benchmarking parameters below for the first four listed (the `?? ?? ?? [??ns]` on each line)
* [x] Commit 5 "Lab completed"
    - Fill in remaining values for the benchmarking parameters below (the `?? ?? ?? [??ns]` on each line)

### Benchmark Parameters

 * `java.util.ArrayList` indexedContains 10000000 1 1 [21,142,000 ns]
 * `java.util.ArrayList` contains 10000000 1 1 [19,338,400 ns]
 * `java.util.LinkedList` indexedContains 50 8 5 [23,550,797,300 ns]
 * `java.util.LinkedList` contains 10000000 1 1 [32,097,800 ns]
 * `datastructures.ArrayList` indexedContains 10000000 1 1 [20,943,200 ns]
 * `datastructures.ArrayList` contains 10000000 1 1 [31,655,500 ns]
 * `datastructures.LinkedList` indexedContains 50 8 5 [31,695,613,600 ns]
 * `datastructures.LinkedList` contains 10000000 1 1 [36,442,900 ns]
 * `datastructures.LinkedListTurbo` indexedContains 10000000 1 1 [83,244,400 ns]
 * `datastructures.LinkedListTurbo` contains 10000000 1 1 [55,691,600 ns]

## [Link to Lab 7 assignment](https://csse.msoe.us/csc1120/lab7)

* [x] Commit 1 "Create report document"
    - Create Word document for your report. Include a title page.
* [x] Commit 2 "Big-O Analysis"
    - Add your Big-O analysis to the report
* [x] Commit 3 "Convert to JavaFX Program"
    - Implement `BenchmarkerFX` which accepts commandline arguments as a JavaFX program
* [x] Commit 4 "Display Chart in JavaFX"
    - Implement functionality to display a chart with the benchmarking results
* [x] Commit 5 "Save PNG image"
    - Implement functionality to save chart as a PNG image in the `plots` folder
    - Add plots to the report
* [x] Commit 6 "Lab completed"
    - Update your code based on feedback from your instructor (if available)
    - Finish all assignment requirements.

