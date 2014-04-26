package interfaces;

import model.RestaurantStatistics;

public interface OrderInterface {
    boolean modifyOrder(String field, String newvalue, RestaurantStatistics stats);
    void modifyTimeStamp(long timestamp);
    int getID();
    long getTimestamp();
    String getStatus();
}
