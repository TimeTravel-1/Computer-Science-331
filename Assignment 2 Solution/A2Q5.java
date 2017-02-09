import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Character.isDigit;
import static java.lang.String.valueOf;
import java.util.Scanner;
import java.io.File;
import java.util.EmptyStackException;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *
 * A class for evaluating lisp style arithmetic expressions.
 *
 * @author  Mike Jacobson
 * @version 1.0
 */

public class A2Q5 {

    /**
     * Determines whether the first character of the string is a digit
     *
     * @param  c the string
     * @return true if the first char is 0, 1, ..., or 9, false otherwise 
     */
    public static boolean isDigit(String c) {
	return Character.isDigit(c.charAt(0));
    }


    /**
     * Evaluates the value of an arithmetic expression in a lisp style syntax
     *
     * @param expression the expression to be evaluated (as a <code>String</code>
     * @return the value of the evaluated expression
     * @throws InvalidExpressionException if the expression is invalid
     */
    public static double evaluate(BoundedStack<String> S, String expression) {
        // make sure S is empty
        while (!S.isEmpty())
	    S.pop();

	// fully parenthesized notation:  left-most char must be "("
	if (expression.charAt(0) != '(')
	    throw new InvalidExpressionException("first char not (");

	// read each character of the expression, starting at the end
	int i = expression.length()-1;

        while (i >= 0) {
            // represent the ith character as a <code>String</code> so we can store it on our
	    // stack of Strings
	    String c = valueOf(expression.charAt(i));
            --i;

	    // if digit, read entire integer input
            if (isDigit(c)) {
		String cc = new String();
		if (i >= 0)
		    cc = valueOf(expression.charAt(i));
		while (i >= 0 && isDigit(cc)) {
		    c = cc + c;
		    --i;
		    if (i >= 0)
			cc = valueOf(expression.charAt(i));
		}
            }

	    try {
		// push characters onto the stack until we encounter a "("
		if (c.equals(")") || isDigit(c) || (c.equals("+")) || (c.equals("-")) || (c.equals("*")) || (c.equals("/"))) 
		    S.push(c);
		else if (c.equals("(")) {
		    c = (String) S.pop();

		    // if the expression is valid, the character on top of the stack must be
		    // an operator followed by 0 or more operands and a ")".  Evaluate this
		    // subexpression, popping the operator, operands, and ")" from the stack
		    // during the process, and push its value onto the stack when finished.

  		    if (c.equals("+")) {
			double currval = 0;
			c = (String) S.pop();
			while (!c.equals(")")) {
			    currval += parseDouble(c);
			    c = (String) S.pop();
			}
			S.push(valueOf(currval));
		    }
		    else if (c.equals("-")) {
			c = (String) S.pop();
			if (c.equals(")") || c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"))
			    throw new InvalidExpressionException("sub");
			double currval = parseDouble(c);
			c = (String) S.pop();
                        if (c.equals(")"))
			    currval = -currval;
                        else {
			    while (!c.equals(")")) {
				currval -= parseDouble(c);
				c = (String) S.pop();
			    }
			}
			S.push(valueOf(currval));
		    }
		    else if (c.equals("*")) {
			double currval = 1;
			c = (String) S.pop();
			while (!c.equals(")")) {
			    currval *= parseDouble(c);
			    c = (String) S.pop();
			}
			S.push(valueOf(currval));
		    }
		    else if (c.equals("/")) {
			c = (String) S.pop();
			if (c.equals(")") || c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"))
			    throw new InvalidExpressionException("div");
			double currval = parseDouble(c);
			c = (String) S.pop();
			if (c.equals(")"))
			    currval = 1.0/currval;
			else {
			    while (!c.equals(")")) {
				currval /= parseDouble(c);
				c = (String) S.pop();
			    }
			}
			S.push(valueOf(currval));
		    }
		    else
			throw new InvalidExpressionException("expected operand");
		
		}
		else if (!c.equals(" ")) {
		    // only character other than a " " or one of the above is invalid
		    throw new InvalidExpressionException("unexpected character c = " + c);
		}
	    }
	    catch (EmptyStackException e) {
		throw new InvalidExpressionException("empty");
	    }
	    catch (FullStackException e) {
		throw new InvalidExpressionException("full");
	    }
	    catch (NumberFormatException e) {
		throw new InvalidExpressionException("number");
	    }
	    catch (ArithmeticException e) {
		throw new InvalidExpressionException("arithmetic");
	    }
	}


	// if the expression is valid, it's value should be the only entry on the stack
	String val = (String) S.pop();
        if (!S.isEmpty())
	    throw new InvalidExpressionException("not empty (end)");

	double dval;
	try {
	    dval = parseDouble(val);
	}
	catch (NumberFormatException e) {
	    throw new InvalidExpressionException("not number at end");
	}

	return dval;
    }



    /**
     *  Prints a message describing proper usage with respect to required
     *  command line parameters and exits.
     */
    public static void usage() {
	System.out.println("Usage: java A2Q5 type infile");
        System.out.println("  type - 0 for array stack, linked stack otherwise");
	System.out.println("  infile - name of input file containing one Lisp expression per line");
	System.exit(1);
    }


    /**
     *  A command line interface to the expression evaluator
     */
    public static void main(String[] args) {
	// gather command line arguments
	if (args.length != 2)  usage();

        int type = parseInt(args[0]);

	// Use a try-catch block to handle any exceptions thrown by
	// reading from the input file
	try {
	    // Create a Scanner object to read from the file argv[1]
	    //  (in current folder), which will be used to read in lines
	    //  of the input file
	    File inputFile = new File(args[1]);
	    Scanner in = new Scanner(inputFile);

	    // read and process each line of the input file
	    while (in.hasNextLine()) {
		String expression = in.nextLine();

		BoundedStack<String> S;
		if (type == 0)
		    S = new BoundedArrayStack<String>(expression.length());
		else
		    S = new BoundedLinkedStack<String>(expression.length());


		try {
		    double val = evaluate(S,expression);
		    System.out.println(val);
		}
		catch(InvalidExpressionException e) {
		    System.out.println("Invalid Expression");
		}
	    }
	}

	// Print a message to standard output if there was a problem
	// opening or processing the input file.
	catch(FileNotFoundException e) {
	    System.out.println(e);
	    System.exit(0);
	}
    }
}
