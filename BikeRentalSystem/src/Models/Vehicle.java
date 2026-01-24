package Models;

import Enums.VehicleStatus;
import Enums.VehicleType;

public abstract class Vehicle {
    protected String vehicleId;
    protected VehicleType vehicleType;

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public Vehicle(String vehicleId, VehicleType vehicleType, double pricePerHour) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.status = VehicleStatus.AVAILABLE;
        this.pricePerHour = pricePerHour;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    protected VehicleStatus status;
    protected double pricePerHour;
}
