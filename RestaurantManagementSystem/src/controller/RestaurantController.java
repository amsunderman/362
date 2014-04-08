package controller;

import java.util.ArrayList;

import interfaces.RestaurantControllerInterface;
import model.Order;
import model.Restaurant;

public class RestaurantController implements RestaurantControllerInterface{
	
	private Restaurant restaurant = null;
	
	public RestaurantController() {
		restaurant = new Restaurant();
	}

	@Override
	public boolean editTableCount(int newTableCount) {
		return restaurant.editTableCount(newTableCount);
	}

	@Override
	public boolean addServer(String serverID) {
		return restaurant.addServer(serverID);
	}

	@Override
	public boolean deleteServer(String serverID) {
		return restaurant.deleteServer(serverID);
	}

	@Override
	public String getTableInfo(int tableNumber) {
		return restaurant.getTableInfo(tableNumber);
	}

	@Override
	public boolean changeTableServer(int tableNumber, String newServerID) {
		return restaurant.changeTableServer(tableNumber, newServerID);
	}

	@Override
	public boolean setTableToInUse(int tableNumber, String serverID) {
		return restaurant.setTableToInUse(tableNumber, serverID);
	}

	@Override
	public boolean setTableToReady(int tableNumber) {
		return restaurant.setTableToReady(tableNumber);
	}

	@Override
	public String getServerTables(String serverID) {
		return restaurant.getServerTables(serverID);
	}

	@Override
	public boolean submitFeedback(String serverID, String feedback, boolean good) {
		return restaurant.submitFeedback(serverID, feedback, good);
	}

	@Override
	public String getServerFeedback(String serverID) {
		return restaurant.getServerFeedback(serverID);
	}
	

	@Override
	public boolean authenticate(String passcode) {
		return restaurant.authenticate(passcode);
	}
	
	//will be called by view on exit
	public void dumpToFile() {
		restaurant.dumpToFile();
	}

	@Override
	public boolean createOrder(int tableNumber, String drink,
			String appetizer, String meal, String side, String special) {
		return restaurant.createOrder(tableNumber, drink, appetizer, meal, side, special);
	}

	@Override
	public boolean modifyOrder(int tableNumber, int orderID, String field, String newvalue) {
		return restaurant.modifyOrder(tableNumber, orderID, field, newvalue);
	}
	
	@Override
	public boolean deleteOrder(int tableNumber, int orderID)
	{
		return restaurant.deleteOrder(tableNumber, orderID);
	}

	@Override
	public ArrayList<Order> obtainOrderListByCreation() {
		return restaurant.obtainOrderListByCreation();
	}

	@Override
	public String getServersAndNumberOfTables() {
		return restaurant.getServersAndNumberOfTables();
	}

	@Override
	public String getTablesChecks(int tableNumber) {
		return restaurant.getTablesChecks(tableNumber);
	}

	@Override
	public String getTablesOrders(int tableNumber) {
		return restaurant.getTablesOrders(tableNumber);
	}

	@Override
	public boolean generateChecks(int tableNumber, ArrayList<String> orders) {
		return restaurant.generateChecks(tableNumber, orders);
	}

}
