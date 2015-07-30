/**
 * Created by IShAani on 28-07-2015.
 */
public class NotificationForPark implements INotificationForParkingLot {

    private int currentOccupancy;
    private int capacity;
    private String lotName;


    public NotificationForPark(int currentOccupancy, int capacity,String lotName) {
        this.currentOccupancy = currentOccupancy;
        this.capacity = capacity;
        this.lotName = lotName;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return this.currentOccupancy;
    }

    @Override
    public String getLotName() {
        return lotName;
    }
}
