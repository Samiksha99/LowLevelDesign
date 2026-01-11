package models;

import Fare.FareStrategy;

public abstract class Vehicle {
    private String vehicleNumber;
    private FareStrategy fareStrategy;
    Vehicle (String vehicleNumber, FareStrategy fareStrategy) {
        this.fareStrategy = fareStrategy;
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public FareStrategy getFareStrategy() {
        return fareStrategy;
    }
}
