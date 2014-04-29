package view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Order;
import model.Server;
import model.Table;
import controller.RestaurantController;

public class CommandLineInterface {
	public static void main(String[] args) {
		RestaurantController controller = new RestaurantController();
		try {
			boolean loop = true;
			boolean authenticated = false;
			while (loop) {
				String operationNumber=JOptionPane.showInputDialog(
				    "Enter operation number \n"+
				    " 1. Manager Login"+
				    " 2. Edit Table Count\n"+
				    " 3. Add Server\n"+
				    " 4. Delete Server\n"+
				    " 5. Check Table Information\n"+
				    " 6. Change Table's Assigned Server\n"+
				    " 7. Set Table to In Use\n"+
				    " 8. Set Table to Ready for Use\n"+
				    " 9. View Tables a Server is Assigned to\n"+
				    " 10. Submit Server Feedback\n"+
				    " 11. Check Server Feedback\n"+
				    " 12. View a List of Servers and the Number of Tables They are Serving\n"+
				    " 13. Create an Order\n"+
				    " 14. Modify an Order\n"+
				    " 15. Delete an Order\n"+
				    " 16. View a List of Orders\n"+
				    " 17. Generate Table's Checks\n"+
				    " 18. Obtain a List of Checks for a Table\n"+
				    " 19. Obtain a List of Orders for a Table\n"+
				    " 20. View a List of Tables with Server Immediate Action\n"+
				    " 21. Get Expected Wait Time For Next Available Table\n"+
				    " 22. Obtain Average Time Table is Waiting to be Cleaned\n"+
				    " 23. Obtain Average Time Table is In Use\n"+
				    " 24. Obtain Average Time Table is Ready For Use\n"+
				    " 25. Obtain a Revenue Report\n"+
				    " 26. View Popularity of Menu Items\n"+
				    " 27. View a List of Servers with Positive Feedback\n"+
				    " 28. View a List of Servers with Negative Feedback\n"+
				    " 29. Exit");
				
				//variables for upcoming switch statement
				int code = Integer.parseInt(operationNumber);
				boolean b;
				String serverID, passcode, special, field, newData = null, ret = null;
				int drink, appetizer, meal, side, tableNumber, orderID, numChecks = -1;
				ArrayList<Order> orders = new ArrayList<Order>();
				ArrayList<String> orderLists;
				
				switch(code) {
				//login
				case 1:
					if (authenticated) {
						JOptionPane.showMessageDialog(null, "You are already logged in");
					} else {
						while (!authenticated) {
							passcode =JOptionPane.showInputDialog("Enter Management Passcode");
							if(controller.authenticate(passcode)) {
								authenticated = true;
								JOptionPane.showMessageDialog(null, "Login successful");
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect Passcode");
							}
						}
					}
					break;
					
				//edit Table Count
				case 2:
					int newTableCount=Integer.parseInt(JOptionPane.showInputDialog("Enter new table count"));
					b= controller.editTableCount(newTableCount);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//add server
				case 3:
					serverID =JOptionPane.showInputDialog("Enter serverID");
					b= controller.addServer(serverID);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//delete server
				case 4:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.deleteServer(serverID);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//check table information
				case 5:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String tableInfo = controller.getTableInfo(tableNumber);
					JOptionPane.showMessageDialog(null, tableInfo);
					break;
					
				//change table's assigned server
				case 6:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String newServerID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.changeTableServer(tableNumber, newServerID);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//set table to "in use"
				case 7:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.setTableToInUse(tableNumber, serverID);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//set table to "ready"
				case 8:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					b= controller.setTableToReady(tableNumber);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//view a list of tables a server is assigned to
				case 9:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverTables = controller.getServerTables(serverID);
					JOptionPane.showMessageDialog(null, serverTables);
					break;
					
				//submit server feedback
				case 10:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String feedback = JOptionPane.showInputDialog("Enter feedback");
					String goodFeedback=JOptionPane.showInputDialog("Enter if the feedback was good or bad \n"+
				    " 1. Good\n"+
				    " 2. Bad");
					b= controller.submitFeedback(serverID, feedback, Integer.parseInt(goodFeedback) == 1);
					JOptionPane.showMessageDialog(null, "Operation success boolean is "+b);
					break;
					
				//check server feedback
				case 11:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverFeedback = controller.getServerFeedback(serverID);
					if (serverFeedback == null) {
						JOptionPane.showMessageDialog(null, "Server Feedback null. Please ensure you are logged in.");
					}
					JOptionPane.showMessageDialog(null, serverFeedback);
					break;
					
				//view a list of server id's and the # of tables they are servicing
				case 12:
					String serverList = controller.getServersAndNumberOfTables();
					JOptionPane.showMessageDialog(null, serverList);
					break;
					
				//create an order
				case 13:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					drink = Integer.parseInt(JOptionPane.showInputDialog("Enter drink \n"+
							" 1. Drink A\n"+
							" 2. Drink B\n"+
							" 3. Drink C\n"
							));
					appetizer = Integer.parseInt(JOptionPane.showInputDialog("Enter appetizer \n"+
							" 1. Appetizer A\n"+
							" 2. Appetizer B\n"+
							" 3. Appetizer C\n"
							));
					meal = Integer.parseInt(JOptionPane.showInputDialog("Enter meal \n"+
							" 1. Meal A\n"+
							" 2. Meal B\n"+
							" 3. Meal C\n"							
							));
					side = Integer.parseInt(JOptionPane.showInputDialog("Enter side \n"+
							" 1. Side A\n"+
							" 2. Side B\n"+
							" 3. Side C\n"
							));
					special = JOptionPane.showInputDialog("Enter special requests");
					b = controller.createOrder(tableNumber, drink, appetizer, meal, side, special);
					JOptionPane.showMessageDialog(null, "Operation Success boolean is " + b);
					break;
					
				//modify an order
				case 14:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					orderID = Integer.parseInt(JOptionPane.showInputDialog("Enter OrderID"));
					operationNumber=JOptionPane.showInputDialog(
						    "Enter field number to modify \n"+
						    " 1. Drink\n"+
						    " 2. Appetizer\n"+
						    " 3. Meal\n"+
						    " 4. Side\n"+
						    " 5. Special Requests\n"+
						    " 6. Status\n");
					code = Integer.parseInt(operationNumber);
					if(code == 1)
					{
						field = "drink";
						newData = (JOptionPane.showInputDialog("Enter drink \n"+
							" 1. Drink A\n"+
							" 2. Drink B\n"+
							" 3. Drink C\n"
							));
					}
					else if(code == 2)
					{
						field = "appetizer";
						newData = (JOptionPane.showInputDialog("Enter appetizer \n"+
								" 1. Appetizer A\n"+
								" 2. Appetizer B\n"+
								" 3. Appetizer C\n"
								));
					}
					else if(code == 3)
					{
						field = "meal";
						newData = (JOptionPane.showInputDialog("Enter meal \n"+
								" 1. Meal A\n"+
								" 2. Meal B\n"+
								" 3. Meal C\n"							
								));
					}
					else if(code == 4)
					{
						field = "side";
						newData = (JOptionPane.showInputDialog("Enter side \n"+
								" 1. Side A\n"+
								" 2. Side B\n"+
								" 3. Side C\n"
								));
					}
					else if(code == 5)
					{
						field = "special";
						newData = JOptionPane.showInputDialog("Enter special requests");
					}
					else if(code == 6)
					{
						field = "status";
						newData = (JOptionPane.showInputDialog(
								" 1. Ordered\n"+
								" 2. Appetizer complete\n"+
								" 3. Order complete\n"
								));
					}
					else
						field = "";
					b = controller.modifyOrder(tableNumber, orderID, field, newData);
					JOptionPane.showMessageDialog(null, "Operation Success boolean is " + b);
					break;
					
				//delete an order
				case 15:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					orderID = Integer.parseInt(JOptionPane.showInputDialog("Enter OrderID"));
					b = controller.deleteOrder(tableNumber, orderID);
					JOptionPane.showMessageDialog(null, "Operation Success boolean is " + b);
					break;
					
				//obtain a list of ordered sorted by creation
				case 16:
					ret = "";
					orders = controller.obtainOrderListByCreation();
					for(Order o : orders)
					{
						ret += o.toString();
					}
					JOptionPane.showMessageDialog(null, ret);
					break;
					
					
				//split a table's checks
				case 17:
					orderLists = new ArrayList<String>();
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					numChecks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of checks"));
					for(int i = 0; i < numChecks; i++)
					{
						orderLists.add(JOptionPane.showInputDialog("Enter orders for check " + (i+1)));
					}
					b = controller.generateChecks(tableNumber, orderLists);
					JOptionPane.showMessageDialog(null, "Operation Success boolean is " + b);
					break;
					
				//obtain a list of checks for a table
				case 18:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					String tableChecks = controller.getTablesChecks(tableNumber);
					JOptionPane.showMessageDialog(null, tableChecks);
					break;
					
				//obtain a list of orders for a table
				case 19:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter table number"));
					String tableOrders = controller.getTablesOrders(tableNumber);
					JOptionPane.showMessageDialog(null, tableOrders);
					break;
					
				//view a list of tables with server action
				case 20:
					ArrayList<Table> serverActionTables = controller.getTablesWithServerActionReqd();
					String serverAction = "Tables that need server attention include:\n";
					for(Table t : serverActionTables)
					{
						serverAction += "Table " + t.getTableNumber() + "\n";
					}
					JOptionPane.showMessageDialog(null, serverAction);
					break;
					
				//get expected wait time for next table
				case 21:
					
					break;
					
				//obtain average time a table is waiting to be cleaned
				case 22:
					
					break;
					
				//obtain average time a table is in use
				case 23:
					
					break;
					
				//obtain average time a table is ready for use
				case 24:
					
					break;
					
				//obtain a revenue report for a specific date
				case 25:
					
					break;
					
				//view popularity of menu items
				case 26:
					int type;
					int count = -1;
					String mealType=JOptionPane.showInputDialog(
						    "Enter field number to modify \n"+
						    " 1. Drink\n"+
						    " 2. Appetizer\n"+
						    " 3. Meal\n"+
						    " 4. Side\n"
						    );
					type = Integer.parseInt(mealType);
					if (type==1)
					{
						drink = Integer.parseInt(JOptionPane.showInputDialog("Enter drink \n"+
								" 1. Drink A\n"+
								" 2. Drink B\n"+
								" 3. Drink C\n"
								));
						count = controller.checkItemPopularity(type, drink);
					}
					else if (type==2)
					{
						appetizer = Integer.parseInt(JOptionPane.showInputDialog("Enter appetizer \n"+
								" 1. Appetizer A\n"+
								" 2. Appetizer B\n"+
								" 3. Appetizer C\n"
								));
						count = controller.checkItemPopularity(type, appetizer);
					}
					else if (type==3)
					{
						meal = Integer.parseInt(JOptionPane.showInputDialog("Enter meal \n"+
								" 1. Meal A\n"+
								" 2. Meal B\n"+
								" 3. Meal C\n"							
								));
						count = controller.checkItemPopularity(type, meal);
					}
					else if (type==4)
					{
						side = Integer.parseInt(JOptionPane.showInputDialog("Enter side \n"+
								" 1. Side A\n"+
								" 2. Side B\n"+
								" 3. Side C\n"
								));
						count = controller.checkItemPopularity(type, side);
					}
					if(count == -1)
					{
						JOptionPane.showMessageDialog(null, "Operation failed");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "This item has been ordered " + count + " times");
					}
					break;
					
				//view a list of servers with positive feedback
				case 27:
					ArrayList<Server> goodservers = controller.getServersPositive();
					String goodList = "Servers with good feedback: \n";
					for (Server s : goodservers)
					{
						goodList += s.getServerID() + "\n";
					}
					JOptionPane.showMessageDialog(null, goodList);
					break;
					
				//view a list of servers with negative feedback
				case 28:
					ArrayList<Server> badservers = controller.getServersNegative();
					String badList = "Servers with bad feedback: \n";
					for (Server s : badservers)
					{
						badList += s.getServerID() + "\n";
					}
					JOptionPane.showMessageDialog(null, badList);
					break;
					
				//exit
				case 29:
					loop = false;
					controller.dumpToFile();
				default:
					break;
				}
			}
		} catch (Exception e){
			controller.dumpToFile();
		}

	}
}
