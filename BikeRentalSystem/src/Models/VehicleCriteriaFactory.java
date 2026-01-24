package Models;

import Enums.BikeSize;
import Enums.ScooterMotor;
import Enums.VehicleType;

public class VehicleCriteriaFactory {

    public static VehicleCriteria fromInput(
            VehicleType vehicleType,
            BikeSize bikeSize,
            ScooterMotor motorType) {

        if (vehicleType == VehicleType.BIKE) {
            return new BikeCriteria(bikeSize);
        }

        if (vehicleType == VehicleType.SCOOTER) {
            return new ScooterCriteria(motorType);
        }

        throw new IllegalArgumentException("Unsupported vehicle type");
    }
}

