public class TreeNode <T> {

	List <T> data;
	Location loc;
	TreeNode <T> c1;
	TreeNode <T> c2;
	TreeNode <T> c3;
	TreeNode <T> c4;
	
	public TreeNode(T e, Location loc) {
		data = new LinkedList <T> ();
		data.insert(e);
		this.loc = loc;
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
	}

	
}
