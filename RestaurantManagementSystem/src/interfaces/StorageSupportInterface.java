package interfaces;

import java.util.ArrayList;

import model.Server;
import model.Table;

public interface StorageSupportInterface {
	boolean authenticatePasscode(String passcode);
	boolean addServer(String serverID);
	boolean deleteServer(String serverID);
	Table getTable(int tableNumber);
	ArrayList<Table> getAllTables();
	Server getServer(String serverID);
}
