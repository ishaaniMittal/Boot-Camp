/**
 * Created by IShAani on 27-07-2015.
 */
public class TestParkingLotOwner extends ParkingLotOwner {

   private boolean full = false;
    private boolean notFull = false;

    public boolean isFull() {
        return full;
    }

    public boolean isNotFull() {
        return notFull;
    }

    @Override
    public void onFull() {
        full = true;
        notFull = false;

    }

    @Override
    public void onVacancy() {
        notFull = true;
        full = false;
    }
}
