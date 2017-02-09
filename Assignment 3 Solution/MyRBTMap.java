import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  A (mostly) iterative red-black tree implementation of the SimpleSortedMap interface 
 *  using generics.
 *  @author M. Jacobson
 */
public class MyRBTMap<K extends Comparable<K>,V> implements SimpleSortedMap<K,V> {
    protected RBTNode<K,V> root;
    protected RBTNode<K,V> nil;
    protected int rbtsize;

    public MyRBTMap() { 
        nil = new RBTNode<K,V>();
        root = nil; 
    }

    
    /**
     * Creates a new RBTMap with a root whose key,value,height, left child and 
     * right child is given.
     *
     * @return void
     * @param key: The key of the root.
     * @param value: Root's corresponding value.
     * @param height: Height of the root.
     * @param L: Left child of the root
     * @param R: Right child of the root
     * */
    public MyRBTMap(K key, V value, int height, MyRBTMap<K,V> L, MyRBTMap<K,V> R) { 
        nil = new RBTNode<K,V>();
        root = new RBTNode<K,V>(key,value,false,L.root,R.root,nil);
        L.root.parent = root;
        R.root.parent = root;
        root.height = height;
    }



    /**
     * Tests whether the map is empty.
     *
     * @return true if the dictionary is empty, false otherwise
     */
    public boolean isEmpty() { 
    	return (rbtsize == 0); 
    }


    /**
     * Returns the number of key-values pairs in the map.
     *
     * @returns int The size of the map.
     */
    public int size () {
        return rbtsize;
    }


    /*
     * Returns maximum height of the given trees
     * @return int  maximum of height(left) and height(right)
     * @param left: first tree
     * @param right: second tree 
     * */
    private int maxSubTreeHeight(RBTNode<K,V> left, RBTNode<K,V> right) {
    	if (!left.isNil()) {
	    if (!right.isNil()) {
		if (left.height > right.height)
		    return left.height;
		else
		    return right.height;
	    }
	    else
		return left.height;
    	}
    	else {
	    if (!right.isNil())
		return right.height;
	    else
		return 0;
    	}
    }


    /*
     * Finds the node with minimum key value in a tree
     * @param T: root of the tree whose minimum value is searched for
     * @return  BSTNode<K,V>: the node with minimum key value in the tree
     * */
    protected RBTNode<K,V> findMin(RBTNode<K,V> T)  {
        if (T.isNil())
            return T;
        else if ((T.left).isNil())
            return T;
        else
            return findMin(T.left);
    }


    /*
     * Finds the node with maximum key value in a tree
     * @param T: root of the tree whose maximum value is searched for
     * @return  BSTNode<K,V>: the node with maximum key value in the tree
     * */
    protected RBTNode<K,V> findMax(RBTNode<K,V> T)  {
        if (T.isNil())
            return T;
        else if ((T.right).isNil())
            return T;
        else
            return findMax(T.right);
    }


    /*
     * Returns black height of a given tree.  If the black height is
     * not well-defined, -1 is returned.
     * @return int
     * @param T: root of the tree
     * */
    private int getBlackHeight(RBTNode<K,V> T) {
    	if (T.isNil())
	    return 0;
    	else {
	    int bL = getBlackHeight(T.left);
	    int bR = getBlackHeight(T.right);
	    if (bL < 0 || bR < 0)
		return -1;

	    if (!(T.left).color)
		++bL;

	    if (!(T.right).color)
		++bR;

	    if (bL != bR)
		return -1;

	    return bL;
    	}
    }

    /**
     * Returns true if the tree is a valid enhanced RBT, false otherwise
     *
     * @return boolean
     */
    public boolean validate() {
	// make sure that the root is black
    	if (root.color) {
	    System.out.println("V: red root!");
	    return false;
    	}

    	int bh = getBlackHeight(root);
    	if (bh < 0)
	    System.out.println("V:  bh = " + bh);
    	return validate(root) && (bh >= 0);
    }


