package model;

import interfaces.StorageSupportInterface;

import java.util.ArrayList;
import java.util.Collection;

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
	public boolean putServer(Server s) {
		return storage.putServer(s);
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
	
	public boolean putTable(Table table) {
		return storage.putTable(table);
	}
	
	public boolean deleteTable(int tableNumber) {
		return storage.deleteTable(tableNumber);
	}

	@Override
	public Collection<Server> getServers() {
		return storage.getServers();
	}

}
