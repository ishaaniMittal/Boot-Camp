import java.util.*;

/**
 * Created by IShAani on 28-07-2015.
 */
public class Attendant implements ParkingLotObserver{

    Map<ParkingLot,Boolean> parkingLotStatus = new HashMap<ParkingLot, Boolean>();

   // Map<ParkingLot,Float> parkingLotEmpty = new HashMap<>();

    List<ParkingLot> parkingLotList = new ArrayList<>();

    private ParkingLot findMaximumEmpty(){
        float max  = 0f;
        ParkingLot temp = new ParkingLot();
        for(ParkingLot p : parkingLotList) {
            if(p.percentageEmpty() > max)
            {
                max = p.percentageEmpty();
                temp = p;
            }
        }
        return temp;
    }

    public Attendant(List<ParkingLot> parkingLots){
        for(ParkingLot pL : parkingLots)
           parkingLotStatus.put(pL,false);
        this.parkingLotList = parkingLots;

    }

    /*public ParkingLot checkParkingLotStatus() {

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
*/
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
         /*Iterator it = parkingLotStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ParkingLot pl = (ParkingLot)pair.getKey();
            if (pl.getName().equals(lotName)) {
               Car c = pl.removeCar(carToken);
                return c;
            }

        }*/
    }


    public Token parkCar(Car c){
        ParkingLot parkingLot = findMaximumEmpty();
        int token = parkingLot.park(c);
        String lotName = parkingLot.getName();
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
                pair.setValue(true);
            }

        }

    }
}
