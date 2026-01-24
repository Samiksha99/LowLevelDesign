package Models;

import Enums.BikeSize;
import Enums.VehicleStatus;
import Enums.VehicleType;

public class Bike extends Vehicle {
    private BikeSize size;

    public void setSize(BikeSize size) {
        this.size = size;
    }

    public BikeSize getSize() {
        return size;
    }

    public Bike(String vehicleId, VehicleType vehicleType, double pricePerHour, BikeSize size) {
        super(vehicleId, vehicleType, pricePerHour);
        this.size = size;
    }
}
