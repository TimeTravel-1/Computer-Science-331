import java.util.Random;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.Arrays.sort;
import java.lang.IllegalArgumentException;

/**
 * The Class contains implementations of insertion sort, merge sort, and four versions of
 * quicksort (including Java's sort function)

 * @author  M. Jacobson
 * @version 1.0
 */
public class Sorting {

    /**
     * Generates an array containing n random integers
     *
     * @return array containing n random int values
     * @param  n desired size of A
     * @throws IllegalArgumentException if n < 0
     */

    public static int [] randomArray(int n)
    {
        // Enforce preconditions
	if (n <= 0)
	    throw new IllegalArgumentException("Negative array size: " + n);

	// create A
	int [] A = new int[n];


	// fill A with random integers
	Random R = new Random();
	for (int i=0; i<n; ++i)
	    {
		A[i] = R.nextInt();
	    }

	return A;
    }



    /**
     * Test two int arrays for equality.
     * @return true if A and B are equal
     * @param  A array of Integers (not null)
     * @param  B array of Integers (not null)
     * @throws IllegalArgumentException if A or B is null      */
    public static boolean testArrays(int [] A, int [] B) {
        if (A == null)
            throw new IllegalArgumentException("Input array A must not be null");
        if (B == null)
            throw new IllegalArgumentException("Input array B must not be null");

        if (A.length != B.length)
             return false;

        for (int i=0; i<A.length; ++i)
            if (A[i] != B[i])
                return false;

        return true;
    }



    /**
     * Prints the contents of the input array.
     *
     * @param  A array of Integers (not null)
     * @throws IllegalArgumentException if A is null
     */
    public static void printArray(int [] A) {
        if (A == null)
            throw new IllegalArgumentException("Input array A must not be null");

        System.out.print("[ ");
        for (int j=0; j<A.length-1; ++j)
            System.out.print(A[j] + ", ");
        System.out.println(A[A.length-1] + " ]");
    }




    /**
     * Sorts the input array using Java's implementation of quicksort
     *
     * @param A integer array
     */

    public static void javaSort(int [] A)
    {
	sort(A);
    }



    /**
     * Sorts the input array using the insertion sort algorithm
     *
     * @param A integer array
     */

    public static void insertionSort(int [] A)
    {
	for (int i=1; i<A.length; ++i) {
	    int j = i;
	    int tmp = A[i];
	    while (j > 0 && tmp < A[j-1]) {
		A[j] = A[j-1];
		--j;
	    }
	    A[j] = tmp;
	}
    }



    /**
     * Sorts the input array using merge sort
     *
     * @param A integer array
     */
    public static void  mergeSort(int [] A)
    {
	int [] B = new int[A.length];
	msort(A,0,A.length-1,B);
    }


    /*
     * Recursive worker function for merge sort
     */
    private static void msort(int [] A, int l, int r, int [] B) {
	if (l < r) {
	    int mid = (r+l) / 2;
	    msort(A,l,mid,B);
	    msort(A,mid+1,r,B);

	    // merge into array B, copy back to A
	    int i1 = l;
	    int i2 = mid+1;
	    int j = 0;
	    while (i1 <= mid && i2 <= r) {
		if (A[i1] <= A[i2]) {
		    B[j] = A[i1];
		    ++i1;
		}
		else {
		    B[j] = A[i2];
		    ++i2;
		}
		++j;
	    }

	    while (i1 <= mid) {
		B[j] = A[i1];
		++i1;
		++j;
	    }

	    while (i2 <= r) {
		B[j] = A[i2];
		++i2;
		++j;
	    }

	    i1 = l;
	    for (j=0; j<r-l+1; ++j) {
		A[i1] = B[j];
		++i1;
	    }
	}
    }




