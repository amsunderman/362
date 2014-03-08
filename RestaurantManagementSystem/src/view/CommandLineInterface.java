package view;

import javax.swing.JOptionPane;

import controller.RestaurantController;

/*Test Commit Comment*/

public class CommandLineInterface {
	public static void main(String[] args) {
		RestaurantController controller = new RestaurantController();
		try {
			boolean loop = true;
			while (loop) {
				String operationNumber=JOptionPane.showInputDialog(
				    "Enter operation number \n"+
				    " 1. Edit Table Count\n"+
				    " 2. Add Server\n"+
				    " 3. Delete Server\n"+
				    " 4. Check Table Information\n"+
				    " 5. Change Table's Assigned Server\n"+
				    " 6. Set Table to In Use\n"+
				    " 7. Set Table to Ready for Use\n"+
				    " 8. View Tables a Server is Assigned to\n"+
				    " 9. Submit Server Feedback\n"+
				    " 10. Check Server Feedback\n"+
				    " 11. View a List of Servers and the Number of Tables They are Serving\n"+
				    " 12. Create an Order\n"+
				    " 13. Modify an Order\n"+
				    " 14. Delete an Order\n"+
				    " 15. View a List of Orders\n"+
				    " 16. Split a Table's Checks\n"+
				    " 17. Obtain a List of Checks for a Table\n"+
				    " 18. Obtain a List of Orders for a Table\n"+
				    " 19. View a List of Tables with Server Immediate Action\n"+
				    " 20. Get Expected Wait Time For Next Available Table\n"+
				    " 21. Obtain Average Time Table is Waiting to be Cleaned\n"+
				    " 22. Obtain Average Time Table is In Use\n"+
				    " 23. Obtain Average Time Table is Ready For Use\n"+
				    " 24. Obtain a Revenue Report\n"+
				    " 25. View Popularity of Menu Items\n"+
				    " 26. View a List of Servers with Positive Feedback\n"+
				    " 27. View a List of Servers with Negative Feedback\n"+
				    " 28. Exit");
				
				//variables for upcoming switch statement
				int code = Integer.parseInt(operationNumber);
				boolean b;
				String serverID = null;
				int tableNumber = -1;
				
				switch(code) {
				//edit Table Count
				case 1:
					int newTableCount=Integer.parseInt(JOptionPane.showInputDialog("Enter new table count"));
					b= controller.editTableCount(newTableCount);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//add server
				case 2:
					serverID =JOptionPane.showInputDialog("Enter serverID");
					b= controller.addServer(serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//delete server
				case 3:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.deleteServer(serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//check table information
				case 4:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String tableInfo = controller.getTableInfo(tableNumber);
					// TODO output tableInfo in desired way
					break;
					
				//change table's assigned server
				case 5:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					String newServerID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.changeTableServer(tableNumber, newServerID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//set table to "in use"
				case 6:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					serverID = JOptionPane.showInputDialog("Enter serverID");
					b= controller.setTableToInUse(tableNumber, serverID);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//set table to "ready"
				case 7:
					tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Number"));
					b= controller.setTableToReady(tableNumber);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//view a list of tables a server is assigned to
				case 8:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverTables = controller.getServerTables(serverID);
					// TODO output the server's tables in the desired way
					break;
					
				//submit server feedback
				case 9:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String feedback = JOptionPane.showInputDialog("Enter feedback");
					String goodFeedback=JOptionPane.showInputDialog("Enter if the feedback was good or bad \n"+
				    " 1. Good\n"+
				    " 2. Bad");
					b= controller.submitFeedback(serverID, feedback, Integer.parseInt(goodFeedback) == 1);
					System.out.println("Operation success boolean is "+b);
					break;
					
				//check server feedback
				case 10:
					serverID = JOptionPane.showInputDialog("Enter serverID");
					String serverFeedback = controller.getServerFeedback(serverID);
					// TODO output the server's feedback in the desired way
					break;
					
				//view a list of server id's and the # of tables they are servicing
				case 11:
					
					break;
					
				//create an order
				case 12:
					
					break;
					
				//modify an order
				case 13:
					
					break;
					
				//delete an order
				case 14:
					
					break;
					
				//obtain a list of ordered sorted by creation
				case 15:
					
					break;
					
				//split a table's checks
				case 16:
					
					break;
					
				//obtain a list of checks for a table
				case 17:
					
					break;
					
				//obtain a list of orders for a table
				case 18:
					
					break;
					
				//view a list of tables with server action
				case 19:
					
					break;
					
				//get expected wait time for next table
				case 20:
					
					break;
					
				//obtain average time a table is waiting to be cleaned
				case 21:
					
					break;
					
				//obtain average time a table is in use
				case 22:
					
					break;
					
				//obtain average time a table is ready for use
				case 23:
					
					break;
					
				//obtain a revenue report for a specific date
				case 24:
					
					break;
					
				//view popularity of menu items
				case 25:
					
					break;
					
				//view a list of servers with positive feedback
				case 26:
					
					break;
					
				//view a list of servers with negative feedback
				case 27:
					
					break;
					
				//exit
				case 28:
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
