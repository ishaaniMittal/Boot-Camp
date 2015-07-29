import java.util.List;

/**
 * Created by IShAani on 29-07-2015.
 */
public class MaximumEmptySelectionStrategy implements SelectionStrategy {



    public ParkingLot apply(List<ParkingLot> parkingLot) {
        float max  = 0f;
        ParkingLot temp = new ParkingLot();
        for(ParkingLot p : parkingLot) {
            if(p.percentageEmpty() > max)
            {
                max = p.percentageEmpty();
                temp = p;
            }
        }

        return temp;
    }



}
