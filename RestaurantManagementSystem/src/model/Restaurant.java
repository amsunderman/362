package model;

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
		return restaurantStatistics.updateTableCount(newTableCount);
	}

	@Override
	public boolean addServer(String serverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteServer(String serverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTableInfo(int tableNumber) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean submitFeedback(String serverID, String feedback, boolean good) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getServerFeedback(String serverID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(String passcode) {
		return storageSupport.authenticatePasscode(passcode);
	}


	public void dumpToFile() {
		storageSupport.dumpToFile(restaurantStatistics);
	}

}
