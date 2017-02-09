import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *  A binary search tree implementation of the SimpleSortedMap interface 
 *  using generics.
 *  @author David Ng
 */
public class BSTMap <K extends Comparable <K>, V> implements SimpleSortedMap <K, V>{

	private int bstsize;
  private Node <K, V> root;
  
  public BSTMap()
  {
  	bstsize = 0;
  	root = null;
  }
	
	public Iterator<K> iterator() {
		return new BSTMapIterator<K,V>();
	}

	/**
   * Tests whether the map is empty.
   * @return true if the dictionary is empty, false otherwise
   */
	public boolean isEmpty() 
	{
		return (bstsize == 0);
	}

	/**
   * Returns the number of key-values pairs in the map.
   * @returns int The size of the map.
   */
	public int size() {
		return bstsize;
	}

	/**
   * Inserts a key-value pair into the map.
   * @param key The key to be inserted.
   * @param value Key's corresponding value.
   * @throws KeyFoundException If a matching key is already present in the map
   */
	public void insert(K key, V value) throws KeyFoundException {
		
		Node <K,V> current = root;
		
		//Empty tree
		if (bstsize == 0)
		{
			root = new Node <K,V> (key, value, null, null, null);
			bstsize ++;
			return;
		}
		
		//Find location to insert
		while(current != null)
		{
			//Cannot insert since key already in tree
			if (current.key.compareTo(key) == 0)
			{
				throw new KeyFoundException();
			}
			//key is less than current, and left child of current is not null
			else if ((current.key.compareTo(key) > 0) && (current.left != null))
			{
				current = current.left;
			}
			//key is greater than current, and right child of current is not null
			else if ((current.key.compareTo(key) < 0) && (current.right != null))
			{
				current = current.right;
			}
			//key is less than current, and left child of current is null
			else if ((current.key.compareTo(key) > 0) && (current.left == null))
			{
				//insert key with value
				Node <K,V> newNode = new Node <K,V> (key, value, null, null, current);
				current.left = newNode;
				break;
			}
			//key is greater than current, and right child of current is null
			else if ((current.key.compareTo(key) < 0) && (current.right == null))
			{
				//insert key with value
				Node <K,V> newNode = new Node <K,V> (key, value, null, null, current);
				current.right = newNode;
				break;
			}
		}
		
		//increment size of tree
		bstsize++;
	
	}

	 /**
   * Deletes the key-value pair with the specified key
   * from the map.
   * @param key The key to remove from the map.
   * @throws KeyNotFoundException If key is not found
   * in the map.
   */
	public void delete(K key) throws KeyNotFoundException 
	{
		Node <K, V> current = root;
    boolean found = false;
    
    //Find where key is located in the tree
    while (current != null)
    {
    		//key found
        if (current.key.compareTo(key) == 0)
        {
            found = true;
            break;
        }
        else if (current.key.compareTo(key) < 0)
        {
            current = current.right;
        }
        else
        {
            current = current.left;
        }
    }
    
    if (found == false)
        throw new KeyNotFoundException ();
        
    //The node containing key has been found and is being pointed to by current
    
    //Case 1 current is a leaf 
    if ((current.left == null) && (current.right == null))
    {
        //current is root
        if (current.parent == null)
        {
            root = null;
        }    
        //current is left child
        else if ((current.parent.left != null) && (current.parent.left.key.compareTo (current.key) == 0))
        {
        	current.parent.left = null;
        }
        //current is right child 
        else
        {
        	current.parent.right = null;
        }
        bstsize--;
    }
    
    //Case 2A current has a left child
    else if ((current.left != null) && (current.right == null))
    {
        //current is root
        if (current.parent == null)
        {
            root = current.left;
            root.parent = null;
        }
            
        //current is a left child 
        else if ((current.parent.left != null) && (current.parent.left.key.compareTo (current.key) == 0))
        {
            current.parent.left = current.left;
            current.left.parent = current.parent;
        }
        
        //current is a right child 
        else
        {
            current.parent.right = current.left;
            current.left.parent = current.parent;
        }
        bstsize--;
    }
    
    //Case 2B current has a right child 
    else if ((current.right != null) && (current.left == null))
    {
        //current is root
        if (current.parent == null)
        {
            root = current.right;
            root.parent = null;
        }
        
        //current is left child
        else if ((current.parent.left != null) && (current.parent.left.key.compareTo (current.key) == 0))
        {
            current.parent.left = current.right;
            current.right.parent = current.parent;
        }
        
        //current is right child
        else
        {
            current.parent.right = current.right;
            current.right.parent = current.parent;
        }
        bstsize--;
    }
    
    //Case 3 current has two children
    else
    {
        //find successor
        Node <K, V> replacement = current.right;
        while (replacement.left!= null)
        {
            replacement = replacement.left;
        }
        
        //replacement is the successor of current
        K tempkey = replacement.key;
        V tempvalue = replacement.value;
        
        //deletion of replacement should be covered by one of the previous cases 
        try
        {
            delete (tempkey);    
        }
        catch (KeyNotFoundException e)
        {
            System.out.println ("ERROR - Something has gone wrong during deletion.");
            System.exit (1);
        }
        current.key =  tempkey;
        current.value = tempvalue;       
    }

	}

