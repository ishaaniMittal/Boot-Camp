import java.util.*;

/**
 * Created by IShAani on 28-07-2015.
 */
public class Attendant implements ParkingLotObserver{

   Map<ParkingLot,Boolean> parkingLotStatus = new HashMap<ParkingLot, Boolean>();


    public Attendant(List<ParkingLot> parkingLots){
        for(ParkingLot pL : parkingLots)
            parkingLotStatus.put(pL,false);
    }

    public ParkingLot checkParkingLotStatus() {

        Iterator it = parkingLotStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() == (Boolean) false) {
                ParkingLot parkingLot = (ParkingLot) pair.getKey();
                return parkingLot;
            }

        }
        return null;
    }

    public Car unparkCar(String token){

        String splitTokenParts[] = token.split("-");
        String lotName = splitTokenParts[0];
        String carToken = splitTokenParts[1];

        Iterator it = parkingLotStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ParkingLot pl = (ParkingLot)pair.getKey();
            if (pl.getName().equals(lotName)) {
               Car c = pl.removeCar(Integer.parseInt(carToken));
                return c;
            }

        }

        return null;
    }


    public String parkCar(Car c){
        ParkingLot parkingLot = checkParkingLotStatus();
        int token = parkingLot.park(c);
        String lotName = parkingLot.getName();
        String tokenString = Integer.toString(token);
        String newToken = lotName.concat("-");
        newToken = newToken.concat(tokenString);
        return newToken;
    }


    @Override
    public void notificationHandler(INotificationForParkingLot notification) {
        String lotName = notification.getLotName();

        Iterator it = parkingLotStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ParkingLot pl = (ParkingLot) pair.getKey();
            if (pl.getName().equals(lotName)) {
                pair.setValue(true);
            }

        }

    }
}
