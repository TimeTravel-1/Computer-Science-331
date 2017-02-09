import java.util.*;
import java.util.Random;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.Arrays.sort;
import java.lang.IllegalArgumentException;

/**
 * Tests a variety of sorting algorithms on randomly-ordered arrays of a given length.
 *
 * @author  Mike Jacobson
 * @version 1.0
 */
public class A4Q5 {

    /**
     *  Prints a message describing proper usage with respect to required
     *  command line parameters and exits.
     */
    public static void usage()
    {  
	System.out.println("Usage: java A4Q5 arraySize numArrays");
	System.out.println("  arraySize - number of elements per array");
	System.out.println("  numArrays - number of different arrays to test");
	System.exit(1);
    }


    /**
     *  Tests the available sorting functions on a common series of random arrays.
     *  Requires two command line parameters of type <code>int</code>:
     *  <ul>
     *   <li> arraySize (length of arrays to test)
     *   <li> numArrays (number of random arrays to test)
     *  </ul>
     */
    public static void main(String[] args)
    {
	if (args.length != 2)  usage();

	// gather command line arguments
	int n = parseInt(args[0]);
	int num_arrays = parseInt(args[1]);

	System.out.println("Testing " + num_arrays + " arrays");
	System.out.println(n + " elements in each array");


	// create num_arrays random arrays and compute the maximum
        // subsequence sum using three different methods for each

	for (int i=0; i<num_arrays; ++i)
	    {
		// create an array of random integers
		int [] A = null;
		A = Sorting.randomArray(n);


		System.out.println("Sorting array " + i);

		// sort using Java's sort function
		int [] Ajava = new int[n];
		for (int j=0; j<A.length; ++j)
		    Ajava[j] = A[j];
		Sorting.javaSort(Ajava);


		// sort using insertion sort
		int [] Ainsertion = new int[n];
		for (int j=0; j<A.length; ++j)
		    Ainsertion[j] = A[j];
		Sorting.insertionSort(Ainsertion);

		// check whether sorted arrays are equal
                if (!Sorting.testArrays(Ajava,Ainsertion)) {
		    System.out.println("ERROR:  sorted arrays using java's sort function and insertion sort are not the same");
		    System.out.println("Sorted using java's sort function:");
		    Sorting.printArray(Ajava);
		    System.out.println("Sorted using insertion sort:");
		    Sorting.printArray(Ainsertion);
		    System.out.println("\n");
                }


		// sort using merge sort
		int [] Amerge = new int[n];
		for (int j=0; j<A.length; ++j)
		    Amerge[j] = A[j];
		Sorting.mergeSort(Amerge);

		// check whether sorted arrays are equal
                if (!Sorting.testArrays(Ajava,Amerge)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and merge sort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    Sorting.printArray(Ajava);
                    System.out.println("Sorted using merge sort:");
                    Sorting.printArray(Amerge);
                    System.out.println("\n");
                }


		// sort using heap sort
		int [] Aheap = new int[n];
		for (int j=0; j<A.length; ++j)
		    Aheap[j] = A[j];
		Sorting.heapSort(Aheap);

		// check whether sorted arrays are equal
                if (!Sorting.testArrays(Ajava,Aheap)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and heap sort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    Sorting.printArray(Ajava);
                    System.out.println("Sorted using heap sort:");
                    Sorting.printArray(Aheap);
                    System.out.println("\n");
                }



		// sort using deterministic quicksort sort
		int [] Adquick = new int[n];
		for (int j=0; j<A.length; ++j)
		    Adquick[j] = A[j];
		Sorting.quickSort(Adquick);

		// check whether sorted arrays are equal
                if (!Sorting.testArrays(Ajava,Adquick)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and deterministic quicksort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    Sorting.printArray(Ajava);
                    System.out.println("Sorted using deterministic quicksort:");
                    Sorting.printArray(Adquick);
                    System.out.println("\n");
                }



		// sort using quicksort with early abort and median-of-3 pivot selection
		int [] Aqsmed3 = new int[n];
		for (int j=0; j<A.length; ++j)
		    Aqsmed3[j] = A[j];
		Sorting.quicksortImproved(Aqsmed3);

		// check whether sorted arrays are equal
                if (!Sorting.testArrays(Ajava,Aqsmed3)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and improved quicksort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    Sorting.printArray(Ajava);
                    System.out.println("Sorted using improved quicksort:");
                    Sorting.printArray(Aqsmed3);
                    System.out.println("\n");
                }
	    }

    }
}