    /**
     * Sorts the input array using heap sort
     *
     * @param A integer array
     */
    public static void heapSort(int [] A)
    {
	int n = A.length;
	if (n > 1) {
  	  // BuildMaxHeap - convert A to a representation of a MaxHeap
	  for (int i=(n >> 1) -1; i>=0; --i) {
	    // MaxHeapify(A,i) to re-establish max heap subheap A[i]
	    int j = i;
	    boolean swapped = true;
            while (swapped) {
	      int l = (j << 1) + 1;
              int r = (j << 1) + 2;
	      int largest = j;
	      swapped = false;

              if (l < n && A[l] > A[j])
                largest = l;
	      if (r < n && A[r] > A[largest])
                largest = r;

	      if (largest != j) {
		int temp = A[j];
		A[j] = A[largest];
		A[largest] = temp;
                swapped = true;
              }

	      j = largest;
            }
          }

	  for (int i=n-1; i>0; --i) {
	    // DeleteMax - remove largest element of heap, copy to location i
	    int temp = A[0];
	    A[0] = A[i];
            A[i] = temp;

	    // MaxHeapify(A,0) to re-establish max heap of size i-1
	    int j = 0;
	    boolean swapped = true;
            while (swapped) {
	      int l = (j << 1) + 1;
              int r = (j << 1) + 2;
	      int largest = j;
	      swapped = false;

              if (l < i && A[l] > A[j])
                largest = l;
	      if (r < i && A[r] > A[largest])
                largest = r;

	      if (largest != j) {
		temp = A[j];
		A[j] = A[largest];
		A[largest] = temp;
                swapped = true;
              }

	      j = largest;
            }
	  }
	}
    }



    /**
     * Sorts the input array using quicksort with deterministic partitioning (using the last
     * element as the pivot)
     *
     * @param A integer array
     */

    public static void quickSort(int [] A)
    {
	qsort(A,0,A.length-1);
    }


    /**
     * recursive worker function for quicksort
     */
    private static void qsort(int [] A, int p, int r) {
	if (p < r) {
	    // deterministic partitioning
	    int x = A[r];
	    int i=p-1;
	    for (int j=p; j<r; ++j) {
		if (A[j] <= x) {
		    ++i;
		    int tmp = A[i];
		    A[i] = A[j];
		    A[j] = tmp;
		}
	    }
	    int tmp = A[i+1];
	    A[i+1] = A[r];
	    A[r] = tmp;

	    qsort(A,p,i);
	    qsort(A,i+2,r);
	}
    }




    /**
     * Sorts the input array using quicksort, the early abort strategy, and a "median-of-3"
     * pivot selection strategy.  The pivot is defined to be the median of the first entry,
     * the last, and the element in the middle of the array.  Once the pivot entry is chosen,
     * these three elements are arranged so that the smallest is at the beginning of the array,
     * the largest is at the end, and the pivot is second last, allowing partition to start at
     * index 1 (instead of 0) and finish one position earlier.
     *
     * @param A integer array
     */

    public static void quicksortImproved(int [] A)
    {
        // partial recursive quicksort with early abort
	qsortImproved(A,0,A.length-1);

        // insertion sort on nearly-sorted array A
	for (int i=1; i<A.length; ++i) {
	    int j = i;
	    int tmp = A[i];
	    while (j > 0 && tmp < A[j-1]) {
		A[j] = A[j-1];
		--j;
	    }
	    A[j] = tmp;
	}
    }


    private static void medianof3(int [] A, int i1, int i2, int i3) {
	if (A[i1] > A[i2]) {
	    int tmp = A[i1];
	    A[i1] = A[i2];
	    A[i2] = tmp;
	}

	if (A[i2] > A[i3]) {
	    int tmp = A[i2];
	    A[i2] = A[i3];
	    A[i3] = tmp;
	}

	if (A[i1] > A[i2]) {
	    int tmp = A[i1];
	    A[i1] = A[i2];
	    A[i2] = tmp;
	}
    }


