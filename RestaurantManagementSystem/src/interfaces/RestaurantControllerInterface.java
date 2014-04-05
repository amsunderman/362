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
	boolean createOrder(int tableNumber, String drink, String appetizer, String meal, String side, String special);
	boolean modifyOrder(int tableNumber, int orderID, String field, String newvalue);
	boolean authenticate(String passcode);
}
