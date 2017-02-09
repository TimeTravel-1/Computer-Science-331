import java.util.Iterator;
import java.util.TreeMap;

/**
 *  A red-black tree implementation of the SimpleSortedMap interface based on the JCF
 *  TreeMap class.
 *  @author M. Jacobson
 */
public class RBTMap <K extends Comparable <K>, V> implements SimpleSortedMap <K, V> {
    private TreeMap<K,V> T;

    RBTMap() {
	T = new TreeMap<K,V>();
    }

    /**
     * Tests whether the map is empty.
     *
     * @return true if the dictionary is empty, false otherwise
     */
    public boolean isEmpty() {
	return T.size() == 0;
    }

    /**
     * Returns the size of the map.
     *
     * @return size of the RBTMap
     */
    public int size() {
	return T.size();
    }

    /**
     * Inserts a key-value pair into the map.
     *
     * @param key The key to be inserted.
     * @param value Key's corresponding value.
     * @throws KeyFoundException If a matching key is already present in the map
     */
    public void insert(K key, V value) throws KeyFoundException {
        if (T.containsKey(key))
	    throw new KeyFoundException();
	T.put(key,value);
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
	if (!T.containsKey(key))
	    throw new KeyNotFoundException();
        return (V) T.get(key);
    }

    /**
     * Deletes the key-value pair with the specified key
     * from the map
     *
     * @param key The key to remove from the map.
     * @throws KeyNotFoundException If key is not found
     * in the map.
     */
    public void delete(K key) throws KeyNotFoundException {
	if (!T.containsKey(key))
	    throw new KeyNotFoundException();
	T.remove(key);
    }

    /**
     * Modifies the value corresponding to key in the map.
     *
     * @param key The key whose value to modify.
     * @param value The new value of key.
     * @throws KeyNotFoundException If key was not found in the map
     * and therefore no value was modified.
     */
    public void modify(K key, V value) throws KeyFoundException {
	if (!T.containsKey(key))
	    throw new KeyFoundException();
	T.put(key,value);
    }

    public Iterator<K> iterator() {
	return T.keySet().iterator();
    }


    public static void main(String[] args) {
        RBTMap <Integer, String> ll = new RBTMap <Integer, String> ();
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
