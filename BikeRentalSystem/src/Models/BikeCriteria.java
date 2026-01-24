package Models;

import Enums.BikeSize;

public class BikeCriteria implements VehicleCriteria {

    private final BikeSize requiredSize;

    public BikeCriteria(BikeSize requiredSize) {
        this.requiredSize = requiredSize;
    }

    @Override
    public boolean matches(Vehicle vehicle) {
        if (!(vehicle instanceof Bike)) {
            return false;
        }
        Bike bike = (Bike) vehicle;
        return bike.getSize() == requiredSize;
    }
}

