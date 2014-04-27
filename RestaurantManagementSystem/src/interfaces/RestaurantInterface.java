package interfaces;

import java.util.ArrayList;

import model.Order;

public interface RestaurantInterface {
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
	String getServersAndNumberOfTables();
	boolean createOrder(int tableNumber, int drink, int appetizer, int meal, int side, String special);
	boolean modifyOrder(int tableNumber, int orderID, String field, String newvalue);
	boolean deleteOrder(int tableNumber, int orderID);
	ArrayList<Order> obtainOrderListByCreation();
	String getTablesChecks(int tableNumber);
	String getTablesOrders(int tableNumber);
	int checkItemPopularity(int type, int itemIndex);
	boolean generateChecks(int tableNumber, ArrayList<String> orders);
	boolean authenticate(String passcode);
}
