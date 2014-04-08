package interfaces;

import java.util.HashMap;
import java.util.Set;

import model.Check;
import model.Order;
import model.Server;

public interface TableInterface {
	String getTableInfo();
	Server getServer();
	boolean setServer(Server newServer);
	boolean setToInUse(Server server);
	void setToReady();
	int getTableNumber();
	boolean putOrder(Order o);
	Order getOrder(int orderID);
	HashMap<Integer, Order> getAllOrders();
	boolean deleteOrder(int orderID);
	Set<Check> getAllChecks();
	boolean addCheck(String orderString);
}
