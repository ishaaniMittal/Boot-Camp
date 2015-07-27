import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLot {

    private int totalCapacity ;
    private int currentCount;
    Map<Integer,ParkCar> mapper = new HashMap<Integer,ParkCar>();


    public ParkingLot(int capacity){
        this.totalCapacity = capacity;
    }
    public int park(ParkCar c){
        if(mapper.containsValue(c))
            throw new CarAlreadyExistsException("Car Already Exists");

        if(currentCount==totalCapacity)
            throw new ParkingFullException("Parking Full");


        mapper.put(currentCount,c);
        return currentCount++;


    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
