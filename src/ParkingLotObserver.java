/**
 * Created by IShAani on 27-07-2015.
 */
public interface ParkingLotObserver {


    public boolean isFull();
    public boolean isNotFull();
    public void onFull();
    public void onVacancy();
}
