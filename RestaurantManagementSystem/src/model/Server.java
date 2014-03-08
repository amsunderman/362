package model;

import interfaces.ServerInterface;

public class Server implements ServerInterface {

	private String serverID;
	private int tableCount = 0;
	
	public Server(String serverID) {
		this.serverID = serverID;
	}

	@Override
	public void incrementTableCount() {
		tableCount++;
	}
	
	@Override
	public boolean decrementServerTableCount() {
		if (tableCount <= 0) {
			return false;
		}
		tableCount--;
		return true;
	}

	@Override
	public boolean submitFeedback(String feedback, boolean good) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerID() {
		return serverID;
	}

	@Override
	public int getTableCount() {
		return tableCount;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;		
	}

}
