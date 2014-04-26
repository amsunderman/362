package model;

import interfaces.OrderInterface;

public class Order implements OrderInterface {
	private int orderID;
	private int drink;
	private int appetizer;
	private int meal;
	private int side;
	private String special;
	private long timestamp;
	private String status;
	
	public Order(int orderID, int drink, int appetizer, int meal, int side, String special)
	{
		this.orderID = orderID;
		this.drink = drink;
		this.appetizer = appetizer;
		this.meal = meal;
		this.side = side;
		this.special = special;
		timestamp = System.currentTimeMillis();
		status = "Ordered";
	}
	
	@Override
	public boolean modifyOrder(String field, String newvalue, RestaurantStatistics stats)
	{
		if (field.equals("drink"))
		{
			int drinkIndex = Integer.parseInt(newvalue);
			stats.updateDrinkCount(drinkIndex, true);
			stats.updateDrinkCount(drink, false);
			drink = drinkIndex;
		}
		else if (field.equals("appetizer"))
		{
			int appetizerIndex = Integer.parseInt(newvalue);
			stats.updateAppetizerCount(appetizerIndex, true);
			stats.updateAppetizerCount(appetizer, false);
			appetizer = appetizerIndex;
		}
		else if (field.equals("meal"))
		{
			int mealIndex = Integer.parseInt(newvalue);
			stats.updateMealCount(mealIndex, true);
			stats.updateMealCount(meal, false);
			meal = mealIndex;
		}
		else if (field.equals("side"))
		{
			int sideIndex = Integer.parseInt(newvalue);
			stats.updateSideCount(sideIndex, true);
			stats.updateSideCount(side, false);
			side = sideIndex;
		}
		else if (field.equals("special"))
			special = newvalue;
		else if (field.equals("status"))
		{
			if(newvalue.equals("Ordered") || newvalue.equals("Appetizers Complete") || newvalue.equals("Order Complete"))
				status = newvalue;
			else
				return false;
		}
		else
			return false;
		
		return true;
	}
	
	public int getID()
	{
		return orderID;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public String toString()
	{
		String ret = "";
		
		ret += orderID + ":\ndrink: " + drink + "\n" + 
				"appetizer: " + appetizer + "\n" + "meal: " + meal + "\n" +
				"side: " + side + "\n" + "special: " + special + "\n" + 
				"status: " + status + "\n" + "timestamp: " + timestamp + "\n";
		return ret;
	}

	@Override
	public void modifyTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
