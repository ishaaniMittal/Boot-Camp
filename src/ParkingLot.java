import java.util.*;

/**
 * Created by IShAani on 27-07-2015.
 */
public class ParkingLot{

    private String name;


    public String getName() {
        return name;
    }

    private  List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    Map<ParkingLotObserver, SubscriberStrategy> strategyMap = new HashMap<>();


   // private TestParkingLotOwner PLO;
    private int totalCapacity ;
    private int currentCount = 0;
    Map<Integer, Car> mapper = new HashMap<Integer, Car>();


    public ParkingLot(String name,int capacity,ParkingLotObserver owner){
        this.name = name;
        this.totalCapacity = capacity;
        subscribe(owner, new SubscriberStrategy() {
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
        });


    }

    public int park(Car c){
        if(mapper.containsValue(c))
            throw new CarAlreadyExistsException("Car Already Exists");

       else if(parkingFull()){
           throw new ParkingFullException("Parking is FUll");
       }
    else {
            mapper.put(currentCount, c);
            ++currentCount;
            this.notifyObserver(new NotificationForPark(this.mapper.size(), this.totalCapacity,this.name));
            return currentCount;
        }
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }


    public int getCarToBeRemoved(int i) {

        if (!mapper.containsKey(i))
            throw new CarDoesntExistException("Car Doesnt Exist");
        else {
            currentCount--;
            return currentCount;

        }

    }

    public Car removeCar(int i){
        if (!mapper.containsKey(i))
            throw new CarDoesntExistException("Car Doesnt Exist");
        else {
             this.notifyObserver(new NotificationForUnpark(this.mapper.size(), this.totalCapacity,this.name));

            Car c = mapper.get(i);
            mapper.remove(i);
            currentCount--;
            return c;
        }
    }

    public boolean parkingFull(){
        if(currentCount==totalCapacity) {
            return true;
        }
        return false;
    }

    public void subscribe(ParkingLotObserver obv, SubscriberStrategy s){
        observers.add(obv);
        strategyMap.put(obv,s);
    }

    public void unregister(ParkingLotObserver obv){
        observers.remove(obv);
        strategyMap.remove(obv);
    }


    public void notifyObserver(INotificationForParkingLot notification){
        Iterator var2 = this.strategyMap.keySet().iterator();
        while(var2.hasNext()) {
            ParkingLotObserver observer = (ParkingLotObserver)var2.next();
            if(((SubscriberStrategy)this.strategyMap.get(observer)).apply(notification)) {
                observer.notificationHandler(notification);
            }
        }
    }

}