    /*
     * Returns true if the tree is a valid enhanced RBT
     * @return boolean
     * @param: root of the tested tree
     * */
    protected boolean validate(RBTNode<K,V> T) {
    	if (T.isNil())
	    // empty tree is a valid enhanced RBT
	    return true;
    	else {
	    // test whether left subtree is a valid enhanced RBT
	    if (!validate(T.left))
		return false;
	    // test whether right subtree is a valid enhanced RBT
	    else if (!validate(T.right))
		return false;
	    else {
		// we now know that both left and right subtrees are valid
		// check height property of root
		int testHeight = 1 + maxSubTreeHeight(T.left,T.right);  
		if (testHeight != T.height) {
		    System.out.println("V: bad height!  key = " + T.key + ", h = " + T.height + ", testheight = " + testHeight);
		    return false;
		}

		// check BST property of root
		if (!(T.left).isNil()) {
                    if ((findMax(T.left).key).compareTo(T.key) >= 0) {
			System.out.println("V: BST left!  key = " + T.key + ", left.key = " + (T.left).key);
			return false;
		    }
		}

		if (!(T.right).isNil()) {
                    if ((findMin(T.right).key).compareTo(T.key) <= 0) {
			System.out.println("V: BST left!  key = " + T.key + ", right.key = " + (T.right).key);
			return false;
		    }
		}

		// check red-black tree property 4
		if (T.color)
		    if ((T.left).color || (T.right).color) {
			System.out.println("V: bad prop 4");
			return false;
		    }

		// T is an enhanced binary search tree
		return true;
	    }
    	}
    }


    /**
     * Computes and returns the height of the tree.
     *
     * @return int The height of the tree
     */
    public int height() {
	if (rbtsize == 0)
	    return 0;
	else
	    return root.height;
    }

    /**
     * Returns height of the node with key value key
     *
     * @param key: key of the node whose height is being calculated 
     * @return int The height of the subtree whose root has key <code>key</code>
     */
    public int getHeight(K key) throws KeyNotFoundException {
    	return getHeight(root,key);
    }



    /*
     * Returns height of the node with key value key in the tree whose root is T     * @return int
     * @param key: key of the node whose height is being calculated
     * @param T: root node of the tree in which the node with key is located 
     * @calls getHeight(RBTNode<K,V> T, K key)
     * */
    protected int getHeight(RBTNode<K,V> T, K key) throws KeyNotFoundException {
	if (T.isNil())
	    throw new KeyNotFoundException();
	else if (key.compareTo(T.key) == 0)
	    return T.height;
	else if (key.compareTo(T.key) < 0)
	    return getHeight(T.left, key);
	else
	    return getHeight(T.right, key);
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
	RBTNode<K,V> curr = root;
	while (!curr.isNil()) {
	    if (key.compareTo(curr.key) == 0)
	    	return curr.value;
	    else if (key.compareTo(curr.key) < 0)
	    	curr = curr.left;
	    else
	    	curr = curr.right;
	}

	// key is not in the tree
	throw new KeyNotFoundException();
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
	RBTNode<K,V> curr = root;
	while (!curr.isNil()) {
	    if (key.compareTo(curr.key) == 0) {
		curr.value = value;
		return;
	    }
	    else if (key.compareTo(curr.key) < 0)
		curr = curr.left;
	    else
		curr = curr.right;
	}

	// key is not in the tree
	throw new KeyNotFoundException();
    }
    



    /*
     * Adjusts the height of an enhanced RBT
     * @return void
     * @param: root of the tree
     * */
    private void adjustHeight(RBTNode<K,V> T) {
	while (!T.isNil()) {
	    T.height =  1 + maxSubTreeHeight(T.left,T.right);
	    T = T.parent;
	}
    }


    /*
     * Rotates a given tree left
     * @return void
     * @param: root of the tree
     * @calls maxSubTreeHeight(RBTNode<K,V> l,RBTNode<K,V> r);
     * */
    private void rotateLeft(RBTNode<K,V> T) {
	RBTNode<K,V> R = T.right;
	RBTNode<K,V> P = T.parent;
	RBTNode<K,V> RL = R.left;

	if (!P.isNil()) {
	    if (P.left == T)
		P.left = R;
	    else
		P.right = R;
	}

	T.parent = R;
	T.right = RL;

	R.parent = P;
	R.left = T;
	if (P.isNil())
	    root = R;

	if (!RL.isNil())
	    RL.parent = T;

	T.height = 1 + maxSubTreeHeight(T.left,T.right);
	R.height = 1 + maxSubTreeHeight(R.left,R.right);
    }

    /*
     * Rotates a given tree right
     * @return void
     * @param: root of the tree
     * @calls maxSubTreeHeight(RBTNode<K,V> l,RBTNode<K,V> r)
     * */
    private void rotateRight(RBTNode<K,V> T) {
	RBTNode<K,V> L = T.left;
	RBTNode<K,V> P = T.parent;
	RBTNode<K,V> LR = L.right;

	if (!P.isNil()) {
	    if (P.left == T)
		P.left = L;
	    else
		P.right = L;
	}

	T.parent = L;
	T.left = LR;

	L.parent = P;
	L.right = T;
	if (P.isNil())
	    root = L;

	if (!LR.isNil())
	    LR.parent = T;

	T.height = 1 + maxSubTreeHeight(T.left,T.right);
	L.height = 1 + maxSubTreeHeight(L.left,L.right);
    }


