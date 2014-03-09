package model;

import interfaces.StorageSupportInterface;

import java.util.ArrayList;

public class StorageSupport implements StorageSupportInterface{

	private Storage storage = null;
	
	public StorageSupport(RestaurantStatistics restaurantStatistics) {
		storage = new Storage(restaurantStatistics);
	}
	
	@Override
	public boolean authenticatePasscode(String passcode) {
		return (storage.getPasscode().equals(passcode));
	}

	@Override
	public boolean addServer(Server s) {
		return storage.addServer(s);
	}

	@Override
	public boolean deleteServer(String serverID) {
		return storage.deleteServer(serverID);
	}

	@Override
	public Table getTable(int tableNumber) {
		return storage.getTable(tableNumber);
	}

	@Override
	public ArrayList<Table> getAllTables() {
		return storage.getAllTables();
	}

	@Override
	public Server getServer(String serverID) {
		return storage.getServer(serverID);
	}

	public void dumpToFile(RestaurantStatistics restaurantStatistics) {
		storage.dumpToFile(restaurantStatistics);
	}
	
	public boolean addTable(Table table) {
		return storage.addTable(table);
	}
	
	public boolean deleteTable(int tableNumber) {
		return storage.deleteTable(tableNumber);
	}

}
