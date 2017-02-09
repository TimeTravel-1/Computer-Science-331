import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test suite for Lisp expression evaluation algorithm
 * Requires:  Junit 4.x.
 *
 * @author David Ng
 */

public class A2Q6 {
    /**
     * Black box and white box tests for Array and Linked List Implementation of Stack
     */

	 /**
  Test Case 1: tests null string
  	*
  	*Test Input: Null String
  	*Expected Output: NullPointerException
  	*Purpose of Test: To test Null String
	  */
	@Test (expected = NullPointerException.class)
	public void testNullString0(){
		String s = null;
		System.out.println("\nTesting StackArray with null string");
		A2Q5 test = new A2Q5();
		String output = A2Q5.evaluateString(s, 0);
	}
	@Test (expected = NullPointerException.class)
	public void testNullString1(){
		String s = null;
		System.out.println("\nTesting StackLinkedList with null string");
		A2Q5 test = new A2Q5();
		String output = A2Q5.evaluateString(s, 1);
	}

	/**
	Test Case 2: tests with empty string
	*Test Input: Empty String
  *Expected Output: StringIndexOutOfBoundsException
  *Purpose of Test: To test Empty String
	*/
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void testEmptyString0(){
		String s = "";
		System.out.println("\nTesting StackArray with empty string");
		A2Q5 test = new A2Q5();
		String output = A2Q5.evaluateString(s, 0);
	}
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void testEmptyString1(){
		String s = "";
		System.out.println("\nTesting StackLinkedList with empty string");
		A2Q5 test = new A2Q5();
		String output = A2Q5.evaluateString(s, 1);
		
	}
    /**
    Test Case 3: tests with invalid character
    *Test Input: String with Invalid Character
  	*Expected Output: "Invalid Expression"
  	*Purpose of Test: To test an expression with an invalid character
 */
    @Test 
    public void testInvalidString0(){
     String s = "(^ 10 2)";
     System.out.println("\nTesting StackArray with invalid string");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("Invalid Expression", output);
 }
    @Test 
    public void testInvalidString1(){
     String s = "(^ 10 2)";
     System.out.println("\nTesting StackLinkedList with invalid string");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("Invalid Expression", output);
 }
    
    /**
    Test Case 4: tests edge cases of addition
    *Test Input: "(+)"
  	*Expected Output: 0.0
  	*Purpose of Test: To test edge case for addition
     */
    @Test 
    public void testAdditionString0(){
     String s = "(+)";
     System.out.println("\nTesting StackArray with string for edge case of addition");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("0.0", output);
    }
    @Test 
    public void testAdditionString1(){
     String s = "(+)";
     System.out.println("\nTesting StackLinkedList with string for edge case of addition");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("0.0", output);
 }
    
    /**
    Test Case 5: tests edge cases of multiplication
    *Test Input: "(*)"
  	*Expected Output: 1.0
  	*Purpose of Test: To test edge case for multiplication
     */
    @Test 
    public void testMultiplicationString0(){
     String s = "(*)";
     System.out.println("\nTesting StackArray with string for edge case of multiplication");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("1.0", output);
    }
    @Test 
    public void testMultiplicationString1(){
     String s = "(*)";
     System.out.println("\nTesting StackLinkedList with string for edge case of multiplication");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("1.0", output);
 }
    
    /**
    Test Case 6: tests edge cases of subtraction
    *Test Input: "(- 4)"
  	*Expected Output: -4.0
  	*Purpose of Test: To test edge case of subtraction
     */
    @Test 
    public void testSubtractionString0(){
     String s = "(- 4)";
     System.out.println("\nTesting StackArray with string for edge case of subtraction");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("-4.0", output);
    }
    @Test 
    public void testSubtractionString1(){
     String s = "(- 4)";
     System.out.println("\nTesting StackLinkedList with string for edge case of subtraction");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("-4.0", output);
 }
    
