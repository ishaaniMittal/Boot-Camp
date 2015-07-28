/**
 * Created by IShAani on 28-07-2015.
 */
public class TestParkingLotObserver implements ParkingLotObserver {

    NotificationTypesForObserver notify = null;

    @Override
    public void notify(NotificationTypesForObserver notification) {

      this.notify = notification;


    }
}
