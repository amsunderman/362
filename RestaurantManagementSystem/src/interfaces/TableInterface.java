package interfaces;

import java.util.Set;

import model.Check;
import model.Server;

public interface TableInterface {
	String getTableInfo();
	Server getServer();
	boolean setServer(Server newServer);
	boolean setToInUse(Server server);
	void setToReady();
	Set<Check> getAllChecks();
}
