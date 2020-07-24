package explore.ParkingLot;

import java.util.List;

import static explore.ParkingLot.VehicleType.*;
import static java.util.Arrays.asList;

public enum SlotSize {

    SMALL(asList(BIKE, COMPACT)),
    MEDIUM(asList(BIKE, COMPACT, SEDAN)),
    LARGE(asList(BIKE, COMPACT, SEDAN, TRUCK));

    private final List<VehicleType> vehicleTypesAllowed;

    SlotSize(List<VehicleType> vehicleTypes) {
        this.vehicleTypesAllowed = vehicleTypes;
    }

    public boolean isVehicleParkingPossible(Vehicle vehicle) {
        return vehicleTypesAllowed.contains(vehicle.getType());
    }
}