    /* Adjusts the tree after insertion. Makes necessary rotations and color changes
     * @return void
     * @param: root of the tree
     * @calls rotateRight(RBTNode<K,V> T)
     * @calls rotateLeft(RBTNode<K,V> T)
     * @calls adjustHeight(RBTNode<K,V> T) 
     * */
    private void adjustInsert(RBTNode<K,V> curr) {
	RBTNode<K,V> currParent = curr.parent;
	while (currParent.color) {
	    RBTNode<K,V> grandparent = currParent.parent;
	    RBTNode<K,V> sibling;

	    if (grandparent.left == currParent) {
		// handle cases 1-3
	        sibling = grandparent.right;

		if (sibling.color) {
		    // Case 1a and b
		    currParent.setBlack();
		    sibling.setBlack();
		    grandparent.setRed();
		    curr = grandparent;
		    currParent = curr.parent;
		}
		else {
		    if (currParent.right == curr) {
			// Case 2
			rotateLeft(currParent);

			rotateRight(grandparent);
			grandparent.setRed();
			curr.setBlack();

			curr = currParent;
			currParent = curr.parent;
		    }
		    else {
			// Case 3
			rotateRight(grandparent);
			grandparent.setRed();
			currParent.setBlack();
		    }
		}
	    }
	    else {
		// handle cases 4-6
		sibling = grandparent.left;

		if (sibling.color) {
		    // Case 4a and 4b
		    currParent.setBlack();
		    sibling.setBlack();
		    grandparent.setRed();
		    curr = grandparent;
		    currParent = curr.parent;
		}
		else {
		    if (currParent.left == curr) {
			// Case 5
			rotateRight(currParent);

			rotateLeft(grandparent);
			grandparent.setRed();
			curr.setBlack();

			curr = currParent;
			currParent = curr.parent;
		    }
		    else {
			// Case 6
			rotateLeft(grandparent);
			grandparent.setRed();
			currParent.setBlack();
		    }
		}
	    }
	}

	if ((curr.parent).isNil())
	    curr.setBlack();

        // fix height further up the tree
        adjustHeight(curr.parent);
    }


    /**
     * Inserts a key-value pair into the map.
     *
     * @param key The key to be inserted.
     * @param value Key's corresponding value.
     * @throws KeyFoundException If a matching key is already present in the map
     */
    public void insert(K key, V value) throws KeyFoundException {
        // perform insertion
	boolean done = false;
	RBTNode<K,V> curr = root;

	if (root.isNil()) {
	    root = new RBTNode<K,V>(key,value,false,nil,nil,nil);
	    root.height = 1;
	    curr = root;
	    ++rbtsize;
	    return;
	}

	while (!done) {
	    if (key.compareTo(curr.key) == 0)
		throw new KeyFoundException();
	    else if (key.compareTo(curr.key) < 0) {
		if (curr.left.isNil()) {
		    curr.left = new RBTNode<K,V>(key,value,true,nil,nil,curr);
		    curr = curr.left;
		    done = true;
		}
		else
		    curr = curr.left;
	    }
	    else {
		if (curr.right.isNil()) {
		    curr.right = new RBTNode<K,V>(key,value,true,nil,nil,curr);
		    curr = curr.right;
		    done = true;
		}
		else
		    curr = curr.right;
	    }
	}


	// adjust to ensure height and red-black properties hold
	adjustHeight(curr.parent);
	adjustInsert(curr);
	++rbtsize;

        assert validate();
    }



