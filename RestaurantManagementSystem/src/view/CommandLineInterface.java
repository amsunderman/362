package view;

import javax.swing.JOptionPane;

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
				    " 17. Split a Table's Checks\n"+
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
				String serverID, passcode = null;
				int tableNumber = -1;
				
				switch(code) {
				//login
				case 1:
					if (authenticated) {
						System.out.println("You are already logged in");
					} else {
						while (!authenticated) {
							passcode =JOptionPane.showInputDialog("Enter Management Passcode");
							if(controller.authenticate(passcode)) {
								authenticated = true;
								System.out.println("Login successful");
							} else {
								System.out.println("Incorrect Passcode");
							}
						}
					}
					break;
					
				//edit Table Count
				case 2:
					int newTableCount=Integer.parseInt(JOptionPane.showInputDialog("Enter new table count"));
					b= controller.editTableCount(newTableCount);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//add server
				case 3:
					serverID =JOptionPane.showInputDialog("Enter serverID");
					b= controller.addServer(serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//delete server
				case 4:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.deleteServer(serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//check table information
				case 5:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String tableInfo = controller.getTableInfo(tableNumber);
					System.out.println(tableInfo);
					break;
					
				//change table's assigned server
				case 6:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String newServerID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.changeTableServer(tableNumber, newServerID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//set table to "in use"
				case 7:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.setTableToInUse(tableNumber, serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//set table to "ready"
				case 8:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					b= controller.setTableToReady(tableNumber);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//view a list of tables a server is assigned to
				case 9:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverTables = controller.getServerTables(serverID);
					System.out.println(serverTables);
					break;
					
				//submit server feedback
				case 10:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String feedback = JOptionPane.showInputDialog("Enter feedback");
					String goodFeedback=JOptionPane.showInputDialog("Enter if the feedback was good or bad \n"+
				    " 1. Good\n"+
				    " 2. Bad");
					b= controller.submitFeedback(serverID, feedback, Integer.parseInt(goodFeedback) == 1);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//check server feedback
				case 11:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverFeedback = controller.getServerFeedback(serverID);
					if (serverFeedback == null) {
						System.out.println("Server Feedback null. Please ensure you are logged in.");
					}
					System.out.println(serverFeedback);
					break;
					
				//view a list of server id's and the # of tables they are servicing
				case 12:
					
					break;
					
				//create an order
				case 13:
					
					break;
					
				//modify an order
				case 14:
					
					break;
					
				//delete an order
				case 15:
					
					break;
					
				//obtain a list of ordered sorted by creation
				case 16:
					
					break;
					
				//split a table's checks
				case 17:
					
					break;
					
				//obtain a list of checks for a table
				case 18:
					
					break;
					
				//obtain a list of orders for a table
				case 19:
					
					break;
					
				//view a list of tables with server action
				case 20:
					
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
					
					break;
					
				//view a list of servers with positive feedback
				case 27:
					
					break;
					
				//view a list of servers with negative feedback
				case 28:
					
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
