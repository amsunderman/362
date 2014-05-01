package model;

import interfaces.RestaurantInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Table;

/**
 * Restaurant model that simulates a restaurant's interactions between servers, tables, orders, and checks
 * @author Team 3
 *
 */
public class Restaurant implements RestaurantInterface {
	
	/**
	 * instance of StorageSupport that manages interactions with persistent data files
	 */
	private StorageSupport storageSupport = null;
	
	/**
	 * instance of RestaurantStatistics which logs information to be used by reporting functions
	 */
	private RestaurantStatistics restaurantStatistics = null;
	
	/**
	 * current orderID. increments for each new order
	 */
	private int orderID = 0;
	
	/**
	 * flag to notify model if user is logged in as management. defaults to false
	 */
	private boolean authenticated = false;
	
	/**
	 * default constructor for Restaurant. Initializes Restaurant Statistics via storageSupport's populate method that
	 * reads in initial values from persistent text files.
	 */
	public Restaurant() {
		
		//create instance of RestaurantStatistics
		restaurantStatistics = new RestaurantStatistics();
		
		//passes restaurantStatistics for initial populate from file
		storageSupport = new StorageSupport(restaurantStatistics);
		
		//set orderID according to populated RestaurantStatistics instance
		orderID = restaurantStatistics.getOrderID();
	}
	
	/**
	 * edits restaurant table count according to provided argument
	 * @param newTableCountvnumber of tables to be contained in Restaurant
	 * @return true for success false for failure
	 */
	@Override
	public boolean editTableCount(int newTableCount) {
		
		// if user is not authenticated, abort operation
		if (!authenticated) {
			return false;
		}
		
		// if tablecount is invalid, abort operation
		if (newTableCount < 0) {
			return false;
		}
		
		// save old table count for data manipulation
		int oldTableCount = restaurantStatistics.getTableCount();
		
		// if old table count is -1 (restaurant hasn't been initialized) then put new tables into database
		if (oldTableCount == -1) {
			for (int i = 1; i <= newTableCount; i++) {
				if(!storageSupport.putTable(new Table(i))) {
					return false; //if putTable() fails, return false
				}
			}
		}
		
		// if new count is different then old count then update tablecount in RestaurantStatistics instance
		else if (oldTableCount != newTableCount && restaurantStatistics.updateTableCount(newTableCount)) {
			
			// if new count is > old count then loop through and add extra tables
			if (newTableCount > oldTableCount) {
				while (newTableCount != oldTableCount) {
					if(!storageSupport.putTable(new Table(oldTableCount+1))) {
						return false; //if putTable() fails, return false
					}
					oldTableCount++;
				}
			} 
			
			// if new count is < old count then loop through and delete removed tables
			else {
				while (newTableCount != oldTableCount) {
					if(!storageSupport.deleteTable(oldTableCount)) {
						return false; //if deleteTable() fails, return false
					}
					oldTableCount--;
				}
			}
		}
		
		// update tablecount in RestaurantStatistics instance
		restaurantStatistics.updateTableCount(newTableCount);
		
		// return successfully
		return true;
	}

	/**
	 * Adds server to Restaurant model
	 * @param serverID server's unique identifier to be stored in memory
	 * @return true for success false for failure
	 */
	@Override
	public boolean addServer(String serverID) {
		
		// if manager is not logged in, exit operation
		if (!authenticated) {
			return false;
		}
		
		// create new server object and put it into storage
		Server s = new Server(serverID);
		return storageSupport.putServer(s);
	}

	/**
	 * Removes server from model by the server's unique identifier
	 * @param serverID server to remove from model
	 * @return true for success false for failure
	 */
	@Override
	public boolean deleteServer(String serverID) {
		
		// if manager is not logged in, exit operation
		if (!authenticated) {
			return false;
		}
		
		// delete server from storage
		return storageSupport.deleteServer(serverID);
	}

	/**
	 * Retrieves table information in formatted string
	 * @param tableNumber table to get information from
	 * @return table "in use" status and server assigned (null if operation fails)
	 */
	@Override
	public String getTableInfo(int tableNumber) {
		
		// get table from storage support
		Table t = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return null
		if(t == null)
		{
			return null;
		}
		
		// get and return table information
		return t.getTableInfo();
	}

