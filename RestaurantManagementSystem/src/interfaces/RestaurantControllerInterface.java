package interfaces;

public interface RestaurantControllerInterface {
	boolean editTableCount(int newTableCount);
	boolean addServer(String serverID);
	boolean deleteServer(String serverID);
	String getTableInfo(int tableNumber);
	boolean changeTableServer(int tableNumber, String newServerID);
	boolean setTableToInUse(int tableNumber, String serverID);
	boolean setTableToReady(int tableNumber);
	String getServerTables(String serverID);
	boolean submitFeedback(String serverID, String feedback, boolean good);
	String getServerFeedback(String serverID);
	boolean authenticate(String passcode);
}
