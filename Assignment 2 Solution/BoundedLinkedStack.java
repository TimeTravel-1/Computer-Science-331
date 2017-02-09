import java.util.EmptyStackException;

/**
 * The BoundedLinkedStack class is a linked list based implementation of the BoundedStack ADT.
 *
 * @author  Mike Jacobson
 * @version 1.0, Oct 6, 2008
 */
public class BoundedLinkedStack<T> implements BoundedStack<T> {
    private StackNode<T> top;
    private int maxSize;
    private int size;

    /**
     * Creates a bounded stack with maximum size capacity
     */
    public BoundedLinkedStack(int capacity) { 
	top = (StackNode<T>) null;
	size = 0;
	maxSize = capacity;
    }

    /**
     * Returns the maximum number of elements that the stack can hold.
     *
     * @return capacity of the stack (int value)
     */
    public int capacity() { 
	return maxSize; 
    }


    /**
     * Returns the number of elements currently on the stack.
     *
     * @return number of elements on the stack (int value)
     */
    public int size() { 
	return size; 
    }


    /**
     * Determines whether the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull() {
	return (size == maxSize);
    }


    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() { 
	return (size == 0); 
    }



    /**
     * Pushes the object x onto the top of the stack.
     *
     * @param x object to be pushed onto the stack.
     * @throws FullStackExcpetion if the stack is full
     */
    public void push(T x) { 
	if (isFull()) throw new FullStackException();
	top = new StackNode<T>(x,top);
	++size;
    }

    /**
     * Returns the object at the top of the stack.
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T top() {
	if (isEmpty()) throw new EmptyStackException();
	return top.data;
    }

    /**
     * Removes the object at the top of the stack.
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
	if (isEmpty()) throw new EmptyStackException();
	T x = top.data;
	top = top.next;
	--size;
	return x;
    }

    /**
     * Private internal class representing a linked node in the stack
     *
     */
    private class StackNode<T> {
	private T data;
	private StackNode<T> next;

	private StackNode(T x, StackNode<T> n) { data = x; next = n; }
    }



    /**
     * A driver for some test cases for this class.
     *
     */
    public static void main(String[] args) {
        BoundedStack<Integer> S = new BoundedLinkedStack<Integer>(4);

        Integer x = new Integer(15);
        System.out.println("S.push(15)");
        S.push(x);

        x = new Integer(10);
        System.out.println("S.push(10)");
        S.push(x);

        System.out.println("S.isFull() = " + S.isFull());

        x = new Integer(5);
        System.out.println("S.push(5)");
        S.push(x);

        x = (Integer) S.top();
        System.out.println("S.top() = " + x);

        System.out.println("S.size() = " + S.size());
        System.out.println("S.capacity() = " + S.capacity());
        System.out.println("S.isFull() = " + S.isFull());

        System.out.println("S.pop");
        S.pop();
        System.out.println("S.size() = " + S.size());
        System.out.println("S.isFull() = " + S.isFull());

        x = new Integer(3);
        System.out.println("S.push(3)");
        S.push(x);

        x = new Integer(4);
        System.out.println("S.push(4)");
        S.push(x);
        System.out.println("S.size() = " + S.size());
        System.out.println("S.isFull() = " + S.isFull());

        x = (Integer) S.top();
        System.out.println("S.top() = " + x);

        System.out.println("S.pop");
        S.pop();

        System.out.println("S.pop");
        S.pop();

        System.out.println("S.pop");
        S.pop();

        System.out.println("S.pop");
        S.pop();

        System.out.println("S.isEmpty() = " + S.isEmpty());
        System.out.println("S.size() = " + S.size());
    }
}
