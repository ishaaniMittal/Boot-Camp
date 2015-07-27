import java.util.HashMap;
import java.util.Map;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLot {

    private int totalCapacity ;
    private int currentCount = 0;
    Map<Integer, Car> mapper = new HashMap<Integer, Car>();


    public ParkingLot(int capacity){
        this.totalCapacity = capacity;
    }

    public int park(Car c){
        if(mapper.containsValue(c))
            throw new CarAlreadyExistsException("Car Already Exists");

        if(currentCount==totalCapacity)
            throw new ParkingFullException("Parking Full");


        mapper.put(currentCount,c);
        return ++currentCount;


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
            return c;
        }
    }


}
