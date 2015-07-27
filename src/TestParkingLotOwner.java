/**
 * Created by IShAani on 27-07-2015.
 */
public class TestParkingLotOwner extends ParkingLotOwner {

   private boolean full = false;

    public boolean isFull() {
        return full;
    }

    @Override
    public void onFull() {
        full = true;
    }
}