    private static void qsortImproved(int [] A, int p, int r) {
	if (r-p+1 >= 128) {
	    // deterministic partitioning --- median of 3 strategy
	    int mid = (r + p) / 2;

	    if (A[p] > A[mid]) {
		int tmp = A[p];
		A[p] = A[mid];
		A[mid] = tmp;
	    }

	    if (A[mid] > A[r]) {
		int tmp = A[mid];
		A[mid] = A[r];
		A[r] = tmp;
	    }

	    if (A[p] > A[mid]) {
		int tmp = A[p];
		A[p] = A[mid];
		A[mid] = tmp;
	    }

	    int tmp = A[mid];
	    A[mid] = A[r-1];
	    A[r-1] = tmp;

	    int x = A[r-1];
	    int i=p;
	    for (int j=p+1; j<r-1; ++j) {
		if (A[j] <= x) {
		    ++i;
		    tmp = A[i];
		    A[i] = A[j];
		    A[j] = tmp;
		}
	    }
	    tmp = A[i+1];
	    A[i+1] = A[r-1];
	    A[r-1] = tmp;

	    qsortImproved(A,p,i);
	    qsortImproved(A,i+2,r);
	}
    }




    /**
     *  Prints a message describing proper usage with respect to required
     *  command line parameters and exits.
     */
    public static void usage()
    {  
	System.out.println("Usage: java Sorting arraySize numArrays");
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
		A = randomArray(n);


		System.out.println("Sorting array " + i);

		// sort using Java's sort function
		int [] Ajava = new int[n];
		for (int j=0; j<A.length; ++j)
		    Ajava[j] = A[j];
		javaSort(Ajava);


		// sort using insertion sort
		int [] Ainsertion = new int[n];
		for (int j=0; j<A.length; ++j)
		    Ainsertion[j] = A[j];
		insertionSort(Ainsertion);

		// check whether sorted arrays are equal
                if (!testArrays(Ajava,Ainsertion)) {
		    System.out.println("ERROR:  sorted arrays using java's sort function and insertion sort are not the same");
		    System.out.println("Sorted using java's sort function:");
		    printArray(Ajava);
		    System.out.println("Sorted using insertion sort:");
		    printArray(Ainsertion);
		    System.out.println("\n");
                }



		// sort using merge sort
		int [] Amerge = new int[n];
		for (int j=0; j<A.length; ++j)
		    Amerge[j] = A[j];
		mergeSort(Amerge);

		// check whether sorted arrays are equal
                if (!testArrays(Ajava,Amerge)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and merge sort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    printArray(Ajava);
                    System.out.println("Sorted using merge sort:");
                    printArray(Amerge);
                    System.out.println("\n");
                }



		// sort using heap sort
		int [] Aheap = new int[n];
		for (int j=0; j<A.length; ++j)
		    Aheap[j] = A[j];
		heapSort(Aheap);

		// check whether sorted arrays are equal
                if (!testArrays(Ajava,Aheap)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and heap sort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    printArray(Ajava);
                    System.out.println("Sorted using heap sort:");
                    printArray(Aheap);
                    System.out.println("\n");
                }



		// sort using deterministic quicksort sort
		int [] Adquick = new int[n];
		for (int j=0; j<A.length; ++j)
		    Adquick[j] = A[j];
		quickSort(Adquick);

		// check whether sorted arrays are equal
                if (!testArrays(Ajava,Adquick)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and deterministic quicksort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    printArray(Ajava);
                    System.out.println("Sorted using deterministic quicksort:");
                    printArray(Adquick);
                    System.out.println("\n");
                }



		// sort using customized sorting algorithm
		int [] Amysort = new int[n];
		for (int j=0; j<A.length; ++j)
		    Amysort[j] = A[j];
		quicksortImproved(Amysort);

		// check whether sorted arrays are equal
                if (!testArrays(Ajava,Amysort)) {
                    System.out.println("ERROR:  sorted arrays using java's sort function and improved quicksort are not the same");
                    System.out.println("Sorted using java's sort function:");
                    printArray(Ajava);
                    System.out.println("Sorted using improved quicksort:");
                    printArray(Amysort);
                    System.out.println("\n");
                }

	    }
    }
}
