import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLot{


    private  List<ParkingLotObserver> viewers = new ArrayList<ParkingLotObserver>();



   // private TestParkingLotOwner PLO;
    private int totalCapacity ;
    private int currentCount = 0;
    Map<Integer, Car> mapper = new HashMap<Integer, Car>();


    public ParkingLot(int capacity,ParkingLotObserver owner){
        this.totalCapacity = capacity;
        subscribe(owner);
       // this.viewers = viewers;

    }

    public int park(Car c){
        if(mapper.containsValue(c))
            throw new CarAlreadyExistsException("Car Already Exists");

       if(parkingFull()){
           throw new ParkingFullException("Parking is FUll");
       }

        mapper.put(currentCount, c);
        ++currentCount;
        if(parkingFull()) {

            for(ParkingLotObserver a : viewers)
                a.onFull();
              //  a.notifyTheObserver(NotificationTypesForObserver.PARKING_FULL);
        }
       return currentCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }


    public int getCarToBeRemoved(int i) {

        if (!mapper.containsKey(i))
            throw new CarDoesntExistException("Car Doesnt Exist");
        else {
            currentCount--;
            return currentCount;

        }

    }

    public Car removeCar(int i){
        if (!mapper.containsKey(i))
            throw new CarDoesntExistException("Car Doesnt Exist");
        else {
            Car c = mapper.get(i);
            mapper.remove(i);

            for(ParkingLotObserver a : viewers)
                    a.onVacancy();
                //a.notifyTheObserver(NotificationTypesForObserver.PARKING_AVAILABLE);

            return c;
        }
    }

    public boolean parkingFull(){
        if(currentCount==totalCapacity) {
            return true;
        }
        return false;
    }

    public void subscribe(ParkingLotObserver obv){
        viewers.add(obv);
    }

    public void unregister(ParkingLotObserver obv){ viewers.remove(obv);}


}
