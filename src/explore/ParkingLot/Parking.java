package explore.ParkingLot;

import java.util.Date;
import java.util.List;


public class Parking {
    private String parkingId;
    private List<Floor> floors;
    private String parkingName;
    private String address;

    public Parking(String parkingId, List<Floor> floors,
                   String parkingName, String address) {
        this.parkingId = parkingId;
        this.floors = floors;
        this.parkingName = parkingName;
        this.address = address;
    }

    public void emptyWholeParkingLot() {
        for (Floor floor : floors) {
            floor.emptyFloor();
        }
    }
}

class ParkingDetails {
    Vehicle vehicle;
    Parking parking;
    Date entryTime;
    Date exitTime;

    public ParkingDetails(Vehicle vehicle, Parking parking, Date entryTime, Date exitTime) {
        this.vehicle = vehicle;
        this.parking = parking;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }
}

