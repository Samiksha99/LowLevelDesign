package Services;

import Enums.VehicleStatus;
import Models.Vehicle;
import Models.VehicleCriteria;
import Repositories.VehicleRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryService {
    private final VehicleRepository vehicleRepository;

    private final Map<String, ReentrantLock> vehicleLocks = new ConcurrentHashMap<>();
    public InventoryService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void removeVehicle(String vehicleId) {
        vehicleRepository.delete(vehicleId);
    }
    public List<Vehicle> getAllAvailableVehicles() {
        return vehicleRepository.findAllAvailableVehicles();
    }
    public Vehicle findAvailableVehicle(VehicleCriteria vehicleCriteria) {
        return vehicleRepository.findAvailableVehiclesByType(vehicleCriteria).stream().findFirst().orElse(null);
    }

    public Vehicle reserveVehicle(String vehicleId) {
        vehicleLocks.putIfAbsent(vehicleId, new ReentrantLock());
        ReentrantLock lock = vehicleLocks.get(vehicleId);

        lock.lock();
        try{
            Vehicle vehicle = vehicleRepository.getVehicleById(vehicleId);
            if(vehicle == null){
                throw new IllegalArgumentException("Vehicle not found");
            }
            if(vehicle.getStatus() == VehicleStatus.AVAILABLE){
                vehicle.setStatus(VehicleStatus.RENTED);
                vehicleRepository.save(vehicle);
                return vehicle;
            } else {
                throw new IllegalStateException("Vehicle is not available for reservation");
            }
        }
        finally {
            lock.unlock();
        }
    }

    public Vehicle releaseVehicle(String vehicleId) {
        vehicleLocks.putIfAbsent(vehicleId, new ReentrantLock());
        ReentrantLock lock = vehicleLocks.get(vehicleId);

        lock.lock();
        try{
            Vehicle vehicle = vehicleRepository.getVehicleById(vehicleId);
            if(vehicle == null){
                throw new IllegalArgumentException("Vehicle not found");
            }
            if(vehicle.getStatus() == VehicleStatus.RENTED){
                vehicle.setStatus(VehicleStatus.AVAILABLE);
                vehicleRepository.save(vehicle);
                return vehicle;
            } else {
                throw new IllegalStateException("Vehicle is already available");
            }
        }
        finally {
            lock.unlock();
        }
    }
}
