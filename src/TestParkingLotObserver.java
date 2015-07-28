/**
 * Created by IShAani on 28-07-2015.
 */
public class TestParkingLotObserver implements ParkingLotObserver {


    private boolean full = false;
    private boolean notFull =false;

    public boolean isNotFull() {
        return notFull;
    }

    public boolean isFull() {
        return full;
    }

    @Override
    public void onFull() {

    }

    @Override
    public void onVacancy() {

    }


   /* public void notifyTheObserver(Enum<NotificationTypesForObserver> notification) {

        if(notification.equals(NotificationTypesForObserver.PARKING_AVAILABLE)){

        }

        else(notification.equals(NotificationTypesForObserver.PARKING_FULL)){

        }

    }*/
}
