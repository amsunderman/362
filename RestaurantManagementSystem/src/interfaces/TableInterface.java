package interfaces;

import model.Server;

public interface TableInterface {
	String getTableInfo();
	Server getServer();
	boolean setServer(Server newServer);
	boolean setToInUse(Server server);
	void setToReady();
	
}
