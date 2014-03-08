package controller;

import interfaces.RestaurantControllerInterface;
import model.Restaurant;

public class RestaurantController implements RestaurantControllerInterface{
	
	private Restaurant restaurant = null;
	
	public RestaurantController() {
		restaurant = new Restaurant();
	}

	@Override
	public boolean editTableCount(int newTableCount) {
		return restaurant.editTableCount(newTableCount);
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
		return restaurant.changeTableServer(tableNumber, newServerID);
	}

	@Override
	public boolean setTableToInUse(int tableNumber, String serverID) {
		return restaurant.setTableToInUse(tableNumber, serverID);
	}

	@Override
	public boolean setTableToReady(int tableNumber) {
		return restaurant.setTableToReady(tableNumber);
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
		return restaurant.authenticate(passcode);
	}
	
	public void dumpToFile() {
		restaurant.dumpToFile();
	}

}
