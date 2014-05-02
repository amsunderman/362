package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import model.Check;
import model.Order;
import model.Server;

public interface TableInterface {
	String getTableInfo();
	Server getServer();
	boolean setServer(Server newServer);
	long setToInUse(Server server);
	long setToReady();
	int getTableNumber();
	boolean putOrder(Order o);
	Order getOrder(int orderID);
	HashMap<Integer, Order> getAllOrders();
	boolean deleteOrder(int orderID);
	ArrayList<Check> getAllChecks();
	boolean addCheck(String orderString);
	public boolean isInUse();
}
