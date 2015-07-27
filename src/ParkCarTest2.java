import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkCarTest2{

    ParkingLot p;
    TestParkingLotOwner TPLO = new TestParkingLotOwner();
    @Before
    public void setUp(){
        p = new ParkingLot(5,TPLO);
        Car c = new Car("MH07D1123","Honda City");
        Car c2 = new Car("MH07D1124","Honda CRV");
        p.park(c);
        p.park(c2);
    }

    @Test
    public void ableToParkCar() {
        Car c3 = new Car("MH07D1127","Toyota Corolla");
        assertEquals(3, p.park(c3));
    }

    @Test(expected = ParkingFullException.class)
    public void testForNoParking(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120","Toyota Camry"));
        p.park(new Car("MH08D1122", "Toyota Fortuner"));
    }

    @Test(expected = CarAlreadyExistsException.class)
    public void carAlreadyExists(){
        p.park(new Car("MH07D1123","Honda City"));
        p.park(new Car("MH07D1123", "Honda City"));
    }

    @Test
    public void removeCarWhenExists(){
        p.park(new Car("MH07D1125", "BMW"));
        int count = p.getCarToBeRemoved(2);
        assertEquals(2, count);
        Car car = p.removeCar(2);

    }

    @Test(expected = CarDoesntExistException.class)
    public void testExceptionIfUnparkOnNonExitanceCar(){
        p.park(new Car("MH07D1125", "BMW"));
        Car car = p.removeCar(5);
    }


    @Test
    public void testOwnerNotifiedWhenParkingIsFull(){
        p.park(new Car("MH07D1125", "BMW"));
        assertEquals(TPLO.isFull(), false);
        p.park(new Car("MH07D1126", "BMW"));
        assertEquals(TPLO.isFull(), false);
        p.park(new Car("MH08D1120", "Toyota Camry"));
        assertEquals(TPLO.isFull(), true);


    }



}