package ExampleVehicle;

import ExampleVehicle.UserLevel.DriveStrategy;

public class Vehicle {
    DriveStrategy driveStrategy;

    Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}
