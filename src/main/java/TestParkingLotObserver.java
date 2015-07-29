/**
 * Created by IShAani on 28-07-2015.
 */
public class TestParkingLotObserver implements ParkingLotObserver {

    private boolean notify = false;



    @Override
    public void notificationHandler(INotificationForParkingLot notification) {
        this.notify = true;

    }

    public boolean isNotificationHandlerCalled(){
        return notify;
    }

}