	/**
	 * Change server assigned to a specific table
	 * @param tableNumber unique identifier for table to change server of
	 * @param newServerID unique identifier for server to assign to table
	 * @return true for success false for failure
	 */
	@Override
	public boolean changeTableServer(int tableNumber, String newServerID) {
		
		// get table from storage
		Table table = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return operation failure
		if (table == null) {
			return false;
		}
		
		// get server from storage
		Server newServer = storageSupport.getServer(newServerID);
		
		// if server doesn't exist, return operation failure
		if (newServer == null) {
			return false;
		}
		
		// get previous server from table instance and decrement their table count
		Server server = table.getServer();
		server.decrementTableCount();
		
		// assign new server to table and increment their table count
		table.setServer(newServer);
		newServer.incrementTableCount();
		
		// return successfully
		return true;
	}

	/**
	 * sets table to "in use" status representing a table with customers seated
	 * @param tableNumber unique identifier for table that is being set in use
	 * @param serverID unique identifier for server who will work table
	 * @return true for success false for failure
	 */
	@Override
	public boolean setTableToInUse(int tableNumber, String serverID) {
		
		// get table from storage
		Table table = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return operation failure
		if (table == null) {
			return false;
		}
		
		// get server from storage
		Server server = storageSupport.getServer(serverID);
		
		// if server doesn't exist, return operation failure
		if (server == null) {
			return false;
		}
		
		// set table to "in use" and return operation status
		return table.setToInUse(server);
	}

	/**
	 * sets table to "ready" status representing a table is ready to seat next customer
	 * @param tableNumber unique identifier for table that is to be set to ready
	 * @return true for operation success, false for failure
	 */
	@Override
	public boolean setTableToReady(int tableNumber) {
		
		// get table from storage
		Table table = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return operation failure
		if (table == null) {
			return false;
		}
		
		// set table to ready
		table.setToReady();
		
		//return successfully
		return true;
	}

	/**
	 * gets a formatted string listing the tables a server is assigned to
	 * @param serverID unique identifier for server to retreive list of tables for
	 * @return list of tables server is assigned to (null if operation fails)
	 */
	@Override
	public String getServerTables(String serverID) {
		
		// string to construct formatted return string
		String ret = "Server " + serverID + " tables: ";
		
		// get all tables from database
		ArrayList<Table> allTables = storageSupport.getAllTables();
		
		// loop through all tables
		for(Table t : allTables)
		{
			// get table's server
			Server s = t.getServer();
			if(s == null); // if table has no server than skip
			
			// if the server for the table is the same as the provided serverID then concatenate table onto string
			else if(s.getServerID().equals(serverID))
			{
				ret += t.getTableNumber() + " ";
			}
		}
		
		// return formatted string
		return ret;
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
		
		// get server from storage
		Server s = storageSupport.getServer(serverID);
		
		// if server doesn't exist return operation failure
		if (s == null)
		{
			return false;
		}
		
		// submit feedback for specified server
		return s.submitFeedback(feedback, good);
	}

	/**
	 * gets a formatted string containing server feedback
	 * @param serverID unique identifier for server to get feedback for
	 * @return formatted string containing server feedback (null if operation fails)
	 */
	@Override
	public String getServerFeedback(String serverID) {
		
		// if management isn't logged on, exit operation
		if (authenticated) {
			return null;
		}
		
		// get server from storage
		Server s = storageSupport.getServer(serverID);
		
		// if server doesn't exist, return null
		if(s==null)
			return null;
		
		// get and return feedback
		return s.getFeedback();
	}
	
