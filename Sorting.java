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

    public static void defaultSort (int[] A) {
        Arrays.sort(A);
    }

    //Adapted from 2/28 in class slideshow
    public static void bubbleSort (int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = A.length - 1; j > i; j--) {
                if ((A[j] < (A[j - 1]))) {
                    swap(A, j, j-1);
                }
            }
        }
    }

    //Adapted from 2/28 in class slideshow
    public static void selectionSort (int[] A) {
        for (int i=0; i<A.length-1; i++) { // Get i-th item
            int lowindex = i; // Remember it
            for (int j=A.length-1; j>i; j--) // Find min
                if (A[j] < (A[lowindex]))
                    lowindex = j; // Put it in placeDSutil.swap(A, i, lowindex);
        }
    }

    //Adapted from 2/28 in class slideshow
    public static void insertionSort (int[] A) {
        for (int i=1; i<A.length; i++) // Insert i’th item
            for (int j=i; (j>0) && (A[j] < (A[j-1])); j--)
                swap(A, j, j-1);
    }

    public static void mergeSort(int[] A) {
        int[] temp = new int[A.length];
        mergeSortHelp(A,temp, 0, A.length - 1);

    }



    public static <E> void quickSort (int[] A) {
        quickSortHelp(A, 0, A.length - 1);
    }

    //Adapted from 3/2 attached code
    private static void mergeSortHelp (int[] A, int[] temp, int l, int r) {
        int mid = (l+r)/2;                // Select midpoint
        if (l == r) return;               // List has one element
        mergeSortHelp(A, temp, l, mid);   // Mergesort first half
        mergeSortHelp(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++)          // Copy subarray to temp
            temp[i] = A[i];
        // Do the merge operation back to A
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1)              // Left sublist exhausted
                A[curr] = temp[i2++];
            else if (i2 > r)              // Right sublist exhausted
                A[curr] = temp[i1++];
            else if (temp[i1] < (temp[i2])) // Get smaller
                A[curr] = temp[i1++];
            else A[curr] = temp[i2++];
        }
    }

    private static void quickSortHelp (int[] A, int i, int j) {
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        swap(A, pivotindex, j);       // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i-1, j, A[j]);
        swap(A, k, j);              // Put pivot in place
        if ((k-i) > 1) quickSortHelp(A, i, k-1);   // Sort left partition
        if ((j-k) > 1) quickSortHelp(A, k+1, j);   // Sort right partition
    }

    static int partition(int[] A, int l, int r, int pivot) {
        do {                 // Move bounds inward until they meet
            while (A[++l] < (pivot));
            while ((r!=0) && (A[--r] > pivot));
            swap(A, l, r);       // Swap out-of-place values
        } while (l < r);              // Stop when they cross
        swap(A, l, r);         // Reverse last, wasted swap
        return l;      // Return first position in right partition
    }

    static int findpivot(int[] A, int i, int j)
    { return (i+j)/2; }

    public static <E> void swap(int[] A, int p1, int p2) {
        int temp = A[p1];
        A[p1] = A[p2];
        A[p2] = temp;
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
                    defaultSort(arrays[i]);
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


