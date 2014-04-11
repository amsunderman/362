package interfaces;

public interface OrderInterface {
    boolean modifyOrder(String field, String newvalue);
    void modifyTimeStamp(long timestamp);
    int getID();
    long getTimestamp();
    String getStatus();
}
