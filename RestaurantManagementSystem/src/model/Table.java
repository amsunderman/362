package model;

import interfaces.TableInterface;

public class Table implements TableInterface {

	private int tableNumber;
	private Server server = null;
	//true if in use, false if ready for use
	private boolean inUse = false;
	
	public Table(int tableNumber, Server server, String tableInfo) {
		this.tableNumber = tableNumber;
		setServer(server);
		setTableInfo(tableInfo);
	}
	
	public Table(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	@Override
	public String getTableInfo() {
		// TODO Auto-generated method stub
		return null;
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

	private void setTableInfo(String tableInfo) {
		// TODO Auto-generated method stub
		
	}
	
}
