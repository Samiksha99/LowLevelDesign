package Models;

import Enums.ScooterMotor;
import Enums.VehicleStatus;
import Enums.VehicleType;

public class Scooter extends Vehicle {
    private ScooterMotor motorType;

    public void setMotorType(ScooterMotor motorType) {
        this.motorType = motorType;
    }

    public ScooterMotor getMotorType() {
        return motorType;
    }

    public Scooter(String vehicleId, VehicleType vehicleType, double pricePerHour, ScooterMotor motorType) {
        super(vehicleId, vehicleType, pricePerHour);
        this.motorType = motorType;
    }
}
