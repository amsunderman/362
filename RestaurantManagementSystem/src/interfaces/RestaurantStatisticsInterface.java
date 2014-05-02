package interfaces;

public interface RestaurantStatisticsInterface {

	void setRevenue(int newRevenue);
	int getRevenue();
	void setAverageTimeReadyForUse(long averageTimeReadyForUse);
	void updateAverageTimeReadyForUse(long readyForUse);
	long getAverageTimeReadyForUse();
	void setAverageTimeInUse(long averageTimeInUse);
	void updateAverageTimeInUse(long inUse);
	long getAverageTimeInUse();
	boolean updateOrderID(int orderID);
	boolean updateTableCount(int tableCount);
	int getSideCount(int itemIndex);
	int getAppetizerCount(int itemIndex);
	int getMealCount(int itemIndex);
	int getDrinkCount(int itemIndex);
	int updateSideCount(int sideIndex, boolean up);
	int updateAppetizerCount(int appetizerIndex, boolean up);
	int updateMealCount(int mealIndex, boolean up);
	int updateDrinkCount(int drinkIndex, boolean up);
	boolean setSides(int[] sideCounts);
	boolean setMeal(int[] mealCounts);
	boolean setApps(int[] appCounts);
	boolean setDrinks(int[] drinkCounts);
	int getOrderID();
	int getTableCount();
	void setAverageTimeReadyForUseOccurrences(
			long averageTimeReadyForUseOccurrences);
	long getAverageTimeReadyForUseOccurrences();
	void setAverageTimeInUseOccurrences(long averageTimeInUseOccurrences);
	long getAverageTimeInUseOccurrences();

}
