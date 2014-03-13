package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Storage {
	
	//management passcode move to statistics?
	private String managementPasscode = "password";
	//stores restaurants servers
	private HashMap<String, Server> servers = new HashMap<String, Server>();
	//stores restaurants tables
	private ArrayList<Table> tables = new ArrayList<Table>();
	//constants for data populate and dump.
	private static final String SERVER_FILE_CONSTANT = "restaurant_server_data.txt";
	private static final String TABLE_FILE_CONSTANT = "restaurant_table_data.txt";
	private static final String STATISTICS_FILE_CONSTANT = "restaurant_statistics_data.txt";
	private static final String FILE_TYPE_CONSTANT = "UTF-8";
	
	public Storage(RestaurantStatistics restaurantStatistics) {
		populate(restaurantStatistics);
	}
	
	/**
	 * fills servers, tables and restaurant statistics with data from the txt files.
	 * @param restaurantStatistics
	 */
	private void populate(RestaurantStatistics restaurantStatistics) {
		//fills servers
		File file = new File(SERVER_FILE_CONSTANT);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			Server server;
			while(scanner.hasNext()) {
				server = new Server(scanner.nextLine());
				server.setTableCount(Integer.parseInt(scanner.nextLine()));
				String fb = scanner.nextLine();
				while(!fb.contains("</GOOD>"))
				{
					if(fb.contains("<GOOD>"))
						fb = scanner.nextLine();
					else
					{
						server.submitFeedback(fb, true);
						fb = scanner.nextLine();
					}
				}
				fb = scanner.nextLine();
				while(!fb.contains("</BAD>"))
				{
					if(fb.contains("<BAD>"))
						fb = scanner.nextLine();
					else
					{
						server.submitFeedback(fb, false);
						fb = scanner.nextLine();
					}
				}
				servers.put(server.getServerID(), server);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error in server data file read");
			//uncomment if you want to see stack trace for file not existing
//			e.printStackTrace();
		}
		
		//fills tables
		file = new File(TABLE_FILE_CONSTANT);
		try {
			scanner = new Scanner(file);
			Table table;
			String serverID, tableInfo;
			int tableCount = 1;
			while (scanner.hasNext()) {
				tableInfo = scanner.nextLine();
				serverID = scanner.nextLine();
				table = new Table(tableCount, servers.get(serverID));
				if(tableInfo.contains(": in use."))
				{
					table.setToInUse(servers.get(serverID));
				}
				tables.add(table);
				tableCount++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error in table data file read");
			//uncomment if you want to see stack trace for file not existing
//			e.printStackTrace();
		}
		
		//fills statistics
		file = new File(STATISTICS_FILE_CONSTANT);
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				restaurantStatistics.updateTableCount(Integer.parseInt(scanner.next()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error in statistics data file read");
			//uncomment if you want to see stack trace for file not existing
//			e.printStackTrace();
		}
	}

	/**
	 * dumps all restaurant data to be saved to 3 different text files.
	 * @param restaurantStatistics
	 */
	public void dumpToFile(RestaurantStatistics restaurantStatistics) {
		//writes server information to file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(SERVER_FILE_CONSTANT, FILE_TYPE_CONSTANT);
		} catch (Exception e) {
			System.out.println("writer create error for server file");
		}
		for (Server server : servers.values()) {
			writer.println(server.getServerID());
			writer.println(server.getTableCount());
			writer.println("<GOOD>");
			for(String fb : server.goodFeedback)
			{
				writer.println(fb);
			}
			writer.println("</GOOD>");
			writer.println("<BAD>");
			for(String fb : server.badFeedback)
			{
				writer.println(fb);
			}
			writer.println("</BAD>");
		}
		writer.close();
		
		//writes table information to file
		try {
			writer = new PrintWriter(TABLE_FILE_CONSTANT, FILE_TYPE_CONSTANT);
		} catch (Exception e) {
			System.out.println("writer create error for table file");
		}
		for (Table table : tables) {
			if (table.getTableInfo() == null) {
				writer.println("_");
			} else {
				writer.println(table.getTableInfo());
			}
			if (table.getServer() == null) {
				writer.println("_");
			} else {
				writer.println(table.getServer().getServerID());
			}
		}
		writer.close();
		
		//writes statistics to file
		try {
			writer = new PrintWriter(STATISTICS_FILE_CONSTANT, FILE_TYPE_CONSTANT);
		} catch (Exception e) {
			System.out.println("writer create error for statistics file");
		}
		writer.println(restaurantStatistics.getTableCount());
		writer.close();
	}

	public String getPasscode() {
		return managementPasscode;
	}

	public Server getServer(String serverID) {
		return servers.get(serverID);
	}

	public Table getTable(int tableNumber) {
		if (tableNumber < 0 || tableNumber > tables.size()) {
			return null;
		}
		return tables.get(tableNumber-1);
	}

	public ArrayList<Table> getAllTables() {
		return tables;
	}

	public boolean deleteServer(String serverID) {
		if(!servers.containsKey(serverID))
			return false;
		servers.remove(serverID);
		return true;
	}

	public boolean putServer(Server s) {
		if (servers.get(s.getServerID()) != null) {
			return false;
		}
		servers.put(s.getServerID(), s);
		return true;
	}
	
	public boolean putTable(Table table) {
		if (tables.size()+1 != table.getTableNumber()) {
			return false;
		}
		tables.add(table);
		return true;
	}
	
	public boolean deleteTable(int tableNumber) {
		if (tables.size() < tableNumber) {
			return false;
		}
		tables.remove(tableNumber-1);
		return true;
	}

}
