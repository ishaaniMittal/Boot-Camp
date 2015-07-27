import java.util.HashMap;
import java.util.Map;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLot {

    private int totalCapacity ;
    private int currentCount;
    Map<ParkCar,Integer> mapper = new HashMap<ParkCar,Integer>();


    public ParkingLot(int capacity){
        this.totalCapacity = capacity;
    }
    public int park(ParkCar c){
        if(currentCount==totalCapacity){
            throw new ParkingFullException("Parking Full"); }

                mapper.put(c, currentCount);
                return currentCount++;


    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
