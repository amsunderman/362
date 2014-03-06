package interfaces;

import java.util.ArrayList;

import model.Server;
import model.Table;

public interface DatabaseSupportInterface {
	boolean authenticatePasscode(String passcode);
	boolean updateTableCount(int newTableCount);
	boolean addServer(String serverID);
	boolean deleteServer(String serverID);
	Table getTable(int tableNumber);
	ArrayList<Table> getAllTables();
	Server getServer(String serverID);
}
