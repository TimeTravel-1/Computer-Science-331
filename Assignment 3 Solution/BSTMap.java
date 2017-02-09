import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

/**
 *  An interative binary search tree implementation of the SimpleSortedMap interface 
 *  using generics.
 *  @author M. Jacobson
 */
public class BSTMap<K extends Comparable<K>,V> implements SimpleSortedMap<K,V> {
    protected BSTNode<K,V> root;
    protected int bstsize;

    public BSTMap() { 
	root = (BSTNode<K,V>) null; 
        bstsize = 0;
    }


    /**
     * Tests whether the map is empty.
     *
     * @return true if the dictionary is empty, false otherwise
     */
    public boolean isEmpty() { 
	return (bstsize == 0); 
    }


    /**
     * Returns the number of key-values pairs in the map.
     *
     * @returns int The size of the map.
     */
    public int size () {
        return bstsize;
    }


    /**
     * Computes and returns the height of the tree.
     *
     * @return int The height of the tree
     */
    public int height() {
	return getHeight(root);
    }

    protected int getHeight(BSTNode<K,V> T) {
	if (T == null)
            return -1;
	else {
            int lh = getHeight(T.left);
            int rh = getHeight(T.right);
            if (lh >= rh)
		return 1 + lh;
            else
		return 1 + rh;
        }
    }



    /**
     * Inserts a key-value pair into the map.
     *
     * @param key The key to be inserted.
     * @param value Key's corresponding value.
     * @throws KeyFoundException If a matching key is already present in the map
     */
    public void insert(K key, V value) throws KeyFoundException {
	BSTNode<K,V> prev = null;
        BSTNode<K,V> curr = root;

 	while (curr != null) {
            if (key.compareTo(curr.key) == 0)
                throw new KeyFoundException();
            else if (key.compareTo(curr.key) < 0) {
                prev = curr;
                curr = curr.left;
            }
            else {
                prev = curr;
                curr = curr.right;
            }
        }

        if (prev == null)
            root = new BSTNode<K,V>(key,value,null,null);
        else if (key.compareTo(prev.key) < 0)
            prev.left = new BSTNode<K,V>(key,value,null,null);
	else
            prev.right = new BSTNode<K,V>(key,value,null,null);

        ++bstsize;
    }



    /**
     * Returns the value corresponding to key.
     *
     * @param key The key to search for in the map.
     * @returns V The value corresponding to key.
     * @throws KeyNotFoundException If key is not found
     * in the map.
     */
    public V search(K key) throws KeyNotFoundException {
        BSTNode<K,V> curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) == 0)
                return curr.value;
            else if (key.compareTo(curr.key) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        }

        throw new KeyNotFoundException();
    }



    /**
     * Deletes the key-value pair with the specified key
     * from the map
     *
     * @param key The key to remove from the map.
     * @throws KeyNotFoundException If key is not found
     * in the map.
     */
    public void delete(K key) throws KeyNotFoundException  {
        BSTNode<K,V> curr = root;
        BSTNode<K,V> prev = null;
        while (curr != null) {
            if (key.compareTo(curr.key) == 0) {
                // found the element to delete

                --bstsize;

                if (curr.right == null && curr.left == null) {
                    // leaf node
                    if (prev == null) {
                        // deleting the root
                        root = null;
                        return;
                    }
                    else if (prev.right == curr) {
                        // deleting right child
                        prev.right = null;
                        return;
                    }
                    else {
                        // deleting left child
                        prev.left = null;
                        return;
                    }
                }
                else if (curr.right == null) {
                    // only has left child
                    if (prev == null) {
                        // deleting the root
                        root = curr.left;
                        return;
                    }
                    else if (prev.right == curr) {
                        // deleting right child
                        prev.right = curr.left;
                        return;
                    }
                    else {
                        // deleting left child
                        prev.left = curr.left;
                        return;
                    }
                }
                else if (curr.left == null) {
                    // only has right child
                    if (prev == null) {
                        // deleting the root
                        root = curr.right;
                        return;
                    }
                    else if (prev.right == curr) {
                        // deleting right child
                        prev.right = curr.right;
                        return;
                    }
                    else {
                        // deleting left child
                        prev.left = curr.right;
                        return;
                    }
                }
                else {
                    // node has two children

                    // find successor (left-most node in right subtree)
                    BSTNode<K,V> succ = curr.right;
                    BSTNode<K,V> prev_succ = curr;
                    while (succ.left != null) {
                        prev_succ = succ;
                        succ = succ.left;
                    }

                    // copy successors data to the current node
                    curr.key = succ.key;
                    curr.value = succ.value;

                    // delete the successor node
                    if (succ.right == null && succ.left == null) {
                        // leaf node
                        if (prev_succ.right == succ) {
                            // deleting right child
                            prev_succ.right = null;
                            return;
                        }
                        else {
                            // deleting left child
                            prev_succ.left = null;
                            return;
                        }
                    }
                    else {
                        // only has right child (min node cannot have a left child)
                        if (prev_succ.right == succ) {
                            // deleting right child
                            prev_succ.right = succ.right;
                            return;
                        }
                        else {
                            // deleting left child
                            prev_succ.left = succ.right;
                            return;
                        }
                    }
                }
            }
            else if (key.compareTo(curr.key) < 0) {
                prev = curr;
                curr = curr.left;
            }
            else if (key.compareTo(curr.key) > 0) {
                prev = curr;
                curr = curr.right;
            }
            else
	        throw new KeyNotFoundException();
        }
    }


    /**
     * Modifies the value corresponding to key in the map.
     *
     * @param key The key whose value to modify.
     * @param value The new value of key.
     * @throws KeyNotFoundException If key was not found in the map
     * and therefore no value was modified.
     */
    public void modify(K key, V value) throws KeyNotFoundException {
        delete(key);
        insert(key,value);
    }



    public Iterator<K> iterator() {
        return new BSTInOrderIterator<K,V>();
    }

    private class BSTInOrderIterator<K extends Comparable<K>,V> implements Iterator<K> {
        private BoundedStack<BSTNode<K,V>> nodeStack;
        private BSTNode<K,V> curr;

        BSTInOrderIterator() {
            nodeStack = new BoundedArrayStack(bstsize);
            curr = (BSTNode<K,V>) root;
        }

        public boolean hasNext() {
            return (curr != null || !nodeStack.isEmpty());
        }

        public K next() {
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.left;
            }

            if (!nodeStack.isEmpty()) {
                BSTNode<K,V> nextNode = (BSTNode<K,V>) nodeStack.pop();
                curr = nextNode.right;
		return nextNode.key;
            }
            else
                throw new NoSuchElementException();
        }



        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



    /*
     * Node class for the binary search tree
     */
    protected class BSTNode<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private BSTNode<K,V> left;
	private BSTNode<K,V> right;

	private BSTNode(K newKey, V newValue, BSTNode<K,V> newLeft, BSTNode<K,V> newRight) {
	    key = newKey;
	    value = newValue;
	    left = newLeft;
	    right = newRight;
	}
    }



    public static void main (String [] args) {
        BSTMap <Integer, String> ll = new BSTMap <Integer, String> ();
        ll.insert (1, "hello");
        ll.insert (2, "test");
        System.out.println(ll.search (1).toString());
        System.out.println(ll.search (2).toString());
        Iterator<Integer> I = ll.iterator();
        while (I.hasNext())
            System.out.println (I.next());
        ll.delete(2);
        System.out.println(ll.search (2).toString());
    }
}
