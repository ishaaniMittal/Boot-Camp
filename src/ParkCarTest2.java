import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkCarTest2 {

    @Test
    public void ableToParkCar() {
        ParkCar c = new ParkCar("MH07D1123","Honda City");
        ParkingLot p = new ParkingLot(2);
        ParkCar c2 = new ParkCar("MH07D1124","Honda CRV");
        assertEquals(0,p.park(c));
        assertEquals(1,p.park(c2));

    }

    @Test(expected = ParkingFullException.class)
    public void noParking(){

        ParkingLot p = new ParkingLot(2);
        p.park(new ParkCar("MH07D1123","Honda City"));
        p.park(new ParkCar("MH07D1124","Honda CRV"));
        p.park(new ParkCar("MH07D1125","BMW"));
    }
}