
public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode <K,T> root , current;
	
	@Override
	public boolean empty() {
		if (root == null)
			return true;
		return false;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
		
	}

	@Override
	public void update(T e) {
		current.data = e;

	}
	
	
	@Override
	
	public Pair<Boolean, Integer> find(K key) {
		int count = 0;
		BSTNode <K, T> p = root;
		
	
		if (empty())
			return new Pair<Boolean, Integer> (false, count);
		
		while (p != null) {
			count++;
			if (key != null) {
			if (key.compareTo(p.key) == 0) {
				current = p;
				return new Pair <Boolean , Integer> (true, count);
			}
				
				else if (key.compareTo(p.key) < 0)  
					p = p.left;
				
				else
					p = p.right;
			}
		}

			return new Pair<Boolean, Integer> (false, count);
			
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K, T> p  = root;
		BSTNode<K, T> q = root;
		int count = 0;
		
		while (p != null) {	
			count++;
			q =p;
			if (key != null) {
				if (key.compareTo(p.key) == 0)
					return new Pair <Boolean , Integer> (false, count);
				
				else if (key.compareTo(p.key) > 0)
					p = p.right;
				else
					p = p.left;
			}
		}
		if (empty()) 
			root = current = new BSTNode<K,T> (key , data);
		
		else if (key != null ) {
			if (key.compareTo(q.key) > 0) {
				q.right = new BSTNode<K,T> (key , data);
				current = q.right;
			}
				
			else {
				q.left = new BSTNode<K,T> (key , data);
				current = q.left;
			}
			
			//count++;
		}
		return new Pair <Boolean , Integer> (true, count);
		
			
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		int count = 0;
		BSTNode <K , T> p = root;
		BSTNode <K , T> q = null;
		K key1 = key; 
			while (p != null) {
				count++;
				if (key != null) {
				if (key1.compareTo(p.key) < 0) {
					q = p;
					p = p.left;
				}
				else if (key1.compareTo(p.key) > 0) {
					q = p;
					p = p.right;
				}
				
				else {
					
				if ( (p.left != null) && (p.right != null)) {//Case3: Two Children
					BSTNode <K ,T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					key1 = min.key;
					p = min;
					}
					
				if (p.left != null)  // One Child
					p = p.left; 
				else // One or no children
					p = p.right;
				
				if (q == null)  //Root must change
					root = p;
				else {
					if (key1.compareTo(q.key) < 0) 
						q.left = p;
					else 
						q.right = p;
				}
				current = root;		 
				return new Pair <Boolean , Integer> (true , count);	
				}}}
		
		return new Pair <Boolean , Integer> (false, count);
	}

	@Override
	public List<K> getAll() {
		List<K> Keys = new LinkedList<K> ();	
		inOrder(root , Keys);	
		return Keys;
	}

	private void inOrder(BSTNode<K,T> node , List<K> l) {
		if (node == null) 
	      return;
	    
	    inOrder(node.left , l);
	    l.insert(node.key);
	    inOrder(node.right , l);    
		
	}
	

	
	
	
	
}