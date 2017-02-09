/**
 * An exception thrown when an invalid lisp style arithmetic expression is encountered
 *
 * @author  Mike Jacobson
 * @version 1.0
 */

public class InvalidExpressionException extends RuntimeException {
    /**
     * Creates an exception with a null cause.
     *  @see RuntimeException#RuntimeException()
     */
    public InvalidExpressionException( ) {
	super( );
    }

    /**
     * Creates an exception with cause message.
     *  @see RuntimeException#RuntimeException(String message)
     */
    public InvalidExpressionException( String message ) {
	super( message );
    }
}
