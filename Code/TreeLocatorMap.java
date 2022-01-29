
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
		Map<K, Location> Bst;
		Locator <K> Locations;
		
		
		
		public TreeLocatorMap () {
			Bst = new BST <K, Location> ();
			Locations = new TreeLocator <K> ();
			}
	
	@Override
	public Map<K, Location> getMap() {
		return Bst;
	}

	@Override
	public Locator<K> getLocator() {
		return Locations;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair <Boolean , Integer> Tree = Bst.insert(k, loc);
		if (Tree.first) 
			Locations.add(k, loc);
		return Tree;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair <Boolean, Integer> Tree = Bst.find(k);
		
		if (Tree.first) {
			Locations.remove(k, Bst.retrieve());
			Locations.add(k, loc);
			Bst.update(loc);
		}
		return Tree;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair <Boolean, Integer> Tree = Bst.find(k);
		
		if (Tree.first)
			return new Pair<Location, Integer> (Bst.retrieve(), Tree.second);
		
			return new Pair<Location, Integer> (null, Tree.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair <Boolean, Integer> Tree = Bst.find(k);
		
		if (Tree.first) {
			Locations.remove(k, Bst.retrieve());
			Bst.remove(k);
		}
		return Tree;
	}

	@Override
	public List<K> getAll() {
		return Bst.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<K>>>, Integer> Range = Locations.inRange(lowerLeft, upperRight);
		List<Pair<Location, List<K>>> list = Range.first;
		List <K> AllKeys = new LinkedList<K> ();	
	
		if (!list.empty()) {
			list.findFirst();
			
			while (!list.last()) {
				if (!list.retrieve().second.empty()) {
					list.retrieve().second.findFirst();
					
					while (!list.retrieve().second.last()) {
						AllKeys.insert(list.retrieve().second.retrieve());
						list.retrieve().second.findNext();
					}
					AllKeys.insert(list.retrieve().second.retrieve());
					list.retrieve().second.findNext();
				}
				list.findNext();
			}
			if (!list.retrieve().second.empty()) {
				list.retrieve().second.findFirst();
				
				while (!list.retrieve().second.last()) {
					AllKeys.insert(list.retrieve().second.retrieve());
					list.retrieve().second.findNext();
				}
				AllKeys.insert(list.retrieve().second.retrieve());
				list.retrieve().second.findNext();
			}	
		}
			
		Pair<List<K>, Integer> KeysInRange = new Pair<List<K>, Integer> (AllKeys, Range.second);		
		return KeysInRange;
	}
	
	 
}
