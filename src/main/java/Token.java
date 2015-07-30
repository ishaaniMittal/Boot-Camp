/**
 * Created by IShAani on 29-07-2015.
 */
public class Token {

    private String parkingLotName;
    private int tokenNumberInEachParkingLot;

    public String getParkingLotName() {
        return parkingLotName;
    }

    @Override
    public String toString() {
        return parkingLotName+"-"+tokenNumberInEachParkingLot;
    }

    public int getTokenNumberInEachParkingLot() {
        return tokenNumberInEachParkingLot;
    }

    public Token(String parkingLotName, int tokenNumberInEachParkingLot) {
        this.parkingLotName = parkingLotName;
        this.tokenNumberInEachParkingLot = tokenNumberInEachParkingLot;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Token) {
            if(this.parkingLotName.equals(((Token) obj).parkingLotName) &&
            this.tokenNumberInEachParkingLot == ((Token) obj).tokenNumberInEachParkingLot)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = parkingLotName.hashCode();
        result = 31 * result + tokenNumberInEachParkingLot;
        return result;
    }
}
