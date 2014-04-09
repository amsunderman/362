package model;

public class RestaurantStatistics {
	private int tableCount = -1;
	private int orderID = -1;

	public int getTableCount() {
		return tableCount;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public boolean updateTableCount(int tableCount) {
		if (tableCount < 0) {
			return false;
		}
		this.tableCount = tableCount;
		return true;
	}
	
	public boolean updateOrderID(int orderID) {
		this.orderID = orderID;
		return true;
	}
	
}
