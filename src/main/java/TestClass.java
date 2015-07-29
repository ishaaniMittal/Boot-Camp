import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by IShAani on 27-07-2015.
 */


public class TestClass {

    ParkingLot p,p2,p3;
    private  List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    List<TestParkingLotObserver> viewers = new ArrayList<TestParkingLotObserver>();
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
                if (notfn.getCurrentOccupancy() == notfn.getCapacity()-1)
                    return true;

            }
            return false;
        }
    };

    Attendant attendant;

    @Before
    public void setUp(){

        p = new ParkingLot("lot1",5,owner);
        p2 = new ParkingLot("lot2" ,16,owner);
        p3 = new ParkingLot("lot3",18,owner);
        parkingLots.add(p);
        parkingLots.add(p2);
        parkingLots.add(p3);
        SelectionStrategy selectionStrategy = new MaximumCapacitySelectionStrategy();
        SelectionStrategy selectionStrategy2 = new MaximumEmptySelectionStrategy();

        attendant = new Attendant(parkingLots, selectionStrategy2);

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
      // assertEquals(true, attendant.parkCar(new Car("MH12D3456", "Nano")).equals("lot2-1"));
        /*System.out.println(attendant.parkCar(new Car("MH12D3456", "Nano")));
        System.out.println(attendant.parkCar(new Car("MH12D3458", "Vento")));*/
    }

    @Test
    public void attendantAbleToUnParkTheCar(){
        p.park(new Car("MH12D1234", "FIGO"));
        p.park(new Car("MH12D1236", "Swift"));
        p.park(new Car("MH12D1239", "Verna"));
        attendant.unparkCar(new Token("lot1", 4));
    }


    @Test
    public void attendantAbleToParkCarInMaximumEmptySlot(){
        p.park(new Car("MH12D1234","FIGO"));
        p.park(new Car("MH12D1236", "Swift"));
        p.park(new Car("MH12D1239", "Verna"));
        p2.park(new Car("MH13B7890", "Xylo"));
        p2.park(new Car("MH67G9807", "ZYS"));
        p3.park(new Car("LK98H9087", "QWER"));
        System.out.println(p.percentageEmpty());
        System.out.println(p2.percentageEmpty());
        System.out.println(p3.percentageEmpty());
        System.out.println(attendant.parkCar(new Car("MH12D3456", "Nano")));

       //assertEquals(true,attendant.parkCar(new Car("MH12D3456", "Nano")).equals(new Token("lot3",2)));
    }

    @Test
    public void attendantAbleToParkCarInMaximumCapacitySlotUsingMockito(){
        List<ParkingLot> pL = new ArrayList<ParkingLot>();
        SelectionStrategy strategy = mock(MaximumCapacitySelectionStrategy.class);
        ParkingLot mockedParking = mock(ParkingLot.class);
        when(mockedParking.getName()).thenReturn("lot3");
        when(strategy.apply(pL)).thenReturn(mockedParking);
        pL.add(mockedParking);
        Attendant attendant_new = new Attendant(pL,strategy);
        when(mockedParking.park(new Car("MH03F3456", "Indica"))).thenReturn(1);
        assertEquals(new Token("lot3", 1), attendant_new.parkCar(new Car("MH03F3456", "Indica")));
    }



}