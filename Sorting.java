/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
public class Sorting {

    private static void bubbleSort (int[] array) {
    }

    private static void selectionSort (int[] array) {
    }

    private static void insertionSort (int[] array) {
    }

    private static void mergeSort (int[] array) {
    }

    private static void quickSort (int[] array) {
    }


 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        In in = new In(args[0]);

        // Storing file input in an array
        int[] a = in.readAllInts();

        //Sorts copy of array a into array b
        int[] b;
        int[] tempArray = a.clone();
        Arrays.sort(tempArray);
        b = tempArray;

        //Reversing array a and putting it into array c
        int[] c = new int[a.length];

        Stack<Integer> iStack = new Stack<>();
        for (int i : b) {

            iStack.push(i);
        }

        int element = 0;
        while (!iStack.isEmpty()) {
            c[element] = iStack.pop();
            element++;
        }

        //Creates array d setting it equal to b and executes the necessary swaps
        int[] d = b.clone();
        Random rand = new Random();
        int swaps = d.length / 10;
        for (int i = 0; i < swaps; i++) {
            int pos1 = rand.nextInt(d.length-1);
            int pos2 = rand.nextInt(d.length-1);
            int tempInt = d[pos1];
            d[pos1] = d[pos2];
            d[pos2] = tempInt;
        }

       /* System.out.println("a: " + Arrays.toString(a));
        System.out.println("b: " + Arrays.toString(b));
        System.out.println("c: " + Arrays.toString(c));
        System.out.println("d: " + Arrays.toString(d));

        */

        //Puts arrays a,b,c,d into an array of arrays
        int[][] arrays = {a, b, c, d};
        String[] fileArray = {"a.txt", "b.txt", "c.txt", "d.txt"};

        char nameOfArray = 'a';
        String nameOfAlgorithm = null;
        String nameOfFile = null;
        for (int i = 0; i < arrays.length; i++) {
            nameOfFile = fileArray[i];
            Stopwatch timer = new Stopwatch();
            switch (args[1]) {
                case "0":
                    Arrays.sort(arrays[i]);
                    nameOfAlgorithm = "Arrays.sort()";
                    break;
                case "1":
                    bubbleSort(arrays[i]);
                    nameOfAlgorithm = "Bubble Sort";
                    break;
                case "2":
                    selectionSort(arrays[i]);
                    nameOfAlgorithm = "Selection Sort";
                    break;
                case "3":
                    insertionSort(arrays[i]);
                    nameOfAlgorithm = "Insertion Sort";
                    break;
                case "4":
                    mergeSort(arrays[i]);
                    nameOfAlgorithm = "Merge Sort";
                    break;
                case "5":
                    quickSort(arrays[i]);
                    nameOfAlgorithm = "Quick Sort";
                    break;
            }

            double time = timer.elapsedTimeMillis();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String netID = "NLEVAS";
            String algorithmUsed = nameOfAlgorithm;
            String arrayUsed = String.valueOf(nameOfArray);
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            nameOfArray ++;
            FileWriter writer = new FileWriter(nameOfFile);
            writer.write(Arrays.toString(arrays[i]));
            writer.close();
        }
    }
} 


