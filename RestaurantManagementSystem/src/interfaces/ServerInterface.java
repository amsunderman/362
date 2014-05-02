package interfaces;

public interface ServerInterface {
	boolean decrementTableCount();
	boolean submitFeedback(String feedback, boolean good);
	String getFeedback();
	String getServerID();
	int getTableCount();
	void incrementTableCount();
	void setTableCount(int tableCount);
}
