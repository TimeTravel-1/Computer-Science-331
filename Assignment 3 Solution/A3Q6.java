import static java.lang.Math.log;
import java.util.Random;
import java.util.Iterator;
import static java.lang.Integer.parseInt;
import java.lang.IllegalArgumentException;

/**
 * The A3Q5 class main program tests the operation of three implementations of the
 * SimpleSortedMap interface, facilitating a height comparison.
 * @author  Mike Jacobson
 * @version 1.0
 */
public class A3Q6 {
    static final int MAX_ARRAY_SIZE=100000;

    /**
     * Generates an array containing n random elements between -maxEntry and
     * maxEntry.
     *
     * @return array containing n distinct random integers
     * @param  n desired size of A
     * @throws IllegalArgumentException if n < 0 or n > MAX_ARRAY_SIZE
     */

    public static Integer [] randomArray(int n)
    {
        // Enforce preconditions
	if (n <= 0)
	    throw new IllegalArgumentException("Negative array size: " + n);

	if (n > MAX_ARRAY_SIZE)
	    throw new IllegalArgumentException("Array size = " + n + " bigger than MAX_ARRAY_SIZE = " + MAX_ARRAY_SIZE);

	// create A
	Integer [] A = new Integer[n];


	// fill A with random integers
	Random R = new Random();
	for (int i=0; i<n; ++i)
	    {
		A[i] = R.nextInt();
                // make sure all elements are distinct
                int j=0;
                while (j < i) {
		    if (A[j].equals(A[i])) {
			A[i] = R.nextInt();
			j = 0;
		    }
		    else
			++j;
                }
	    }

	return A;
    }



    /**
     *  Prints a message describing proper usage with respect to required
     *  command line parameters and exits.
     */
    public static void usage()
    {  
	System.out.println("Usage: java A3Q5 arraySize numArrays");
	System.out.println("  arraySize - number of elements per array");
	System.out.println("  numArrays - number of different arrays to test");
	System.exit(1);
    }


    /**
     *  Tests the three maximum subsequence sum functions on a common series
     *  of random arrays.
     *  Requires three command line parameters of type <code>int</code>:
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
	System.out.println(n + " elements in each array\n");


	// create num_arrays random arrays and compute the maximum
        // subsequence sum using three different methods for each

	int minh = n;
	int maxh = 0;
	int sumh = 0;

	for (int i=0; i<num_arrays; ++i)
	    {
		// create an array of random integers between
                // -max_int and max_int
		Integer [] A = null;
		try 
		    {
			A = randomArray(n);
		    }
		catch (IllegalArgumentException e)
		    {
			System.out.println(e);
			usage();
		    }


		// create and test dictionary using BSTrecursive class
		BSTMap<Integer,Integer> T = new BSTMap();
		for (int j=0; j<n; ++j)
		    T.insert(A[j],A[j]);

		int h = T.height();
		if (h < minh)
		    minh = h;
		if (h > maxh)
		    maxh = h;
		sumh += h;
	    }

	System.out.println("min height = " + minh);
	System.out.println("max height = " + maxh);
	System.out.println("avg height = " + sumh/num_arrays);
	System.out.println("exp avg height (binary search tree) = " + 3*log((double) n)/log((double) 2));
	System.out.println("max height (red-black tree) = " + 2*log((double) n+1)/log((double) 2));
    }
}
