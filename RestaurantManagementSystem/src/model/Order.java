package model;

import interfaces.OrderInterface;

/**
 * Model object representing the collection of menu items ordered by a specific customer
 * @author Team 3
 *
 */
public class Order implements OrderInterface {
	
	/**
	 * unique identifier for order instance
	 */
	private int orderID;
	
	/**
	 * integer representing the drink menu item ordered
	 */
	private int drink;
	
	/**
	 * integer representing the appetizer menu item ordered
	 */
	private int appetizer;
	
	/**
	 * integer representing the meal menu item ordered
	 */
	private int meal;
	
	/**
	 * integer representing the side menu item ordered
	 */
	private int side;
	
	/**
	 * string that adds any additional special order request (i.e. "steak medium rare")
	 */
	private String special;
	
	/**
	 * timestamp of order creation
	 */
	private long timestamp;
	
	/**
	 * order status: "Ordered", "Appetizers Complete", or "Order Complete"
	 */
	private String status;

	/**
	 * Initialization constructor for order. Status defaults to "Ordered"
	 * @param orderID unique order identifier
	 * @param drink ordered drink
	 * @param appetizer ordered appetizer
	 * @param meal ordered meal
	 * @param side ordered side
	 * @param special special requests
	 */
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
	
	/**
	 * modifies an order by modifying the specified field with the given newvalue parameter. updates given 
	 * RestaurantStatistics object
	 * @param field field identifier for which field needs modified
	 * @param newvalue new data to insert into specified field
	 * @param stats RestaurantStatistics object to update accordingly
	 * @return true for operation success, false for failure
	 */
	@Override
	public boolean modifyOrder(String field, String newvalue, RestaurantStatistics stats)
	{
		// if field equals drink then update drink and correlating stats
		if (field.equals("drink"))
		{
			int drinkIndex = Integer.parseInt(newvalue);
			stats.updateDrinkCount(drinkIndex, true);
			stats.updateDrinkCount(drink, false);
			drink = drinkIndex;
		}
		// if field equals appetizer update appetizer and correlating stats
		else if (field.equals("appetizer"))
		{
			int appetizerIndex = Integer.parseInt(newvalue);
			stats.updateAppetizerCount(appetizerIndex, true);
			stats.updateAppetizerCount(appetizer, false);
			appetizer = appetizerIndex;
		}
		// if field equals meal then update meal and correlating stats
		else if (field.equals("meal"))
		{
			int mealIndex = Integer.parseInt(newvalue);
			stats.updateMealCount(mealIndex, true);
			stats.updateMealCount(meal, false);
			meal = mealIndex;
		}
		// if field equals side then update side and correlating stats
		else if (field.equals("side"))
		{
			int sideIndex = Integer.parseInt(newvalue);
			stats.updateSideCount(sideIndex, true);
			stats.updateSideCount(side, false);
			side = sideIndex;
		}
		// if field equals special then update special requests
		else if (field.equals("special"))
			special = newvalue;
		// if field equals status then update status
		else if (field.equals("status"))
		{
			if(newvalue.equals("Ordered") || newvalue.equals("Appetizers Complete") || newvalue.equals("Order Complete"))
				status = newvalue;
			else
				return false; // if status is invalid, return operation failure
		}
		// if field is invalid return operation failure
		else
			return false;
		
		// return successfully
		return true;
	}
	
	/**
	 * get order's unique identifier
	 * @param none
	 * @return unique identifier for order instance
	 */
	public int getID()
	{
		return orderID;
	}

	/**
	 * get order's timestamp
	 * @param none
	 * @return timestamp for order instance (long)
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * get order's status
	 * @param none
	 * @return status of order instance
	 */
	public String getStatus()
	{
		return status;
	}
	
	/**
	 * get formatted string representing order
	 * @param none
	 * @return formatted string containing ordered menu items
	 */
	public String toString()
	{
		// initialize formatted return string
		String ret = "";
		
		// concatenate order information
		ret += orderID + ":\ndrink: " + drink + "\n" + 
				"appetizer: " + appetizer + "\n" + "meal: " + meal + "\n" +
				"side: " + side + "\n" + "special: " + special + "\n" + 
				"status: " + status + "\n" + "timestamp: " + timestamp + "\n";
		
		// return formatted string
		return ret;
	}

	/**
	 * modify time stamp (should be called if order goes back to "Ordered" status after incorrect order)
	 * @param none
	 * @return void
	 */
	@Override
	public void modifyTimeStamp(long timestamp) {
		this.timestamp = System.currentTimeMillis();
	}
}
