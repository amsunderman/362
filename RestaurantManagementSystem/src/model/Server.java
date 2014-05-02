package model;

import interfaces.ServerInterface;

import java.util.ArrayList;

public class Server implements ServerInterface {

	private String serverID;
	private int tableCount = 0;
	public ArrayList<String> goodFeedback = new ArrayList<String>();
	public ArrayList<String> badFeedback = new ArrayList<String>();
	
	
	public Server(String serverID) {
		this.serverID = serverID;
	}

	@Override
	public void incrementTableCount() {
		tableCount++;
	}
	
	@Override
	public boolean decrementTableCount() {
		if (tableCount <= 0) {
			return false;
		}
		tableCount--;
		return true;
	}

	@Override
	public boolean submitFeedback(String feedback, boolean good) {
		if(good == true)
			return goodFeedback.add(feedback);
		else
			return badFeedback.add(feedback);
	}

	@Override
	public String getFeedback() {
		String ret = "Server " + serverID + " feedback:\n";
		ret += "Good: \n";
		for(int i=0; i<goodFeedback.size(); i++)
		{
			ret += (i+1) + ". " + goodFeedback.get(i) + "\n";
		}
		
		ret += "Bad: \n";
		
		for(int i=0; i<badFeedback.size(); i++)
		{
			ret += (i+1) + ". " + badFeedback.get(i);
			if(i != badFeedback.size() - 1)
			{
				ret += "\n";
			}
		}
				
		return ret;
	}

	@Override
	public String getServerID() {
		return serverID;
	}

	@Override
	public int getTableCount() {
		return tableCount;
	}

	@Override
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;		
	}

}
