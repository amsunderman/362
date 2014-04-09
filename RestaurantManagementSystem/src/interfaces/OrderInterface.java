package interfaces;

public interface OrderInterface {
    void modifyDrink(String newValue);
    void modifyAppetizer(String newValue);
    void modifyMeal(String newValue);
    void modifySide(String newValue);
    void modifySpecial(String newValue);
    void modifyStatus(String newValue);
    void modifyTimeStamp(long timestamp);
    int getID();
    long getTimestamp();
    String getStatus();
}
