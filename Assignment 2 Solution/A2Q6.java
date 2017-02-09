import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * Test suite for a the maximum subsequence sum alogirithms
 * Requires:  Junit 4.x.
 *
 * @author Mike Jacobson
 */

public class A2Q6 {
    /**
     * Black-box tests for the expression evaluation function in
     * A2Q5.java.  Note that we include two versions of each test: one
     * for array-based stacks and a second for linked list stacks.
     */

    /**
       Test Case 1: boundary case, invalid expression consisting of a single invalid character
    */
    @Test (expected = InvalidExpressionException.class)
    public void testa_array(){
        String e = new String("a");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testa_list(){
        String e = new String("a");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 2: boundary case, invalid expression "+"
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_plus_array(){
        String e = new String("+");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_plus_list(){
        String e = new String("+");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }



    /**
       Test Case 3: boundary case, invalid expression "("
    */
    @Test (expected = InvalidExpressionException.class)
    public void testLeftBracket_array(){
        String e = new String("(");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testLeftBracket_list(){
        String e = new String("(");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 4: boundary case, invalid expression ")"
    */
    @Test (expected = InvalidExpressionException.class)
    public void testRightBracket_array(){
        String e = new String(")");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testRightBracket_list(){
        String e = new String(")");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }



    /**
       Test Case 5: boundary case, invalid expression "()"
    */
    @Test (expected = InvalidExpressionException.class)
    public void testEmptyBrackets_array(){
        String e = new String("()");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testEmptyBrackets_list(){
        String e = new String("()");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 6: boundary case, tests (+)
    */
    @Test
    public void testZero_array(){
        String e = new String("(+)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(0,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testZero_list(){
        String e = new String("(+)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(0,A2Q5.evaluate(S,e),0.001);
    }



    /**
       Test Case 7: boundary case, tests (*)
    */
    @Test
    public void testOne_array(){
        String e = new String("(*)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(1,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testOne_list(){
        String e = new String("(*)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(1,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 8: boundary case, invalid expression "(-)"
    */
    @Test (expected = InvalidExpressionException.class)
    public void testMinus_array(){
        String e = new String("(-)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testMinus_list(){
        String e = new String("(-)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 9: boundary case, invalid expression "(/)"
    */
    @Test (expected = InvalidExpressionException.class)
    public void testDiv_array(){
        String e = new String("(/)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void testDiv_list(){
        String e = new String("(/)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 10: boundary case, tests + with one operand
    */
    @Test
    public void testPlusOneOp_array(){
        String e = new String("(+ 2)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(2,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testPlusOne_list(){
        String e = new String("(+ 2)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(2,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 11: boundary case, tests * with one operand
    */
    @Test
    public void testTimesOneOp_array(){
        String e = new String("(* 3)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(3,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testTimesOne_list(){
        String e = new String("(* 3)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(3,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 12: boundary case, tests - with one operand
    */
    @Test
    public void testMinusOneOp_array(){
        String e = new String("(- 5)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(-5,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testMinusOne_list(){
        String e = new String("(- 5)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(-5,A2Q5.evaluate(S,e),0.001);
    }



    /**
       Test Case 13: boundary case, tests / with one operand
    */
    @Test
    public void testDivOneOp_array(){
        String e = new String("(/ 7)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(0.14285,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testDivOne_list(){
        String e = new String("(/ 7)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(0.14285,A2Q5.evaluate(S,e),0.001);
    }



    /**
       Test Case 14: typical case, + with two operands
    */
    @Test
    public void testAddTwoOp_array(){
        String e = new String("(+ 1 2)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(3,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testAddTwoOp_list(){
        String e = new String("(+ 1 2)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(3,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 15: typical case, - with two operands
    */
    @Test
    public void testMinusTwoOp_array(){
        String e = new String("(- 7 9)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(-2,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testMinusTwoOp_list(){
        String e = new String("(- 7 9)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(-2,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 16: typical case, * with two operands
    */
    @Test
    public void testMultTwoOp_array(){
        String e = new String("(* 3 5)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(15,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testMultTwoOp_list(){
        String e = new String("(* 3 5)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(15,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 17: typical case, / with two operands
    */
    @Test
    public void testDivTwoOp_array(){
        String e = new String("(/ 8 4)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(2,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testDivTwoOp_list(){
        String e = new String("(/ 8 4)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(2,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 18: typical case, + with multiple operands
    */
    @Test
    public void testAddMultOp_array(){
        String e = new String("(+ 1 2 3)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(6,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testAddMultOp_list(){
        String e = new String("(+ 1 2 3)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(6,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 19: typical case, - with multiple operands
    */
    @Test
    public void testMinusMultOp_array(){
        String e = new String("(- 2 3 1)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(-2,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testMinusMultOp_list(){
        String e = new String("(- 2 3 1)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(-2,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 20: typical case, * with multiple operands
    */
    @Test
    public void testMultMultOp_array(){
        String e = new String("(* 2 3 4 4)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(96,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testMultMultOp_list(){
        String e = new String("(* 2 3 4 4)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(96,A2Q5.evaluate(S,e),0.001);
    }


    /**
       Test Case 21: typical case, / with multiple operands
    */
    @Test
    public void testDivMultOp_array(){
        String e = new String("(/ 8 2 2 3 5)");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(0.1333333,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testDivMultOp_list(){
        String e = new String("(/ 8 2 2 3 5)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(0.1333333,A2Q5.evaluate(S,e),0.001);
    }


   /**
       Test Case 22: typical case, example in assignment description
    */
    @Test
    public void testExample_array(){
        String e = new String("(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(16.5,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testExample_list(){
        String e = new String("(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(16.5,A2Q5.evaluate(S,e),0.001);
    }



    /**
       Test Case 23: typical case, approximation of pi
    */
    @Test
    public void testPi_array(){
        String e = new String("(+ 3 (/ (+ 7 (/ (+ 9 6 (/ (+ 1 (/ (+ (* 4 (+ 1 (* 8 9))) (/ (+ 1 1)))))))))))");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(3.141592653,A2Q5.evaluate(S,e),0.000000001);
    }

    @Test
    public void testPi_list(){
        String e = new String("(+ 3 (/ (+ 7 (/ (+ 9 6 (/ (+ 1 (/ (+ (* 4 (+ 1 (* 8 9))) (/ (+ 1 1)))))))))))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(3.141592653,A2Q5.evaluate(S,e),0.000000001);
    }



    /**
       Test Case 24: typical case, approximation of sqrt(2)
    */
    @Test
    public void testSqrt2_array(){
        String e = new String("(+ 1 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1))))))))))");
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        assertEquals(1.414,A2Q5.evaluate(S,e),0.001);
    }

    @Test
    public void testSqrt2_list(){
        String e = new String("(+ 1 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1 (+ 2 (/ 1))))))))))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        assertEquals(1.414,A2Q5.evaluate(S,e),0.001);
    }



    /**
       Test Case 25: typical case, unbalanced parentheses
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_unbalanced_array(){
        String e = new String("(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 ))");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_unbalanced_list(){
        String e = new String("(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 ))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }



    /**
       Test Case 26: another typical case, unbalanced parentheses
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_unbalanced2_array(){
        String e = new String("(+ - 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 ))");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_unbalanced2_list(){
        String e = new String("(+ - 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 ))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }





    /**
     * White-box tests for the expression evaluation function in
     * A2Q5.java.  Note that we include two versions of each test: one
     * for array-based stacks and a second for linked list stacks.
     */


    /**
       Test Case 27: else branch of main if construct in try block (invalid char)
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_char_array(){
        String e = new String("(+ 3 a)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_char_list(){
        String e = new String("(+ 3 a)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 28: else branch of $($ case (operand expected)
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_op_expected_array(){
        String e = new String("(5)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
	public void test_op_expected_list(){
        String e = new String("(5)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 29: empty stack initially in while loop, + case
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_emptyS_plus_array(){
        String e = new String("(+");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_emptyS_plus_list(){
        String e = new String("(+");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 30: invalid char on stack (number format exception), + case
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_char_plus_array(){
        String e = new String("(+ - 3 2)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_char_plus_list(){
        String e = new String("(+ - 3 2)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 31: empty stack in while loop, + case
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_emptyS_plus2_array(){
        String e = new String("(+ 3 4");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_emptyS_plus2_list(){
        String e = new String("(+ 3 4");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


    /**
       Test Case 32: invalid char on stack during while (number format exception), + case
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_while_plus_array(){
        String e = new String("(+ 3 4 - 2 3)");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_invalid_while_plus_list(){
        String e = new String("(+ 3 4 - 2 3)");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }



    /**
       Test Case 33: S not empty at end
    */
    @Test (expected = InvalidExpressionException.class)
    public void test_empty_at_end_array(){
        String e = new String("(5 (+ 1 2))");;
	BoundedStack<String> S = new BoundedArrayStack<String>(e.length());
	System.out.println("\nTesting evaluate with array stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }

    @Test (expected = InvalidExpressionException.class)
    public void test_empty_at_end_list(){
        String e = new String("(5 (+ 1 2))");
	BoundedStack<String> S = new BoundedLinkedStack<String>(e.length());
	System.out.println("\nTesting evaluate with linked stack and e = " + e);
        double val = A2Q5.evaluate(S,e);
    }


}