    /* Adjusts the tree after deletion. Makes necessary rotations and color changes
     * @return void
     * @param: root of the tree
     * @calls rotateRight(RBTNode<K,V> T)
     * @calls rotateLeft(RBTNode<K,V> T)
     * @calls adjustHeight(RBTNode<K,V> T) 
     * */
    private void adjustDelete(RBTNode<K,V> beta, RBTNode<K,V> alpha) {
	while (!beta.isNil() && !alpha.color) {
	    if (beta.left == alpha) {
		// left child:  cases 3a - 3e
		RBTNode<K,V> delta = beta.right;
		RBTNode<K,V> gamma = delta.left;
		RBTNode<K,V> zeta = delta.right;
 
		if (!beta.color && !gamma.color && !delta.color && !zeta.color) {
		    // case 3a
		    delta.setRed();
		    alpha = beta;
		    beta = alpha.parent;
		}
		else if (beta.color && !gamma.color && !delta.color && !zeta.color) {
		    // case 3b
		    delta.setRed();
		    alpha = beta;
		    beta = alpha.parent;
		}
		else if (!beta.color && !gamma.color && delta.color && !zeta.color) {
		    // case 3c
		    beta.setRed();
		    delta.setBlack();
		    rotateLeft(beta);
		}
		else if (gamma.color && !delta.color && !zeta.color) {
		    // case 3d
		    gamma.setBlack();
		    delta.setRed();
		    rotateRight(delta);
		}
		else if (!delta.color && zeta.color) {
		    // case 3e
		    zeta.setBlack();
		    delta.color = beta.color;
		    beta.setBlack();
		    rotateLeft(beta);
		    alpha = root;
		    beta = nil;
		}
	    }
	    else {
		// right child:  cases 3f - 3j
		RBTNode<K,V> delta = beta.left;
		RBTNode<K,V> gamma = delta.right;
		RBTNode<K,V> zeta = delta.left;

		if (!beta.color && !gamma.color && !delta.color && !zeta.color) {
		    // case 3f
		    delta.setRed();
		    alpha = beta;
		    beta = alpha.parent;
		}
		else if (beta.color && !gamma.color && !delta.color && !zeta.color) {
		    // case 3g
		    delta.setRed();
		    alpha = beta;
		    beta = alpha.parent;
		}
		else if (!beta.color && !gamma.color && delta.color && !zeta.color) {
		    // case 3h
		    beta.setRed();
		    delta.setBlack();
		    rotateRight(beta);
		}
		else if (gamma.color && !delta.color && !zeta.color) {
		    // case 3i
		    gamma.setBlack();
		    delta.setRed();
		    rotateLeft(delta);
		}
		else if (!delta.color && zeta.color) {
		    // case 3e
		    zeta.setBlack();
		    delta.color = beta.color;
		    beta.setBlack();
		    rotateRight(beta);
		    alpha = root;
		    beta = nil;
		}
	    }
	}

	alpha.setBlack();

        // fix height further up the tree
        adjustHeight(beta);
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
	RBTNode<K,V> curr = root;
	RBTNode<K,V> prev = curr.parent;
	boolean adjust = false;

	while (!curr.isNil()) {
	    if (key.compareTo(curr.key) == 0) {
		// found the element to delete

		if (curr.right.isNil() && curr.left.isNil()) {
		    // check whether height adjustments will be necessary, i.e., if deleted
		    // node is black
		    if (!curr.color)
			adjust = true;

		    // leaf node
		    if (prev.isNil()) {
			// deleting the root
			root = nil;
			curr = root;
		    }
		    else if (prev.right == curr) {
			// deleting right child
			prev.right = nil;
			curr = nil;
		    }
		    else {
			// deleting left child
			prev.left = nil;
			curr = nil;
		    }
		}
		else if (curr.right.isNil()) {
		    // only has left child

		    // check whether height adjustments will be necessary, i.e., if deleted
		    // node is black
		    if (!curr.color)
			adjust = true;

		    if (prev.isNil()) {
			// deleting the root
			root = curr.left;
			curr = root;
		    }
		    else if (prev.right == curr) {
			// deleting right child
			prev.right = curr.left;
			curr = curr.left;
		    }
		    else {
			// deleting left child
			prev.left = curr.left;
			curr = curr.left;
		    }
		}
		else if (curr.left.isNil()) {
		    // only has right child

		    // check whether height adjustments will be necessary, i.e., if deleted
		    // node is black
		    if (!curr.color)
			adjust = true;

		    if (prev.isNil()) {
			// deleting the root
			root = curr.right;
			curr = root;
		    }
		    else if (prev.right == curr) {
			// deleting right child
			prev.right = curr.right;
			curr = curr.right;
		    }
		    else {
			// deleting left child
			prev.left = curr.right;
			curr = curr.right;
		    }
		}
		else {
		    // node has two children

		    // find successor (left-most node in right subtree)
		    RBTNode<K,V> succ = curr.right;
		    RBTNode<K,V> prev_succ = curr;
		    while (!(succ.left).isNil()) {
			prev_succ = succ;
			succ = succ.left;
		    }

		    // copy successors data to the current node
		    curr.key = succ.key;
		    curr.value = succ.value;

		    // check whether height adjustments will be necessary, i.e., if deleted
		    // node is black
		    if (!succ.color)
			adjust = true;

		    // delete the successor node
		    if (succ.right.isNil() && succ.left.isNil()) {
			// leaf node
			if (prev_succ.right == succ) {
			    // deleting right child
			    prev_succ.right = nil;
			    prev = prev_succ;
			    curr = nil;
			}
			else {
			    // deleting left child
			    prev_succ.left = nil;
			    prev = prev_succ;
			    curr = nil;
			}
		    }
		    else {
			// only has right child (min node cannot have a left child)
			if (prev_succ.right == succ) {
			    // deleting right child
			    prev_succ.right = succ.right;
			    prev = prev_succ;
			    curr = succ.right;
			}
			else {
			    // deleting left child
			    prev_succ.left = succ.right;
			    prev = prev_succ;
			    curr = succ.right;
			}
		    }
		}


		// make height and red-black tree adjustments
		adjustHeight(prev);
		if (adjust)
		    adjustDelete(prev,curr);
		--rbtsize;

		assert validate();
		return;
	    }
	    else if (key.compareTo(curr.key) < 0) {
		prev = curr;
		curr = curr.left;
	    }
	    else {
		prev = curr;
		curr = curr.right;
	    }
	}

	// key is not in the tree
	throw new KeyNotFoundException();
    }



