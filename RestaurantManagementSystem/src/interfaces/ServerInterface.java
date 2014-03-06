package interfaces;

public interface ServerInterface {
	boolean decrementServerTableCount();
	boolean submitFeedback(String feedback, boolean good);
	String getFeedback();
}
