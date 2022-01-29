public class VehicleHiringManager {
	LocatorMap<String> LocationMap;
	
	
	public VehicleHiringManager() {
		LocationMap = new TreeLocatorMap<String> ();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return LocationMap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		LocationMap = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		Pair <Boolean, Integer> Add = LocationMap.add(id, loc);
		return Add.first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		Pair <Boolean, Integer> Move = LocationMap.move(id, loc);
		return Move.first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		Pair <Boolean, Integer> Remove = LocationMap.remove(id);
		return Remove.first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return LocationMap.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Location LowerLeft = new Location (loc.x - r, loc.y - r);
		Location UpperRight = new Location (loc.x + r , loc.y + r);
		Pair <List<String> , Integer> Range = LocationMap.getInRange(LowerLeft, UpperRight);
		return Range.first;
	}
}
