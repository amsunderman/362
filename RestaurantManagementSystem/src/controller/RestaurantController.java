package controller;

import java.util.ArrayList;

import interfaces.RestaurantControllerInterface;
import model.Order;
import model.Restaurant;
import model.Server;
import model.Table;

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

	/**
	 * sets table to "ready" status representing a table is ready to seat next customer
	 * @param tableNumber unique identifier for table that is to be set to ready
	 * @return true for operation success, false for failure
	 */
	@Override
	public boolean setTableToReady(int tableNumber) {
		return restaurant.setTableToReady(tableNumber);
	}

	/**
	 * gets a formatted string listing the tables a server is assigned to
	 * @param serverID unique identifier for server to retreive list of tables for
	 * @return list of tables server is assigned to (null if operation fails)
	 */
	@Override
	public String getServerTables(String serverID) {
		return restaurant.getServerTables(serverID);
	}

	/**
	 * submits feedback for a specified server
	 * @param serverID unique identifier for server to submit feedback for
	 * @param feedback string containing feedback for server
	 * @param good true marks positive feedback, false marks criticism
	 * @return true for operation success, false for failure
	 */
	@Override
	public boolean submitFeedback(String serverID, String feedback, boolean good) {
		return restaurant.submitFeedback(serverID, feedback, good);
	}

	/**
	 * gets a formatted string containing server feedback
	 * @param serverID unique identifier for server to get feedback for
	 * @return formatted string containing server feedback (null if operation fails)
	 */
	@Override
	public String getServerFeedback(String serverID) {
		return restaurant.getServerFeedback(serverID);
	}
	
	/**
	 * gets a list of servers with feedback that is positive.
	 * @param none
	 * @return a list of servers with good feedback.
	 */
	@Override
	public ArrayList<Server> getServersPositive()
	{
		return restaurant.getServersPositive();
	}
	
	/**
	 * gets a list of servers with feedback that is negative.
	 * @param none
	 * @return a list of servers with bad feedback.
	 */
	@Override
	public ArrayList<Server> getServersNegative()
	{
		return restaurant.getServersNegative();
	}
	
	/**
	 * gets formatted string containing all servers and the number of tables they are working
	 * @param none
	 * @return formatted string containing all servers and table count (null if operation fails)
	 */
	@Override
	public String getServersAndNumberOfTables() {
		return restaurant.getServersAndNumberOfTables();
	}

	/**
	 * creates order for a customer at a specified table
	 * @param tableNumber unique identifier for table that contains customer who is ordering
	 * @param drink integer representing the drink ordered from menu
	 * @param appetizer integer representing the appetizer ordered from menu
	 * @param meal integer representing the meal ordered from the menu
	 * @param side integer representing the side ordered from the menu
	 * @param special string representing any special request from the customer
	 * @return true if order is successfully created, false if operation fails
	 */
	@Override
	public boolean createOrder(int tableNumber, int drink,
			int appetizer, int meal, int side, String special) {
		return restaurant.createOrder(tableNumber, drink, appetizer, meal, side, special);
	}

	/**
	 * modifies an order's specified field with a provided new value
	 * @param tableNumber unique identifier for the table that contains the order to be modified
	 * @param orderID unique identifier for order to modify
	 * @param field string representing which field needs modified
	 * @param newvalue string that contains new data to write into specified field
	 * @return true if operation success, false if operation fails
	 */
	@Override
	public boolean modifyOrder(int tableNumber, int orderID, String field, String newvalue) {
		return restaurant.modifyOrder(tableNumber, orderID, field, newvalue);
	}
	
	/**
	 * deletes a specified order, removing it from restaurant model entirely
	 * @param tableNumber unique identifier for table that contains order to be removed
	 * @param orderID unique identifier for order to remove
	 * @return true for operation success, false for failure
	 */
	@Override
	public boolean deleteOrder(int tableNumber, int orderID)
	{
		return restaurant.deleteOrder(tableNumber, orderID);
	}

	/**
	 * obtains a list of orders (sorted by earliest creation timestamp)
	 * @param none
	 * @return array list of orders sorted by creation date
	 */
	@Override
	public ArrayList<Order> obtainOrderListByCreation() {
		return restaurant.obtainOrderListByCreation();
	}

	/**
	 * gets formatted string containing tables checks
	 * @param tableNumber unique identifier for table to retrieve check information from
	 * @return formatted string containing all check information for specified table (null if operation fails)
	 */
	@Override
	public String getTablesChecks(int tableNumber) {
		return restaurant.getTablesChecks(tableNumber);
	}

	/**
	 * gets formatted string containing all tables orders
	 * @param tableNumber unique identifier for table to retrieve order information from
	 * @return formatted string containing all order information for specified table (null if operation fails)
	 */
	@Override
	public String getTablesOrders(int tableNumber) {
		return restaurant.getTablesOrders(tableNumber);
	}

	/**
	 * uses table order information to generate monetary check objects
	 * @param tableNumber unique identifier for table to generate checks for
	 * @param orders list strings listing orders on a per check basis
	 * @return true if operation succeeds, false if operation fails
	 */
	@Override
	public boolean generateChecks(int tableNumber, ArrayList<String> orders) {
		return restaurant.generateChecks(tableNumber, orders);
	}

	/**
	 * obtains the number of times a specific menu option has been ordered
	 * @param type integer representing the menu subsection (i.e. 1 represents drinks)
	 * @param itemIndex number correlating to menu option in specified subsection
	 * @return number of times menu option has been ordered (-1 if operation fails)
	 */
	@Override
	public int checkItemPopularity(int type, int itemIndex) {
		return restaurant.checkItemPopularity(type, itemIndex);
	}
	
	/**
	 * Gets a list of tables which require immediate server attention.
	 * @param none
	 * @return list of tables that require server action
	 */
	@Override
	public ArrayList<Table> getTablesWithServerActionReqd()
	{
		return restaurant.getTablesWithServerActionReqd();
	}
	
	/**
	 * gets the estimated wait time for next table
	 * @param none
	 * @return number of minutes until table will be available
	 */
	@Override
<<<<<<< HEAD
	public long getEstimatedWaitForNextTable()
=======
	public int getEstimatedWaitForNextTable()
>>>>>>> c63277de16cd3734bc7a447b292039999fe1353e
	{
		return restaurant.getEstimatedWaitForNextTable();
	}
	
	/**
	 * get revenue earned to date
	 * @param none
	 * @return revenue to date in dollars
	 */
	@Override
	public int getRevenue()
	{
		return restaurant.getRevenue();
	}
	
	/**
	 * log on authentication for Restaurant Manager
	 * @param passcode password for manager account
	 * @return true if manager successfully logs in, false if operation fails
	 */
	@Override
	public boolean authenticate(String passcode) {
		return restaurant.authenticate(passcode);
	}
	
	/**
	 * stores all restaurant data into text files as persistent data. Must be called by View on exit
	 * @param none
	 * @return void
	 */
	@Override
	public void dumpToFile() {
		restaurant.dumpToFile();
	}
	
	@Override
	public long getAverageTimeInUse() {
		return restaurant.getAverageTimeInUse();
	}

	@Override
	public long getAverageTimeReadyForUse() {
		return restaurant.getAverageTimeReadyForUse();
	}
}
