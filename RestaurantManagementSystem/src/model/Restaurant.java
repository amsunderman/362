package model;

import java.util.ArrayList;
import interfaces.RestaurantInterface;

public class Restaurant implements RestaurantInterface {
	
	private StorageSupport storageSupport = null;
	private RestaurantStatistics restaurantStatistics = null;
	
	public Restaurant() {
		restaurantStatistics = new RestaurantStatistics();
		//passes restaurantStatistics for initial populate from file
		storageSupport = new StorageSupport(restaurantStatistics);
	}
	
	
	@Override
	public boolean editTableCount(int newTableCount) {
		if (newTableCount < 0) {
			return false;
		}
		int oldTableCount = restaurantStatistics.getTableCount();
		if (oldTableCount == -1) {
			for (int i = 1; i <= newTableCount; i++) {
				if(!storageSupport.addTable(new Table(i))) {
					return false;
				}
			}
		}
		else if (oldTableCount != newTableCount && restaurantStatistics.updateTableCount(newTableCount)) {
			if (newTableCount > oldTableCount) {
				//add extra tables
				while (newTableCount != oldTableCount) {
					if(!storageSupport.addTable(new Table(oldTableCount+1))) {
						return false;
					}
					oldTableCount++;
				}
			} else {
				while (newTableCount != oldTableCount) {
					if(!storageSupport.deleteTable(oldTableCount)) {
						return false;
					}
					oldTableCount--;
				}
			}
		}
		restaurantStatistics.updateTableCount(newTableCount);
		return true;
	}

	@Override
	public boolean addServer(String serverID) {
		Server s = new Server(serverID);
		return storageSupport.addServer(s);
	}

	@Override
	public boolean deleteServer(String serverID) {
		return storageSupport.deleteServer(serverID);
	}

	@Override
	public String getTableInfo(int tableNumber) {
		Table t = storageSupport.getTable(tableNumber);
		if(t == null)
		{
			return null;
		}
		return t.getTableInfo();
	}

	@Override
	public boolean changeTableServer(int tableNumber, String newServerID) {
		Table table = storageSupport.getTable(tableNumber);
		if (table == null) {
			return false;
		}
		Server newServer = storageSupport.getServer(newServerID);
		if (newServer == null) {
			return false;
		}
		Server server = table.getServer();
		server.decrementServerTableCount();
		table.setServer(newServer);
		newServer.incrementTableCount();
		return true;
	}

	@Override
	public boolean setTableToInUse(int tableNumber, String serverID) {
		Table table = storageSupport.getTable(tableNumber);
		if (table == null) {
			return false;
		}
		Server server = storageSupport.getServer(serverID);
		return table.setToInUse(server);
	}

	@Override
	public boolean setTableToReady(int tableNumber) {
		Table table = storageSupport.getTable(tableNumber);
		if (table == null) {
			return false;
		}
		table.setToReady();
		return true;
	}

	@Override
	public String getServerTables(String serverID) {
		String ret = "";
		ArrayList<Table> allTables = storageSupport.getAllTables();
		for(Table t : allTables)
		{
			Server s = t.getServer();
			if(s.getServerID().equals(serverID))
			{
				ret.concat(t.getTableInfo() + " ");
			}
		}
		return ret;
	}

	@Override
	public boolean submitFeedback(String serverID, String feedback, boolean good) {
		Server s = storageSupport.getServer(serverID);
		return s.submitFeedback(feedback, good);
	}

	@Override
	public String getServerFeedback(String serverID) {
		Server s = storageSupport.getServer(serverID);
		return s.getFeedback();
	}

	@Override
	public boolean authenticate(String passcode) {
		return storageSupport.authenticatePasscode(passcode);
	}


	public void dumpToFile() {
		storageSupport.dumpToFile(restaurantStatistics);
	}

}
