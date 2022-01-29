public class BSTNode <K extends Comparable<K>, T> {
	public K key;
	public T data;
	public BSTNode <K , T> left , right;
	
	public BSTNode(K k , T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BSTNode (K k , T val,  BSTNode <K, T> l, BSTNode <K, T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}
