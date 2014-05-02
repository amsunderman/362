package interfaces;

import java.util.ArrayList;
import java.util.Collection;

import model.RestaurantStatistics;
import model.Server;
import model.Table;

public interface StorageSupportInterface {
	boolean authenticatePasscode(String passcode);
	boolean putServer(Server s);
	boolean deleteServer(String serverID);
	Table getTable(int tableNumber);
	ArrayList<Table> getAllTables();
	Server getServer(String serverID);
	Collection<Server> getServers();
	void dumpToFile(RestaurantStatistics restaurantStatistics);
	boolean putTable(Table table);
	boolean deleteTable(int tableNumber);
}
