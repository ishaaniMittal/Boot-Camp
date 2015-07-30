import java.util.List;

/**
 * Created by IShAani on 29-07-2015.
 */
public interface SelectionStrategy {

    public ParkingLot apply(List<ParkingLot> parkingLot);
}
