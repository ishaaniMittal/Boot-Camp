/**
 * Created by IShAani on 28-07-2015.
 */
public class NotificationForPark implements INotificationForParkingLot {

    private int currentOccupancy;
    private int capacity;


    public NotificationForPark(int currentOccupancy, int capacity) {
        this.currentOccupancy = currentOccupancy;
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return this.currentOccupancy;
    }
}
