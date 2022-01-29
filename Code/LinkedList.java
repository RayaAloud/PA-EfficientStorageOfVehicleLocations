
 class Node <T> {
	public T data;
	public Node <T> next; 
		
	public Node (T val) {
		data = val;
		next = null;
	}
	
}
public class LinkedList<T> implements List<T> {
	
		private Node<T> head;
		private Node<T> current;

	@Override
	public boolean empty() {
		if (head == null)
			return true;
		return false;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		current = current.next;
	}

	@Override
	public boolean last() {
		if (current.next == null)
			return true;
		return false;
	}

	@Override
	public T retrieve() {
		if (current != null)
			return current.data;
		return null;
	}

	@Override
	public void update(T e) {
		current.data = e;

	}

	@Override
	public void insert(T e) {
		Node<T> newNode = new Node<T> (e);
		if (empty()) 
			head = current = newNode;
		else {
			Node <T> tmp = current.next;
			current.next = newNode;
			current = current.next;
			current.next = tmp;
		}

	}

	@Override
	public void remove() {
		if (current == head)
			head = head.next;
		else {
			Node <T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			
			tmp.next = current.next;
		}
		
		if (current.next == null)
			current = head;
		else
			current = current.next;			

	}
	
}

