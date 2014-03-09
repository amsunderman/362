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
			while (scanner.hasNext()) {
				tableInfo = scanner.next();
				serverID = scanner.next();
				if (servers.get(serverID) != null && tableInfo != null) {
					table = new Table(servers.get(serverID), tableInfo);
					tables.add(table);
				}
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
		return tables.get(tableNumber);
	}

	public ArrayList<Table> getAllTables() {
		return tables;
	}

	public void deleteServer(String serverID) {
		servers.remove(serverID);
	}

	public boolean addServer(String serverID) {
		if (servers.get(serverID) != null) {
			return false;
		}
		Server server = new Server(serverID);
		servers.put(serverID, server);
		return true;
	}

}