    public Iterator<K> iterator() {
        return new MyRBTInOrderIterator<K,V>();
    }

    private class MyRBTInOrderIterator<K extends Comparable<K>,V> implements Iterator<K> {
        private BoundedStack<RBTNode<K,V>> nodeStack;
        private RBTNode<K,V> curr;

        MyRBTInOrderIterator() {
            nodeStack = new BoundedArrayStack(rbtsize);
            curr = (RBTNode<K,V>) root;
        }

        public boolean hasNext() {
            return (!curr.isNil() || !nodeStack.isEmpty());
        }

        public K next() {
            while (!curr.isNil()) {
                nodeStack.push(curr);
                curr = curr.left;
            }

            if (!nodeStack.isEmpty()) {
                RBTNode<K,V> nextNode = (RBTNode<K,V>) nodeStack.pop();
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
     * Node class for the red-black tree
     */
    protected class RBTNode<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private boolean color;
	private int height;
	private RBTNode<K,V> left;
	private RBTNode<K,V> right;
	private RBTNode<K,V> parent;

	private RBTNode() {
	    key = null;
	    value = null;
	    color = false;
	    height = 0;
	    left = null;
	    right = null;
	    parent = null;
	}

	private RBTNode(K newKey, V newValue, boolean newColor, RBTNode<K,V> newLeft, RBTNode<K,V> newRight, RBTNode<K,V> newParent) {
	    key = newKey;
	    value = newValue;
	    color = newColor;
	    height = 1;
	    left = newLeft;
	    right = newRight;
	    parent = newParent;
	}

	private boolean isNil() {
	    return key == null && value == null;
	}

	private void setRed() {
	    color = true;
	}

	private void setBlack() {
	    color = false;
	}
    }



    public static void main (String [] args) {
        MyRBTMap <Integer, String> L = new MyRBTMap <Integer, String> ();
        L.insert (1, "hello");
        L.insert (2, "test");
        System.out.println(L.search (1).toString());
        System.out.println(L.search (2).toString());
        Iterator<Integer> I = L.iterator();
        while (I.hasNext())
            System.out.println (I.next());
        L.delete(2);
        System.out.println("L valid?  " + L.validate());

        MyRBTMap <Integer, String> R = new MyRBTMap <Integer, String> ();
        R.insert (11, "hello2");
        R.insert (12, "test2");
        R.insert (13, "test3");
	System.out.println("R.size = " + R.size());
        I = R.iterator();
        while (I.hasNext())
            System.out.println (I.next());
        System.out.println("R valid?  " + R.validate());

        MyRBTMap <Integer, String> T = new MyRBTMap <Integer, String> (7,"root",3,L,R);
        System.out.println("T valid?  " + T.validate());

        MyRBTMap <Integer, String> T2 = new MyRBTMap <Integer, String> (15,"root",2,L,R);
        System.out.println("T2 valid?  " + T2.validate());

        MyRBTMap <Integer, String> T3 = new MyRBTMap <Integer, String> (7,"root",6,L,R);
        System.out.println("T3 valid?  " + T3.validate());

        R.insert (14, "test4");
        R.insert (15, "test5");
        R.insert (16, "test6");
        System.out.println("R valid?  " + R.validate());
        MyRBTMap <Integer, String> T4 = new MyRBTMap <Integer, String> (7,"root",5,L,R);
        System.out.println("T4 valid?  " + T4.validate());
    }
}