    /**
    Test Case 7: tests edge cases of division
    *Test Input: "(/ 8)'
  	*Expected Output: 0.125
  	*Purpose of Test: To test edge case of division
     */
    @Test 
    public void testDivisionString0(){
     String s = "(/ 8)";
     System.out.println("\nTesting StackArray with string for edge case of division");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("0.125", output);
    }
    @Test 
    public void testDivisionString1(){
     String s = "(/ 8)";
     System.out.println("\nTesting StackLinkedList with string for edge case of division");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("0.125", output);
 }
    
    /**
    Test Case 8: tests typical input
    *Test Input: Typical Input
  	*Expected Output: 16.5
  	*Purpose of Test: To test for typical input
     */
    @Test 
    public void testTypicalString0(){
     String s = "(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))";
     System.out.println("\nTesting StackArray with string for typical case");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("16.5", output);
    }
    @Test 
    public void testTypicalString1(){
     String s = "(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))";
     System.out.println("\nTesting StackLinkedList with string for typical case");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("16.5", output);
 }
    
    /**
    Test Case 9: tests typical invalid input
    *Test Input: a string containing invalid input
  	*Expected Output: "Invalid Expression"
  	*Purpose of Test: To test invalid input
     */
    @Test 
    public void testTypicalInvalidString0(){
     String s = "(+ 4 5)))) - 9 8))))";
     System.out.println("\nTesting StackArray with string for typical invalid case");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("Invalid Expression", output);
    }
    @Test 
    public void testTypicalInvlaidString1(){
     String s = "(+ 4 5)))) - 9 8))))";
     System.out.println("\nTesting StackLinkedList with string for typical invalid case");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("Invalid Expression", output);
 }
    
    /**
    Test Case 10: tests negative input
    *Test Input: "(+ -4 -5 (* -1 -2))"
  	*Expected Output: -7.0
  	*Purpose of Test: To test for a case in which negative integers are involved
     */
    @Test 
    public void testNegativeString0(){
     String s = "(+ -4 -5 (* -1 -2))";
     System.out.println("\nTesting StackArray with string for negative values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("-7.0", output);
    }
    @Test 
    public void testNegativeString1(){
     String s = "(+ -4 -5 (* -1 -2))";
     System.out.println("\nTesting StackLinkedList with string for negative values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("-7.0", output);
 }
    
    /**
    Test Case 11: tests multi-digit input
    *Test Input: "(+ -34 -55 (* -16 2))"
  	*Expected Output: -121.0
  	*Purpose of Test: To test for a case in which arbitrary integers values are involved
     */
    @Test 
    public void testMultiDigitString0(){
     String s = "(+ -34 -55 (* -16 2))";
     System.out.println("\nTesting StackArray with string for arbitrary integer values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("-121.0", output);
    }
    @Test 
    public void testMultiDigitString1(){
     String s = "(+ -34 -55 (* -16 2))";
     System.out.println("\nTesting StackLinkedList with string for arbitrary integer values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("-121.0", output);
 }
    
    
    /**
    Test Case 12: tests typical input
    *Test Input: "(+ 4)"
  	*Expected Output: 4.0
  	*Purpose of Test: To test for a typical case
     */
    @Test 
    public void testSimpleString0(){
     String s = "(+ 4)";
     System.out.println("\nTesting StackArray with string for simple integer values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 0);
     assertEquals("4.0", output);
    }
    @Test 
    public void testSimpleString1(){
     String s = "(+ 4)";
     System.out.println("\nTesting StackLinkedList with string for simple integer values");
     A2Q5 test = new A2Q5();
     String output = A2Q5.evaluateString(s, 1);
     assertEquals("4.0", output);
 }
    
    
    

   
    /**
     * This test suite provides thorough testing of the algorithm. This is because all the primary edge cases
     * are considered. For instance, the edge cases of all the operators are included and are shown to work. 
     * It also includes both black box and white box tests to account for the wide array of possible inputs
     * that this program may accept. Some typical cases are included, to show that the algorithm works for many
     * common cases. Additionally, tests for negative integers and integers of more than one digit are included. 
     * This is to show that even arbitrary integer values will be processed correctly by the algorithm. 
     */

    
}