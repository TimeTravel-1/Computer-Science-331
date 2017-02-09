/**
 * The BoundedStack interface represents the Stack ADT extended to
 * have a maximum capacity.
 *
 * @author  Mike Jacobson
 * @version 1.0, Feb 5, 2007
 */
public interface BoundedStack<T> extends cpsc331Stack<T> {

    /**
     * Returns the maximum number of elements that the stack can hold.
     *
     * @return capacity of the stack (int value)
     */
    public int capacity();

    /**
     * Returns the number of elements currently on the stack.
     *
     * @return number of elements on the stack (int value)
     */
    public int size();

    /**
     * Determines whether the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull();

    /**
     * Pushes the object x onto the top of the stack.
     *
     * @param x object to be pushed onto the stack.
     * @throws FullStackException if the number of elements on the stack is equal to its capacity
     */
    public void push(T x);
}
