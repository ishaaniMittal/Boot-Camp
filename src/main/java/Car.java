/**
 * Created by IShAani on 27-07-2015.
 */
public class Car {

    private String carNo;
    private String travellerName;



    public Car(String carNo, String travellerName){
        this.carNo = carNo;
        this.travellerName = travellerName;
    }


    public String getCarNo() {
        return carNo;
    }

    public String getTravellerName() {
        return travellerName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNo='" + carNo + '\'' +
                ", travellerName='" + travellerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!carNo.equals(car.carNo)) return false;
        return travellerName.equals(car.travellerName);

    }

    @Override
    public int hashCode() {
        int result = carNo.hashCode();
        result = 31 * result + travellerName.hashCode();
        return result;
    }
}

