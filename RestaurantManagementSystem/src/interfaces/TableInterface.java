package interfaces;

import model.Server;

public interface TableInterface {
	String getTableInfo();
	Server getServer();
	boolean setServer(String newServerID);
	boolean setToInUse(String serverID);
	boolean setToReady();
	
}
