package Repositories;

import Enums.VehicleStatus;
import Models.Vehicle;
import Models.VehicleCriteria;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class VehicleRepository {
    private final Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();
    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
    }
    public void delete(String vehicleId) {
        vehicles.remove(vehicleId);
    }


    public Vehicle getVehicleById(String vehicleId) {
        return vehicles.get(vehicleId);
    }
    public List<Vehicle> findAllAvailableVehicles() {
        return vehicles.values().stream().toList();
    }
    public List<Vehicle> findAvailableVehiclesByType(VehicleCriteria vehicleCriteria) {
        return vehicles.values().stream()
                .filter(vehicle -> vehicle.getStatus() == VehicleStatus.AVAILABLE)
                .filter(vehicleCriteria::matches)
                .toList();
    }

}
