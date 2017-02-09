/**
 * An exception used by bounded stacks to indicate an error when trying to push an element
 * onto a full stack.
 *
 * @author  Mike Jacobson
 * @version 1.0
 *
 */

public class FullStackException extends RuntimeException {
    /**
     * Creates an exception with a null cause.
     * @see RuntimeException#RuntimeException()
     */
    public FullStackException( ) {
	super( );
    }

    /**
     * Creates an exception with cause message.
     * @see RuntimeException#RuntimeException(String message)
     */
    public FullStackException( String message ) {
	super( message );
    }
}
