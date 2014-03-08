package model;

import interfaces.TableInterface;

public class Table implements TableInterface {

	private Server server = null;
	//true if in use, false if ready for use
	private boolean inUse;
	
	public Table(Server server, String tableInfo) {
		setServer(server);
		setTableInfo(tableInfo);
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

	private void setTableInfo(String tableInfo) {
		// TODO Auto-generated method stub
		
	}

}
