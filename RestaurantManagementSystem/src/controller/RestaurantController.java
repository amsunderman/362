package controller;

import java.util.ArrayList;

import interfaces.RestaurantControllerInterface;
import model.Order;
import model.Restaurant;

/**
 * Controller class that mediates interactions between view and Restaurant Model. Handles an instance of Restaurant.
 * implements Restaurant Controller Interface.
 * @author Team 3
 *
 */
public class RestaurantController implements RestaurantControllerInterface{

	/**
	 * Instance of restaurant
	 */
	private Restaurant restaurant = null;
	
	/**
	 * Default constructor for RestaurantController. Initializes instance of Restaurant model
	 */
	public RestaurantController() {
		restaurant = new Restaurant();
	}

	/**
	 * edits restaurant table count according to provided argument
	 * @param newTableCountvnumber of tables to be contained in Restaurant
	 * @return true for success false for failure
	 */
	@Override
	public boolean editTableCount(int newTableCount) {
		return restaurant.editTableCount(newTableCount);
	}

	/**
	 * Adds server to Restaurant model
	 * @param serverID server's unique identifier to be stored in memory
	 * @return true for success false for failure
	 */
	@Override
	public boolean addServer(String serverID) {
		return restaurant.addServer(serverID);
	}

	/**
	 * Removes server from model by the server's unique identifier
	 * @param serverID server to remove from model
	 * @return true for success false for failure
	 */
	@Override
	public boolean deleteServer(String serverID) {
		return restaurant.deleteServer(serverID);
	}

	/**
	 * Retrieves table information in formatted string
	 * @param tableNumber table to get information from
	 * @return table "in use" status and server assigned (null if operation fails)
	 */
	@Override
	public String getTableInfo(int tableNumber) {
		return restaurant.getTableInfo(tableNumber);
	}

	/**
	 * Change server assigned to a specific table
	 * @param tableNumber unique identifier for table to change server of
	 * @param newServerID unique identifier for server to assign to table
	 * @return true for success false for failure
	 */
	@Override
	public boolean changeTableServer(int tableNumber, String newServerID) {
		return restaurant.changeTableServer(tableNumber, newServerID);
	}

	/**
	 * sets table to "in use" status representing a table with customers seated
	 * @param tableNumber unique identifier for table that is being set in use
	 * @param serverID unique identifier for server who will work table
	 * @return true for success false for failure
	 */
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
	public boolean createOrder(int tableNumber, int drink,
			int appetizer, int meal, int side, String special) {
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

	@Override
	public int checkItemPopularity(int type, int itemIndex) {
		return restaurant.checkItemPopularity(type, itemIndex);
	}

}
