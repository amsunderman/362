package model;

import interfaces.RestaurantInterface;

public class Restaurant implements RestaurantInterface {

	@Override
	public boolean editTableCount(String passcode, int newTableCount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addServer(String passcode, String serverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteServer(String passcode, String serverID) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTableToInUse(int tableNumber, String serverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTableToReady(int tableNumber) {
		// TODO Auto-generated method stub
		return false;
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

}
