import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IShAani on 27-07-2015.
 */


public class TestClass {

    ParkingLot p,p2,p3;
    private  List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    List<TestParkingLotObserver> viewers = new ArrayList<>();
    TestParkingLotObserver owner = new TestParkingLotObserver();
    TestParkingLotObserver agent = new TestParkingLotObserver();
    TestParkingLotObserver agent2 = new TestParkingLotObserver();

    List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    SubscriberStrategy strategy = new SubscriberStrategy() {
        @Override
        public boolean apply(INotificationForParkingLot notification) {
            if (notification instanceof NotificationForPark) {
                NotificationForPark notfn = (NotificationForPark) notification;
                if (notfn.getCapacity() == notfn.getCurrentOccupancy())
                    return true;
            } else if (notification instanceof NotificationForUnpark) {
                NotificationForUnpark notfn = (NotificationForUnpark) notification;
                if (notfn.getCurrentOccupancy() == notfn.getCapacity() - 1)
                    return true;

            }
            return false;
        }
    };

    Attendant attendant;

    @Before
    public void setUp(){

        p = new ParkingLot("lot1",5,owner);
        p2 = new ParkingLot("lot2" ,5,owner);
        p3 = new ParkingLot("lot3",5,owner);
        parkingLots.add(p);
        parkingLots.add(p2);
        parkingLots.add(p3);
        attendant = new Attendant(parkingLots);
        p.subscribe(attendant, strategy);
        p2.subscribe(attendant,strategy);
        p3.subscribe(attendant,strategy);
        p.subscribe(agent, new SubscriberStrategy() {
            @Override
            public boolean apply(INotificationForParkingLot notification) {
                if(notification instanceof NotificationForPark) {
                    NotificationForPark notfn = (NotificationForPark)notification;
                    if((double)notfn.getCurrentOccupancy() / (double)notfn.getCapacity() == 0.8D) {
                        return true;
                    }
                }

                if(notification instanceof NotificationForUnpark) {
                    NotificationForUnpark notfn1 = (NotificationForUnpark)notification;
                    if((double)notfn1.getCurrentOccupancy() / (double)notfn1.getCapacity() == 0.8D) {
                        return true;
                    }
                }

                return false;
            }

        });
        Car c = new Car("MH07D1123","Honda City");
        Car c2 = new Car("MH07D1124","Honda CRV");
        p.park(c);
        p.park(c2);
    }

    @Test
    public void TestIfAttendantIsNotifiedOnPark(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120", "Toyota Camry"));
        assertTrue(owner.isNotificationHandlerCalled());
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


    @Test
    public void testObserverNotifiedWhenParkingIsAvailableAgain(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120","Toyota Camry"));
        Car car = p.removeCar(2);
        assertTrue(owner.isNotificationHandlerCalled());
    }

    @Test
    public void testObserverNotifiedWhenParkingIsFull(){
        p.park(new Car("MH07D1125", "BMW"));
        p.park(new Car("MH07D1126", "BMW"));
        p.park(new Car("MH08D1120", "Toyota Camry"));
        assertTrue(owner.isNotificationHandlerCalled());

    }

    @Test
    public void TestIfMultipleObserversAreNotified() {
        p.park(new Car("MH12D1234","FIGO"));
        p.park(new Car("MH12D1236", "Swift"));
        p.park(new Car("MH12D1239", "Verna"));
        p.removeCar(4);
        p.removeCar(3);
        org.junit.Assert.assertTrue(agent.isNotificationHandlerCalled());
    }


    @Test
    public void attendantAbleToDirectCarForPark(){
        p.park(new Car("MH12D1234","FIGO"));
        p.park(new Car("MH12D1236", "Swift"));
        p.park(new Car("MH12D1239", "Verna"));
       assertEquals(true, attendant.parkCar(new Car("MH12D3456", "Nano")).equals("lot2-1"));
    }

    @Test
    public void attendantAbleToUnParkTheCar(){
        p.park(new Car("MH12D1234","FIGO"));
        p.park(new Car("MH12D1236", "Swift"));
        p.park(new Car("MH12D1239", "Verna"));
        System.out.println(attendant.unparkCar("lot1-3"));
    }






}