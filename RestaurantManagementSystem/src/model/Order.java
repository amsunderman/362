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
	
	public void modifyDrink(String newvalue)
	{
		drink = newvalue;
	}
	
	public void modifyAppetizer(String newvalue)
	{
		appetizer = newvalue;
	}
	
	public void modifyMeal(String newvalue)
	{
		meal = newvalue;
	}
	
	public void modifySide(String newvalue)
	{
		side = newvalue;
	}
	
	public void modifySpecial(String newvalue)
	{
		special = newvalue;
	}
	
	public void modifyStatus(String newvalue)
	{
		status = newvalue;
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
}
