package model;

import interfaces.OrderInterface;

public class Order implements OrderInterface {
	private int orderID;
	private String drink;
	private String appetizer;
	private String meal;
	private String side;
	private String special;
	private long timestamp;
	private String status;
	
	public Order(int orderID, String drink, String appetizer, String meal, String side, String special)
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
	public boolean modifyOrder(String field, String newvalue)
	{
		if (field.equals("drink"))
			drink = newvalue;
		else if (field.equals("appetizer"))
			appetizer = newvalue;
		else if (field.equals("meal"))
			meal = newvalue;
		else if (field.equals("side"))
			side = newvalue;
		else if (field.equals("special"))
			special = newvalue;
		else if (field.equals("status"))
			if(newvalue.equals("Ordered") || newvalue.equals("Appetizers Complete") || newvalue.equals("Order Complete"))
				status = newvalue;
			else
				return false;
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
