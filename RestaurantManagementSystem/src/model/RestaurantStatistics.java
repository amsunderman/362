package model;

public class RestaurantStatistics {
	private int tableCount = -1;

	public int getTableCount() {
		return tableCount;
	}

	public boolean updateTableCount(int tableCount) {
		if (tableCount < 0) {
			return false;
		}
		this.tableCount = tableCount;
		return true;
	}
	
}
