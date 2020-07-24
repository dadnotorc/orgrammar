package explore.ParkingLot;

public class HatchBackCar implements Vehicle {

    private String license;

    public HatchBackCar(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "car license=" + license;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.COMPACT;
    }
}
