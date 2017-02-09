import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * Test suite for a the maximum subsequence sum alogirithms
 * Requires:  Junit 4.x.
 *
 * @author Mike Jacobson
 */

public class MSSTester {
    /**
     * Black-box tests for maxSubSum1, maxSubSum2, and maxSubSum3
     */

    /**
       Test Case 1: tests null array precondition
    */
    @Test (expected = IllegalArgumentException.class)
    public void testNullArray1(){
        int [] A = null;
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
        MaxSubsequenceSum.maxSubSum1(A);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullArray2(){
        int [] A = null;
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
        MaxSubsequenceSum.maxSubSum2(A);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullArray3(){
        int [] A = null;
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
        MaxSubsequenceSum.maxSubSum3(A);
    }


    /**
       Test Case 2: tests array with no elements
    */
    @Test
    public void testZeroElementArray1(){
	int [] A = {};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testZeroElementArray2(){
	int [] A = {};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testZeroElementArray3(){
	int [] A = {};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 3: tests array with one positive element
    */
    @Test
    public void testOneElementArray1(){
	int [] A = {4};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(4,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testOneElementArray2(){
	int [] A = {4};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(4,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testOneElementArray3(){
	int [] A = {4};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(4,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 4: tests array with one negative element
    */
    @Test
    public void testOneNegElementArray1(){
	int [] A = {-5};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testOneNegElementArray2(){
	int [] A = {-5};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testOneNegElementArray3(){
	int [] A = {-5};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 5: tests array with multiple elements but only
       one postive element
    */
    @Test
    public void testOnePosArray1(){
	int [] A = {-11,-5,2,-8};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(2,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testOnePosArray2(){
	int [] A = {-11,-5,2,-8};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(2,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testOnePosArray3(){
	int [] A = {-11,-5,2,-8};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(2,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 6: tests array with max being sum of all elements
    */
    @Test
    public void testMaxSum1(){
	int [] A = {3,10,3};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(16,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testMaxSum2(){
	int [] A = {3,10,3};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(16,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testMaxSum3(){
	int [] A = {3,10,3};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(16,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 7: tests array with all negative elements
    */
    @Test
    public void testAllNegElementArray1(){
	int [] A = {-2,-5,-1};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testAllNegElementArray2(){
	int [] A = {-2,-5,-1};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testAllNegElementArray3(){
	int [] A = {-2,-5,-1};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(0,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 8: typical case
    */
    @Test
    public void testTypical1(){
	int [] A = {25,-5,-12,-9,14,12,-13,5,8,-2,18,-8};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(42,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testTypical2(){
	int [] A = {25,-5,-12,-9,14,12,-13,5,8,-2,18,-8};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(42,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testTypical3(){
	int [] A = {25,-5,-12,-9,14,12,-13,5,8,-2,18,-8};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(42,MaxSubsequenceSum.maxSubSum3(A));
    }


    /**
       Test Case 9: tests array with max being last element
    */
    @Test
    public void testLastMax1(){
	int [] A = {-3,-10,-3,15};
	System.out.println("\nTesting maxSubSum1 with A = " + Arrays.toString(A));
	assertEquals(15,MaxSubsequenceSum.maxSubSum1(A));
    }

    @Test
    public void testLastMax2(){
	int [] A = {-3,-10,-3,15};
	System.out.println("\nTesting maxSubSum2 with A = " + Arrays.toString(A));
	assertEquals(15,MaxSubsequenceSum.maxSubSum2(A));
    }

    @Test
    public void testLastMax3(){
	int [] A = {-3,-10,-3,15};
	System.out.println("\nTesting maxSubSum3 with A = " + Arrays.toString(A));
	assertEquals(15,MaxSubsequenceSum.maxSubSum3(A));
    }



    /**
     * White-box tests for maxSubSUm3:  none required, as black-box
     * tests ensure complete code coverage.
     */
}
