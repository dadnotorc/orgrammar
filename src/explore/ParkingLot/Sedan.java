package explore.ParkingLot;

public class Sedan implements Vehicle {

    private String license;

    public Sedan(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "car license=" + license;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.SEDAN;
    }
}
