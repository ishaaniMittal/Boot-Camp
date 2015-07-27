/**
 * Created by IShAani on 27-07-2015.
 */
public class TestFBIAgent implements ParkingLotObserver {

    private String name;

    public TestFBIAgent(String name){
        this.name = name;

    }


    private boolean full = false;
    private boolean notFull = false;

    @Override
    public boolean isFull() {
        return full;
    }

    @Override
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
        full = false;
        notFull = true;
    }
}