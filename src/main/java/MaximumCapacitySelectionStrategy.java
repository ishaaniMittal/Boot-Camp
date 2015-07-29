import java.util.List;

/**
 * Created by IShAani on 29-07-2015.
 */
public class MaximumCapacitySelectionStrategy implements SelectionStrategy {

    public ParkingLot apply(List<ParkingLot> parkingLot) {
            float max = 0f;
            ParkingLot temp = new ParkingLot();
            for(ParkingLot p : parkingLot) {
                if(p.getTotalCapacity() > max)
                {
                    max = p.getTotalCapacity();
                    temp = p;
                }
            }
            return temp;
    }

}
