/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLotOwner implements ParkingLotObserver{
    private String name;

    public ParkingLotOwner(String name){
        this.name = name;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isNotFull() {
        return false;
    }

    public void onFull(){

    }

    public void onVacancy(){

    }
}
