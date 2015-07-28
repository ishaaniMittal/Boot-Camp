/**
 * Created by IShAani on 28-07-2015.
 */
public class NotificationForUnpark implements INotificationForParkingLot {
    private int currentOccupancy;
    private int capacity;


    public NotificationForUnpark(int currentOccupancy, int capacity) {
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
