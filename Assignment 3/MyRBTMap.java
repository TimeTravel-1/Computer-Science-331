import java.util.*;

/**
 *  A red black tree implementation of the SimpleSortedMap interface 
 *  using generics.
 *  Adapted from http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html by Robert Sedgewick and Kevin Wayne.
 *  @author David Ng
 */
public class MyRBTMap<K extends Comparable<K>, V> implements SimpleSortedMap <K, V>{

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    public MyRBTMap() {
    }

    // is node red, false if x is null
    private boolean isRed(Node x) 
    {
        if (x == null)
        {
        	return false;
        }
        return x.color == RED;
    }

    // number of node in subtree rooted 
    private int size(Node x)
    {
        if (x == null) 
        {
        	return 0;
        }
        return x.N;
    } 

    /**
     * Returns the number of key-values pairs in the map.
     * @returns int The size of the map.
     */
    public int size() 
    {
        return size(root);
    }

    /**
     * Tests whether the map is empty.
     *
     * @return true if the dictionary is empty, false otherwise
     */
    public boolean isEmpty() 
    {
        return root == null;
    }

    /**
     * Returns the value corresponding to key.
     * @param key The key to search for in the map.
     * @returns V The value corresponding to key.
     * @throws KeyNotFoundException If key is not found
     * in the map.
     */
    public V search(K key) throws KeyNotFoundException{
        try
        {
        	return get(root, key);
        }
        catch (KeyNotFoundException e)
        {
        	throw new KeyNotFoundException();
        }
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private V get(Node x, K key) {
        while (x != null) 
        {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) 
            {
            	x = x.left;
            }
            else if (cmp > 0) 
            {
            	x = x.right;
            }
            else              
            {
            	return x.val;
            }
        }
        throw new KeyNotFoundException();
    }

    //Check whether tree contains key
    public boolean contains(K key) 
    {
        return search(key) != null;
    }

    /**
     * Inserts a key-value pair into the map.
     * @param key The key to be inserted.
     * @param value Key's corresponding value.
     * @throws KeyFoundException If a matching key is
     * already present in the map
     */
    public void insert(K key, V val) throws KeyFoundException
    {
        if (val == null) 
        {
            delete(key);
            return;
        }

        root = insert(root, key, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the key-value pair in the subtree at root h
    private Node insert(Node h, K key, V val) 
    { 
        if (h == null) 
        {
        	return new Node(key, val, RED, 1);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) 
        {
        	h.left  = insert(h.left,  key, val); 
        }
        else if (cmp > 0) 
        {
        	h.right = insert(h.right, key, val); 
        }
        else             
        {
        	throw new KeyFoundException();
        }

        // fix-up right-leaning links
        if (isRed(h.right) && !isRed(h.left))      
        {
        	h = rotateLeft(h);
        }
        if (isRed(h.left)  &&  isRed(h.left.left)) 
        {
        	h = rotateRight(h);
        }
        if (isRed(h.left)  &&  isRed(h.right))     
        {
        	flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

 
    /**
     * Modifies the value corresponding to key in the map.
     * @param key The key whose value to modify.
     * @param value The new value of key.
     * @throws KeyNotFoundException If key was not found in the map
     * and therefore no value was modified.
     */
  public void modify(K key, V value) throws KeyNotFoundException{

    try
    {
    	root = modify(root, key, value);
    }
    catch (KeyNotFoundException e)
    {
    	throw new KeyNotFoundException();
    }
    
}

// insert the key-value pair in the subtree rooted at h
private Node modify(Node h, K key, V value) throws KeyNotFoundException{ 
    if (h == null) 
    {
    	throw new KeyNotFoundException();
    }

    int cmp = key.compareTo(h.key);
    if (cmp < 0) 
    {
    	h.left  = modify(h.left,  key, value); 
    }
    else if (cmp > 0) 
    {
    	h.right = modify(h.right, key, value); 
    }
    else						
    {
    	h.val = value;
    }
    
    return h;
}
  
    /**
     * Remove the smallest key and value 
     */
    public void deleteMin() {

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
        {
        	root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) 
        {
        	root.color = BLACK;
        }
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
        {
        	return null;
        }

        if (!isRed(h.left) && !isRed(h.left.left))
        {
        	h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * Deletes the key-value pair with the specified key
     * from the map
     * @param key The key to remove from the map.
     * @throws KeyNotFoundException If key is not found
     * in the map.
     */
    public void delete(K key) throws KeyNotFoundException{ 
        if (!contains(key)) 
        {
        	throw new KeyNotFoundException();
        }

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
        {
        	root.color = RED;
        }

        root = delete(root, key);
        if (!isEmpty()) 
        {
        	root.color = BLACK;
        }
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, K key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  
        {
            if (!isRed(h.left) && !isRed(h.left.left))
            {
            	h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
            {
            	h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && (h.right == null))
            {
            	return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left))
            {
            	h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0) 
            {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    // rotate right
    private Node rotateRight(Node h) 
    {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // rotate left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and children
    private void flipColors(Node h) 
    {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assume h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) 
    {
     
        flipColors(h);
        if (isRed(h.right.left)) 
        { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) 
    {
       	flipColors(h);
        if (isRed(h.left.left)) 
        { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) 
    {
        // assert (h != null);

        if (isRed(h.right))
        {
        	h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left))
        {
        	h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right))     
        {
        	flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public K min() {
        if (isEmpty()) 
        {
        	throw new NoSuchElementException();
        }
        return min(root).key;
    } 

    // the smallest key in subtree rooted at x, null if no key
    private Node min(Node x) { 
        
        if (x.left == null) 
        {
        	return x; 
        }
        else                
        {
        	return min(x.left); 
        }
    } 




    public Iterator<K> iterator() {
      return new MyRBTMapIterator<K,V>();
  }

    //essentially the same as BSTMapIterator
    private class MyRBTMapIterator<K extends Comparable<K>, V> implements Iterator<K>
    {
    	
    	private Node curr;
    	private Stack <Node> stk;
    		
    	public MyRBTMapIterator()
    	{
    			curr = root;
    			stk = new Stack <Node>();
    	}
    	
    	//checks whether there is a next
    	public boolean hasNext() 
    	{
        return (!stk.isEmpty() || (curr != null));
    	}

    	//returns the next key (inorder traversal)
    	@SuppressWarnings("unchecked")
		public K next() {
    		
    		Node prev = null;
    		
    		while ((!stk.isEmpty()) || (curr != null)) 
    		{
    			if (curr != null)
    			{
    				stk.push(curr);
    				curr = curr.left;
    			}
    			else
    			{
    				curr = stk.pop();
    				prev = curr;
    				curr = curr.right;
    				break;
    			}
    		}
    		
    		if (prev == null)
    		{
    			throw new NoSuchElementException();
    		}

    		return (K) prev.key;
    	}
    		
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    }
    
    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private K key;           	// key
        private V val;        		 // value
        private Node left, right;  // left and right subtrees
        private boolean color;     // color of parent 
        private int N;             // subtree number

        public Node(K k, V v, boolean c, int num) {
            key = k;
            val = v;
            color = c;
            N = num;
        }
    }
  
}

