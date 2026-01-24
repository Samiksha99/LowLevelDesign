package Models;

import Enums.ScooterMotor;

public class ScooterCriteria implements VehicleCriteria {

    private final ScooterMotor requiredMotorType;

    public ScooterCriteria(ScooterMotor requiredMotorType) {
        this.requiredMotorType = requiredMotorType;
    }

    @Override
    public boolean matches(Vehicle vehicle) {
        if (!(vehicle instanceof Scooter)) {
            return false;
        }
        Scooter scooter = (Scooter) vehicle;
        return scooter.getMotorType() == requiredMotorType;
    }
}
