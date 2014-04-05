package model;

import java.util.HashMap;

import interfaces.TableInterface;

public class Table implements TableInterface {

	private int tableNumber;
	private Server server = null;
	//true if in use, false if ready for use
	private boolean inUse = false;
	private HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	
	public Table(int tableNumber, Server server) {
		this.tableNumber = tableNumber;
		setServer(server);
	}
	
	public Table(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	@Override
	public String getTableInfo() {
		String ret = "Table " + tableNumber + " info: ";
		if(inUse)
		{
			ret += "in use. ";
		}
		else
		{
			ret += "not in use. ";
		}
		if(server != null)
		{
			ret += "assigned to " + server.getServerID() + ".";
		}
		else
		{
			ret += "no one assigned.";
		}
		return ret;
	}

	@Override
	public Server getServer() {
		return server;
	}

	@Override
	public boolean setServer(Server newServer) {
		if (newServer == null) {
			return false;
		}
		server = newServer;
		return true;
	}

	@Override
	public boolean setToInUse(Server server) {
		if (server == null) {
			return false;
		}
		inUse = true;
		return setServer(server);
	}

	@Override
	public void setToReady() {
		server = null;
		inUse = false;
	}
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public boolean putOrder(Order o) {
		Integer ID = new Integer(o.getID());
		orders.put(ID,o);
		return true;
	}
	
	public Order getOrder(int orderID)
	{
		Integer ID = new Integer(orderID);
		if (orders.containsKey(ID))
		{
			return orders.get(ID);
		}
		else
			return null;
			
	}
}
