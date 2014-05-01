package model;

public class RestaurantStatistics {
	private int drink[] = new int[3];
	private int meal[] = new int[3];
	private int appetizer[] = new int[3];
	private int side[] = new int[3];
	private int drinkPrices[] = {1, 2, 3};
	private int mealPrices[] = {10, 20, 30};
	private int appetizerPrices[] = {3, 6, 9};
	private int sidePrices[] = {2, 4, 6};
	private int tableCount = -1;
	private int orderID = -1;
	private int averageTimeInUse = 20;
	private int averageTimeReadyForUse = 10;
	private int avarageTimeWaitingToBeCleaned = 5;
	private int revenue = 0;

	public int getTableCount() {
		return tableCount;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public boolean setDrinks(int drinkCounts[])
	{
		if (drinkCounts.length != 3)
			return false;
		for(int i = 0; i < drink.length; i++)
		{
			drink[i] = drinkCounts[i];
		}
		return true;
	}
	
	public boolean setApps(int appCounts[])
	{
		if (appCounts.length != 3)
			return false;
		for(int i = 0; i < appetizer.length; i++)
		{
			appetizer[i] = appCounts[i];
		}
		return true;
	}
	
	public boolean setMeal(int mealCounts[])
	{
		if (mealCounts.length != 3)
			return false;
		for(int i = 0; i < meal.length; i++)
		{
			meal[i] = mealCounts[i];
		}
		return true;
	}
	
	public boolean setSides(int sideCounts[])
	{
		if (sideCounts.length != 3)
			return false;
		for(int i = 0; i < side.length; i++)
		{
			side[i] = sideCounts[i];
		}
		return true;
	}

	public int updateDrinkCount(int drinkIndex, boolean up){
		if (drinkIndex <= 3 && drinkIndex >= 1)
		{
			if(up)
				drink[drinkIndex-1]++;
			else
				drink[drinkIndex-1]--;
			return drinkPrices[drinkIndex-1];
		}
		return -1;
	}
	
	public int updateMealCount(int mealIndex, boolean up){
		if (mealIndex <= 3 && mealIndex >= 1)
		{
			if(up)
				meal[mealIndex-1]++;
			else
				meal[mealIndex-1]--;
			return mealPrices[mealIndex-1];
		}
		return -1;
	}
	
	public int updateAppetizerCount(int appetizerIndex, boolean up){
		if (appetizerIndex <= 3 && appetizerIndex >= 1)
		{
			if(up)
				appetizer[appetizerIndex-1]++;
			else
				appetizer[appetizerIndex-1]--;
			return appetizerPrices[appetizerIndex-1];
		}
		return -1;
	}
	
	public int updateSideCount(int sideIndex, boolean up){
		if (sideIndex <= 3 && sideIndex >= 1)
		{
			if(up)
				side[sideIndex-1]++;
			else
				side[sideIndex-1]--;
			return sidePrices[sideIndex-1];
		}
		return -1;
	}
	
	public int getDrinkCount(int itemIndex) {
		return drink[itemIndex-1];
	}
	
	public int getMealCount(int itemIndex) {
		return meal[itemIndex-1];
	}
	
	public int getAppetizerCount(int itemIndex) {
		return appetizer[itemIndex-1];
	}
	
	public int getSideCount(int itemIndex) {
		return side[itemIndex-1];
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

	public int getAverageTimeInUse() {
		return averageTimeInUse;
	}

	public void setAverageTimeInUse(int averageTimeInUse) {
		this.averageTimeInUse = averageTimeInUse;
	}

	public int getAverageTimeReadyForUse() {
		return averageTimeReadyForUse;
	}

	public void setAverageTimeReadyForUse(int averageTimeReadyForUse) {
		this.averageTimeReadyForUse = averageTimeReadyForUse;
	}

	public int getAvarageTimeWaitingToBeCleaned() {
		return avarageTimeWaitingToBeCleaned;
	}

	public void setAvarageTimeWaitingToBeCleaned(
			int avarageTimeWaitingToBeCleaned) {
		this.avarageTimeWaitingToBeCleaned = avarageTimeWaitingToBeCleaned;
	}
	
	public int getRevenue()
	{
		return revenue;
	}
	
	public void setRevenue(int newRevenue)
	{
		revenue = newRevenue;
	}
	
}