	/**
	 * gets formatted string containing all servers and the number of tables they are working
	 * @param none
	 * @return formatted string containing all servers and table count (null if operation fails)
	 */
	@Override
	public String getServersAndNumberOfTables() {
		
		// get all servers from storage
		Collection<Server> servers = storageSupport.getServers();
		
		// initialize formatted return string
		String returnString = "";
		
		// loop through all servers and concatenate the number of tables they are servicing to return string
		for(Server server : servers) {
			returnString += "Server ID: " + server.getServerID() + "\nServicing: " + server.getTableCount() + " tables.\n\n";
		}
		
		// return formatted string
		return returnString;
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
	public boolean createOrder(int tableNumber, int drink, int appetizer, int meal, int side, String special) {
		
		// get table from storage
		Table t = storageSupport.getTable(tableNumber);
		
		// order's generated revenue
		int orderRevenue = 0;
		
		// if table doesn't exist, return operation failure
		if (t == null)
			return false;
		
		// update restaurant statistics for all ordered items
		restaurantStatistics.updateOrderID(++orderID);
		orderRevenue += restaurantStatistics.updateDrinkCount(drink, true);
		orderRevenue += restaurantStatistics.updateMealCount(meal, true);
		orderRevenue += restaurantStatistics.updateAppetizerCount(appetizer, true);
		orderRevenue += restaurantStatistics.updateSideCount(side, true);
		
		// update revenue in statistics
		restaurantStatistics.setRevenue(restaurantStatistics.getRevenue() + orderRevenue);
		
		// create new order
		Order o = new Order(orderID, drink, appetizer, meal, side, special);
		
		// put order into table's orders and return operation status
		return t.putOrder(o);
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
	public boolean modifyOrder(int tableNumber, int orderID, String field,
			String newvalue) {
		
		// get table from storage
		Table t =  storageSupport.getTable(tableNumber);
		
		// if table doesn't exist return operation failure
		if (t == null)
			return false;
		
		// get order from table
		Order o = t.getOrder(orderID);
		
		// if order doesn't exist return operation failure
		if (o == null)
			return false;
		
		// modify order, if operation fails, return operation failure
		if(o.modifyOrder(field, newvalue, restaurantStatistics) == false)
			return false;
		
		// put order back into table's orders
		return t.putOrder(o);
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
		
		// get table from storage
		Table t = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return operation failure
		if (t == null)
			return false;
		
		// delete order from table
		return t.deleteOrder(orderID);
	}
	
	/**
	 * obtains a list of orders (sorted by earliest creation timestamp)
	 * @param none
	 * @return array list of orders sorted by creation date
	 */
	@Override
	public ArrayList<Order> obtainOrderListByCreation()
	{
		// initialize return list of orders
		ArrayList<Order> ret = new ArrayList<Order>();
		
		// get all tables from storage
		ArrayList<Table> tables = storageSupport.getAllTables();
		
		// boolean flag to track if table has been added to list yet
		boolean added;
		
		// loop through all tables
		for (Table t : tables)
		{
			// get all orders for current table
			HashMap<Integer, Order> orders = t.getAllOrders();
			
			// loop through order entry set
			for (Entry<Integer, Order> entry : orders.entrySet())
			{
				// initialize added to false
				added = false;
				
				// get order from current hash map entry
				Order o = entry.getValue();
				
				// if status if "Ordered" then we need to add it to the list
				if(o.getStatus().equals("Ordered"))
				{
					// loop through list and find location to add order according to timestamp
					for (int i = 0; i<ret.size(); i++)
					{
						if (ret.get(i).getTimestamp() >= o.getTimestamp())
						{
							ret.add(i,o);
							added = true;
							break;
						}
					}
					if(!added)
						ret.add(o);
				}
			}
		}
		
		// return ordered list
		return ret;
	}

	/**
	 * gets formatted string containing tables checks
	 * @param tableNumber unique identifier for table to retrieve check information from
	 * @return formatted string containing all check information for specified table (null if operation fails)
	 */
	@Override
	public String getTablesChecks(int tableNumber) {
		
		// get table from storage
		Table t = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist return null
		if (t == null)
		{
			return null;
		}
		
		// get all checks from table
		ArrayList<Check> checks = t.getAllChecks();
		
		// intialize formatted return string
		String returnString = "";
		
		// initialize check counter variable at 1
		int count = 1;
		
		// for all checks concatenate check info to formatted return string
		for (Check check : checks) {
			returnString += "Check " + count + ":\n" + check.toString() + "\n";
			count++;
		}
		
		// return formatted return string containing all checks for table
		return returnString;
	}

	/**
	 * gets formatted string containing all tables orders
	 * @param tableNumber unique identifier for table to retrieve order information from
	 * @return formatted string containing all order information for specified table (null if operation fails)
	 */
	@Override
	public String getTablesOrders(int tableNumber) {
		
		// get table from storage
		Table t = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist return null
		if (t == null)
		{
			return null;
		}
		
		// get all orders for current table
		Collection<Order> orders = t.getAllOrders().values();
		
		// initialize formatted return string
		String returnString = "";
		
		// loop through all orders and concatenate order info to formatted string
		for (Order order : orders) {
			returnString += "Order: " + order.toString() + "\n";
		}
		
		// return formatted string containing table's orders
		return returnString;
	}

	/**
	 * uses table order information to generate monetary check objects
	 * @param tableNumber unique identifier for table to generate checks for
	 * @param orders list strings listing orders on a per check basis
	 * @return true if operation succeeds, false if operation fails
	 */
	@Override
	public boolean generateChecks(int tableNumber, ArrayList<String> orders) {
		
		// get table from storage
		Table t = storageSupport.getTable(tableNumber);
		
		// if table doesn't exist, return operation failure
		if(t==null)
			return false;
		
		// for each string of orders in provided list add check containing specified orders
		for(String oList : orders)
		{
			if(t.addCheck(oList) == false)
				return false; // if add check fails, return operation failure
		}
		
		// return successfully
		return true;
	}

	/**
	 * obtains the number of times a specific menu option has been ordered
	 * @param type integer representing the menu subsection (i.e. 1 represents drinks)
	 * @param itemIndex number correlating to menu option in specified subsection
	 * @return number of times menu option has been ordered (-1 if operation fails)
	 */
	@Override
	public int checkItemPopularity(int type, int itemIndex) {
		
		// if type == 1 (drinks) return drink item popularity
		if (type == 1)
		{
			return restaurantStatistics.getDrinkCount(itemIndex);
		}
		
		// if type == 2 (appetizer) return appetizer item popularity
		else if (type == 2)
		{
			return restaurantStatistics.getAppetizerCount(itemIndex);
		}
		
		// if type == 3 (meal) return meal item popularity
		else if (type == 3)
		{
			return restaurantStatistics.getMealCount(itemIndex);
		}
		
		// if type == 4 (side) return side item popularity
		else if (type == 4)
		{
			return restaurantStatistics.getSideCount(itemIndex);
		}
		
		// else return operation failure (-1)
		return -1;
	}
	
	/**
	 * Gets a list of tables which require immediate server attention.
	 * @param none
	 * @return list of tables that require server action
	 */
	@Override
	public ArrayList<Table> getTablesWithServerActionReqd()
	{
		// get all tables from storage
		ArrayList<Table> allTables = storageSupport.getAllTables();
		
		// initialize return list of tables
		ArrayList<Table> serverActionTables = new ArrayList<Table>();
		
		// loop through all tables to evaluate if server action is required
		for(Table t : allTables)
		{
			// pull orders and checks from table to check for server action required status
			HashMap<Integer, Order> orders = t.getAllOrders();
			
			// boolean flag to mark if table should be added to return list
			boolean addTable = true;
			
			// boolean flags to mark if the first order has either apps ready or meal ready
			boolean appsReady = false;
			boolean mealReady = false;
			
			// if table doesn't contain any orders, don't add it
			if(orders.isEmpty())
			{
				addTable = false;
			}
			
			// check if all orders are either appetizer ready or meal ready
			for (Entry<Integer, Order> entry : orders.entrySet())
			{
				// get order from hashtable entry
				Order o = entry.getValue();
				
				// if this is the first order and appetizers are ready then mark appsReady flag
				if(o.getStatus() == "Appetizers complete" && !appsReady && !mealReady)
				{
					appsReady = true;
				}
				
				// if this is the first order and meal is ready then mark mealReady flag
				else if(o.getStatus() == "Order complete" && !appsReady && !mealReady)
				{
					mealReady = true;
				}
				
				// if apps are ready and current order doesn't have apps ready then this table doesn't require
				// immediate action
				else if(o.getStatus() != "Appetizers complete" && appsReady)
				{
					// server action not required. break loop
					addTable = false;
					break;
				}
				
				// if meal is ready for some orders but current order doesn't have meal ready then this table doesn't
				// requirer immediate action
				else if(o.getStatus() == "Order complete" && mealReady)
				{
					// server action not required. break loop
					addTable = false;
					break;
				}
				
				// if this order is the first order and isn't app or meal complete, this table doesn't need server
				else if(o.getStatus() != "Appetizers Complete" && o.getStatus() != "Order complete" && !appsReady &&
						!mealReady)
				{
					addTable = false;
					break;
				}
				
				else
				{
					// continue
				}
			}
			
			// if all orders are requiring server action then table requires server action
			if(addTable)
			{
				serverActionTables.add(t);
			}
		}
		
		//return server action tables
		return serverActionTables;
	}
	
	/**
	 * gets the estimated wait time for next table
	 * @param none
	 * @return number of minutes until table will be available
	 */
	@Override
	public int getEstimatedWaitForNextTable()
	{
		// initialize return value
		int minutesToWait = -1;
		
		// track which table has been in use the longest (likely first done)
		long earliestTS = Long.MAX_VALUE;
		
		// get tables from storage
		ArrayList<Table> tables = storageSupport.getAllTables();
		
		// loop through all tables and find table likely to finish first
		for(Table t : tables)
		{
			if(!t.isInUse())
			{
				// if there is a table not in use then a table is already available so wait time is 0
				return 0;
			}
			
			// loop through orders keeping track of earliest time stamp and its table
			for(Order o : t.getAllOrders().values())
			{
				if(o.getTimestamp() < earliestTS && !o.getStatus().equals("Order complete"))
				{
					earliestTS = o.getTimestamp();
				}
			}
		}
		
		// calculate minutes to wait using average time tables are in use and waiting to be cleaned
		minutesToWait = ((restaurantStatistics.getAverageTimeInUse() + 
				restaurantStatistics.getAvarageTimeWaitingToBeCleaned()) - (int) ((System.currentTimeMillis() - 
				earliestTS) / 60000));
		
		// if minutes to wait is below 5 or negative, inform user it will be about 5 minutes
		if(minutesToWait < 5)
		{
			return 5;
		}
		
		// return successfully
		return minutesToWait;
	}

	/**
	 * get revenue earned to date
	 * @param none
	 * @return revenue to date in dollars
	 */
	@Override
	public int getRevenue()
	{
		return restaurantStatistics.getRevenue();
	}
	
	/**
	 * logon authentication for Restaurant Manager
	 * @param passcode password for manager account
	 * @return true if manager successfully logs in, false if operation fails
	 */
	@Override
	public boolean authenticate(String passcode) {
		
		// utilize storageSupport to authenticate
		authenticated = storageSupport.authenticatePasscode(passcode);
		
		// return authentication status
		return authenticated;
	}

	/**
	 * stores all restaurant data into text files as persistent data. Must be called by View on exit
	 * @param none
	 * @return void
	 */
	public void dumpToFile() {
		
		// utilize storage support to dump to file
		storageSupport.dumpToFile(restaurantStatistics);
	}

	/**
	 * gets a list of servers with feedback that is positive.
	 * @param none
	 * @return a list of servers with good feedback.
	 */
	public ArrayList<Server> getServersPositive() 
	{
		Collection<Server> servers = storageSupport.getServers();
		ArrayList<Server> goodservers = new ArrayList<Server>();
		// Goes through the list of all servers...
		for (Server s : servers)
		{
			// And checks to see if the size of the goodFeedback list is greater than or equal to 1.
			 if (s.goodFeedback.size() >= 1)
			 {
				 // Then adds the server to the list.
				 goodservers.add(s);
			 }
		}
		// Returns said list.
		return goodservers;
	}
	
	/**
	 * gets a list of servers with feedback that is negative.
	 * @param none
	 * @return a list of servers with bad feedback.
	 */
	public ArrayList<Server> getServersNegative() 
	{
		Collection<Server> servers = storageSupport.getServers();
		ArrayList<Server> badservers = new ArrayList<Server>();
		// Goes through the list of all servers...
		for (Server s : servers)
		{
			// And checks to see if the size of the badFeedback list is greater than or equal to 1.
			 if (s.badFeedback.size() >= 1)
			 {				
				 // Then adds the server to the list.
				 badservers.add(s);
			 }
		}
		// Returns said list.
		return badservers;
	}
}
