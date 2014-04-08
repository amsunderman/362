package interfaces;

import java.util.ArrayList;
import model.Order;

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
	boolean deleteOrder(int tableNumber, int orderID);
	ArrayList<Order> obtainOrderListByCreation();
	boolean authenticate(String passcode);
	String getServersAndNumberOfTables();
	String getTablesChecks(int tableNumber);
	String getTablesOrders(int tableNumber);
}
