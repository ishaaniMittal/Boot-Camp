import java.util.*;

/**
 * Created by IShAani on 28-07-2015.
 */
public class Attendant implements ParkingLotObserver{

    Map<ParkingLot,Boolean> parkingLotStatus = new HashMap<ParkingLot, Boolean>();
    SelectionStrategy strategy;
    List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public Attendant(List<ParkingLot> parkingLots, SelectionStrategy strat){
        for(ParkingLot pL : parkingLots)
           parkingLotStatus.put(pL,false);
        this.parkingLotList = parkingLots;
        this.strategy=strat;
        //this.parametertoDirectCar = paramtertoDirectCar;

    }

    public Car unparkCar(Token token){
        String lotName = token.getParkingLotName();
        int carToken = token.getTokenNumberInEachParkingLot();

        for(ParkingLot p : parkingLotList){
            if(p.getName().equals(lotName)){
                Car c = p.removeCar(carToken);
                return c;
            }
        }
        return null;

    }


    public Token parkCar(Car c){
        int token = strategy.apply(this.parkingLotList).park(c);
        String lotName = strategy.apply(this.parkingLotList).getName();
        Token tokenGenerated = new Token(lotName,token);
        return tokenGenerated;
    }


    @Override
    public void notificationHandler(INotificationForParkingLot notification) {
        String lotName = notification.getLotName();

        Iterator it = parkingLotStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ParkingLot pl = (ParkingLot) pair.getKey();
            if (pl.getName().equals(lotName)) {
                if(notification instanceof NotificationForPark) {
                    pair.setValue(true);
                    parkingLotList.remove(pl);
                }
                else if (notification instanceof  NotificationForUnpark) {
                    pair.setValue(false);
                    parkingLotList.add(pl);
                }
            }

        }

    }
}
