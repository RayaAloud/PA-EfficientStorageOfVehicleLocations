public class TreeLocator<T> implements Locator<T> {
	TreeNode <T> root;
	TreeNode <T> current;
	
	
	
	@Override
	public int add(T e, Location loc) {
		TreeNode <T> p = root;
		TreeNode<T> q = root;
		int count = 0;
		
		if (root == null) {
			root = current = new TreeNode<T>(e , loc);
			return count;
		}
		
		while (p != null) {
			count++;
			q = p;
			if (loc.x == p.loc.x && loc.y == p.loc.y) {
				p.data.insert(e);
				return count;
			}
			
			else if (loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.c1;
			else if (loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.c2;
			else if (loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.c3;
			else 
				p = p.c4;		
		}
		TreeNode<T> newNode = new TreeNode<T>(e, loc);
		
		if (loc.x < q.loc.x && loc.y <= q.loc.y)
			q.c1 = newNode;
		else if (loc.x <= q.loc.x && loc.y > q.loc.y)
			q.c2 = newNode;
		else if (loc.x > q.loc.x && loc.y >= q.loc.y)
			q.c3 = newNode;
		else if (loc.x >= q.loc.x && loc.y < q.loc.y)
			q.c4 = newNode;	
		
		return count;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		TreeNode <T> p = root;
		int count = 0;
		
		if (root == null)
			return new Pair <List<T>, Integer> (new LinkedList<T>() , count);
		
		while (p != null) {
			count++;
			if (loc.x == p.loc.x && loc.y == p.loc.y)
				return new Pair <List<T>, Integer> (p.data, count);
			
			else if (loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.c1;
			else if (loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.c2;
			else if (loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.c3;
			else 
				p = p.c4;
					
		}
		return new Pair <List<T>, Integer> (new LinkedList<T>() , count);
		
		
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		TreeNode<T> p = root;
		int count = 0;
		boolean flag =  false;
		if (root == null)
			return new Pair <Boolean, Integer> (false, count);
		
		while (p != null) {
			count++;
			if (loc.x == p.loc.x && loc.y == p.loc.y) {
				if (p.data.empty())
					return new Pair <Boolean, Integer> (false, count);
			
				p.data.findFirst();
				while (!p.data.last()) {
					if (p.data.retrieve().equals(e)) {
						p.data.remove();
						flag = true;
					}
					else
						p.data.findNext();
					
				}
				if (p.data.retrieve().equals(e)) {
					p.data.remove();
					flag = true;
				}
				
				if (flag)
					return new Pair <Boolean, Integer> (true, count);
				else
					return new Pair <Boolean, Integer> (false, count);

			}	
			else if (loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.c1;
			else if (loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.c2;
			else if (loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.c3;
			else 
				p = p.c4;
					
		}
		return new Pair <Boolean, Integer> (false, count);
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List <Pair<Location , List<T>>> list = new LinkedList <Pair<Location , List<T>>>();
		preOrder(root, list);
		return list;
	}
	
	
	private void preOrder (TreeNode <T> node, List <Pair<Location , List<T>>> l) {
		if (node == null)
			return;
		
		l.insert(new Pair <Location, List <T>> (node.loc, node.data));
		preOrder(node.c1, l);
		preOrder(node.c2, l);
		preOrder(node.c3, l);
		preOrder(node.c4, l);
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<T>>>, Integer> Range = new Pair<List<Pair<Location, List<T>>>, Integer> (new LinkedList <Pair<Location, List<T>>>(),0);
		PreOrderRange(root, Range, lowerLeft.x, lowerLeft.y, upperRight.x , upperRight.y);
		return Range;
	}
	
	private void PreOrderRange (TreeNode<T> p, Pair<List<Pair<Location, List<T>>>, Integer> Range, int x1, int y1, int x2, int y2) {
		if (p == null)
			return;
		
		Range.second++;
		//if in Rectangle
		if (p.loc.x >= x1 &&  p.loc.x <= x2 && p.loc.y >= y1 && p.loc.y <= y2) {
			Range.first.insert(new Pair <Location , List <T>> (p.loc, p.data));
			PreOrderRange (p.c1, Range , x1, y1, x2, y2);
			PreOrderRange (p.c2, Range , x1, y1, x2, y2);
			PreOrderRange (p.c3, Range , x1, y1, x2, y2);
			PreOrderRange (p.c4, Range , x1, y1, x2, y2);
		}
		
		else if (x2 < p.loc.x && y2 <= p.loc.y)
			PreOrderRange (p.c1, Range , x1, y1, x2, y2);
		else if (x2 <= p.loc.x && y1 > p.loc.y)
			PreOrderRange (p.c2, Range , x1, y1, x2, y2);
		else if (x1 > p.loc.x && y1 >= p.loc.y)
			PreOrderRange (p.c3, Range , x1, y1, x2, y2);
		else if (x1 >= p.loc.x && y2 < p.loc.y)
			PreOrderRange (p.c4, Range , x1, y1, x2, y2);
		
		
		else if (y2 < p.loc.y) {
			PreOrderRange (p.c1, Range , x1, y1, x2, y2);
			PreOrderRange (p.c4, Range , x1, y1, x2, y2);
		}
		
		else if (y1 > p.loc.y) {
			PreOrderRange (p.c2, Range , x1, y1, x2, y2);
			PreOrderRange (p.c3, Range , x1, y1, x2, y2);
		}
		else if (x2 < p.loc.x) {
			PreOrderRange (p.c1, Range , x1, y1, x2, y2);
			PreOrderRange (p.c2, Range , x1, y1, x2, y2);
		}
		else if (x1 > p.loc.x) {
			PreOrderRange (p.c3, Range , x1, y1, x2, y2);
			PreOrderRange (p.c4, Range , x1, y1, x2, y2);
		}
			
	}
	
	



}
