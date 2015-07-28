import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IShAani on 27-07-2015.
 */


public class TestClass {

    ParkingLot p;
    List<TestParkingLotObserver> viewers = new ArrayList<>();
    TestParkingLotObserver owner = new TestParkingLotObserver();
    TestParkingLotObserver agent = new TestParkingLotObserver();
    TestParkingLotObserver agent2 = new TestParkingLotObserver();

    @Before
    public void setUp(){


        p = new ParkingLot(5,owner);

        p.subscribe(agent);
        p.subscribe(agent2);
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
        Car car = p.removeCar(2);

    }

    @Test(expected = CarDoesntExistException.class)
    public void testExceptionIfUnparkOnNonExitanceCar(){
        p.park(new Car("MH07D1125", "BMW"));
        Car car = p.removeCar(5);
    }


   /* @Test
    public void testOwnerNotifiedWhenParkingIsFull(){
        p.park(new Car("MH07D1125", "BMW"));
        assertEquals(owner.isFull(), false);
        p.park(new Car("MH07D1126", "BMW"));
        assertEquals(owner.isFull(), false);
        p.park(new Car("MH08D1120", "Toyota Camry"));
        assertEquals(owner.isFull(), true);
    }

    @Test
    public void testOwnerNotifiedWhenParkingIsAvailableAgain(){
        p.park(new Car("MH07D1125", "BMW"));
        int count = p.getCarToBeRemoved(2);
        assertEquals(2, count);
        Car car = p.removeCar(2);
        assertEquals(owner.isNotFull(),true);
    }

    @Test
    public void checkIfParkingFullStatusIsGoneAfterTheParkingIsAvailable(){
        p.park(new Car("MH07D1125", "BMW"));
        int count = p.getCarToBeRemoved(2);
        assertEquals(2, count);
        Car car = p.removeCar(2);
        assertEquals(owner.isFull(),false);
    }*/


    @Test
    public void testObserverNotifiedWhenParkingIsFull(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120", "Toyota Camry"));
        assertEquals(NotificationTypesForObserver.PARKING_FULL, agent.notify);
        assertEquals(NotificationTypesForObserver.PARKING_FULL, agent2.notify);
        assertEquals(NotificationTypesForObserver.PARKING_FULL, owner.notify);
    }


    @Test
    public void testObserverNotifiedWhenParkingIsAvailableAgain(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120","Toyota Camry"));
        Car car = p.removeCar(2);
        assertEquals(NotificationTypesForObserver.PARKING_AVAILABLE, owner.notify);
        assertEquals(NotificationTypesForObserver.PARKING_AVAILABLE, agent.notify);
        assertEquals(NotificationTypesForObserver.PARKING_AVAILABLE, agent2.notify);

    }


    @Test
    public void testAgentNotifiedAt80PercentFullParking(){

    }


}