	 /**
   * Returns the value corresponding to key.
   * @param key The key to search for in the map.
   * @returns V The value corresponding to key.
   * @throws KeyNotFoundException If key is not found
   * in the map.
   */
	public V search(K key) throws KeyNotFoundException 
	{
		Node <K,V> current = root;
		while (current != null)
		{
			//key is found, return value
			if (current.key.compareTo(key) == 0)
			{
				return current.value;
			}
			//set current to be left subtree
			else if (current.key.compareTo(key) > 0)
			{
				current = current.left;
			}
			//set current to be right subtree
			else
			{
				current = current.right;
			}	
		}
		
		//key is not found
		throw new KeyNotFoundException();
	}

	/**
   * Modifies the value corresponding to key in the map.
   * @param key The key whose value to modify.
   * @param value The new value of key.
   * @throws KeyNotFoundException If key was not found in the map
   * and therefore no value was modified.
   */
	public void modify(K key, V value) throws KeyNotFoundException 
	{
		Node <K,V> current = root;
		while (current != null)
		{
			//key found, value replaced
			if (current.key.compareTo(key) == 0)
			{
				current.value = value;
				return;
			}
			//set current to be left subtree
			else if (current.key.compareTo(key) > 0)
			{
				current = current.left;
			}
			//set current to be right subtree
			else
			{
				current = current.right;
			}
		}
		
		//key not found, could not modify
		throw new KeyNotFoundException();
	
	}
	
	 /**
   * Returns the value corresponding to height of the binary search tree.
   * @returns the height of the binary search tree
   */
	public int findHeight()
	{
		//return height for Question 6
		return height(root);
	}
	
	private int height(Node <K,V> n)
	{
		//if null, decrement height by 1
		if (n == null)
		{
			return -1;
		}
		else
		{
			//return the maximum height of left and right subtree 
			return Math.max(height(n.left), height(n.right)) + 1;
		}
	}
	
private class BSTMapIterator<K extends Comparable<K>, V> implements Iterator<K>
{
	
	private Node<K,V> curr;
	private Stack <Node<K,V>> stk;
		
	public BSTMapIterator()
	{
			curr =  (Node<K, V>) root;
			stk = new Stack <Node<K,V>>();
	}
	
	//if stack is not empty, or current is not null, return true
	public boolean hasNext() 
	{
    return (!stk.isEmpty() || (curr != null));
	}

	//return next key
	public K next() {
		
		Node <K,V> prev = null;
		
		//implemented using pseudocode from inorder traversal
		while ((!stk.isEmpty()) || (curr != null)) 
		{
			if (curr != null)
			{
				//If current is not null, push to stack and set current to left child
				stk.push(curr);
				curr = curr.left;
			}
			else
			{
				//otherwise, pop stack, and set current to right child
				curr = stk.pop();
				prev = curr;
				curr = curr.right;
				break;
			}
		}
		
		if (prev == null)
		{
			//no element
			throw new NoSuchElementException();
		}

		//return next key of inorder traversal
		return prev.key;
	}
		
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}
	
	private class Node <K extends Comparable <K>, V> {
		private K key;
		private V value;
		private Node <K,V> left;
		private Node <K,V> right;
		private Node <K,V> parent;

		
		public Node (K k, V v, Node <K,V> l, Node <K,V> r, Node <K,V> p)
		{
			key = k;
			value = v;
			left = l;
			right = r;
			parent = p;
		}
	}
	
	//used to test BSTMap
	 public static void main (String [] args) {
     BSTMap <Integer, String> ll = new BSTMap <Integer, String> ();
     System.out.println(ll.findHeight());
     ll.insert (1, "hello");
     System.out.println(ll.findHeight());
     ll.insert (0, "test");
     System.out.println(ll.findHeight());
     System.out.println(ll.search (1).toString());
     System.out.println(ll.search (0).toString());
     ll.insert(2,  "SDFSFD");
     Iterator<Integer> I = ll.iterator();
     while (I.hasNext()) 
         System.out.println (I.next());
     System.out.println(ll.findHeight());
 }

}
