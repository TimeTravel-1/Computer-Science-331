import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 *  A red black tree implementation of the SimpleSortedMap interface 
 *  using generics.
 *  @author David Ng
 */
public class RBTMap <K extends Comparable <K>, V> implements SimpleSortedMap <K, V>{

	private TreeMap<K,V> tree;
	
	public RBTMap()
	{
		tree = new TreeMap<K,V>();
	}
	
	//iterator to iterate over keys 
	public Iterator<K> iterator() {
		Set<K> set = tree.keySet();
		Iterator<K> it = set.iterator();
		return it;
		
	}

	 /**
   * Tests whether the map is empty.
   *
   * @return true if the dictionary is empty, false otherwise
   */
	public boolean isEmpty() {

		return tree.isEmpty();
	}

	 /**
   * Returns the number of key-values pairs in the map.
   * @returns int The size of the map.
   */
	public int size() {

	return tree.size();
	}

	/**
   * Inserts a key-value pair into the map.
   * @param key The key to be inserted.
   * @param value Key's corresponding value.
   * @throws KeyFoundException If a matching key is
   * already present in the map
   */
	public void insert(K key, V value) throws KeyFoundException 
	{
		if (tree.containsKey(key))
		{
			throw new KeyFoundException();
		}
		else
		{
			tree.put(key, value);
		}
	}

	 /**
   * Deletes the key-value pair with the specified key
   * from the map
   * @param key The key to remove from the map.
   * @throws KeyNotFoundException If key is not found
   * in the map.
   */
	public void delete(K key) throws KeyNotFoundException 
	{
		if (!tree.containsKey(key))
		{
			throw new KeyNotFoundException();
		}
		else
		{
			tree.remove(key);
		}
	}

  /**
   * Returns the value corresponding to key.
   * @param key The key to search for in the map.
   * @returns V The value corresponding to key.
   * @throws KeyNotFoundException If key is not found
   * in the map.
   */
	public V search(K key) throws KeyNotFoundException {
	
		if (tree.containsKey(key))
		{
			return tree.get(key);
		}
		else
		{
			throw new KeyNotFoundException();
		}
	}

	/**
   * Modifies the value corresponding to key in the map.
   * @param key The key whose value to modify.
   * @param value The new value of key.
   * @throws KeyNotFoundException If key was not found in the map
   * and therefore no value was modified.
   */
	public void modify(K key, V value) throws KeyNotFoundException {

	
			if(tree.containsKey(key))
			{
				tree.put(key, value);
			}
			else
			{
				throw new KeyNotFoundException();
			}
	}
	
	//testing RBTMap
	 public static void main (String [] args) {
     RBTMap <Integer, String> ll = new RBTMap <Integer, String> ();
     ll.insert (2, "hello");
     ll.insert (1, "test");
     System.out.println(ll.search (1).toString());
     System.out.println(ll.search (2).toString());
     ll.insert(0,  "SDFSFD");
     Iterator<Integer> I = ll.iterator();
     while (I.hasNext()) 
         System.out.println (I.next());
 }
}