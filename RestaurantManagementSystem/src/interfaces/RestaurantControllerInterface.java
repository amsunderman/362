package interfaces;

public interface RestaurantControllerInterface {
	boolean editTableCount(String passcode, int newTableCount);
	boolean addServer(String passcode, String serverID);
	boolean deleteServer(String passcode, String serverID);
	String getTableInfo(int tableNumber);
	boolean changeTableServer(int tableNumber, String newServerID);
	boolean setTableToInUse(int tableNumber, String serverID);
	boolean setTableToReady(int tableNumber);
	String getServerTables(String serverID);
	boolean submitFeedback(String serverID, String feedback, boolean good);
	String getServerFeedback(String serverID);
}
