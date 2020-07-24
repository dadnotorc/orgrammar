package explore.ParkingLot;

public class Bike implements Vehicle {

    private String license;

    public Bike(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "bike license=" + license;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.BIKE;
    }

}
