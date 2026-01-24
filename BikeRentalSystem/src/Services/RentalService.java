package Services;

import Enums.RentalStatus;
import Models.Customer;
import Models.Rental;
import Models.Vehicle;
import Repositories.RentalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RentalService {

    private final RentalRepository rentalRepository;
    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
    public Rental createRental(Customer user, Vehicle vehicle, LocalDateTime startDate, LocalDateTime endDate, double initialFare) {
        // Logic to create a new rental
        String vehicleId = vehicle.getVehicleId();
        locks.putIfAbsent(vehicleId, new ReentrantLock());
        ReentrantLock lock = locks.get(vehicleId);

        lock.lock();
        try{
            Rental activeRental = getActiveRentalForVehicle(vehicleId);
            if(activeRental != null){
                throw new IllegalStateException("Vehicle is already rented: " + vehicleId);
            }
            Rental rental = new Rental(UUID.randomUUID().toString(), user, vehicle, startDate, endDate, initialFare, RentalStatus.ACTIVE);
            rentalRepository.saveRental(rental);
            return rental;
        } finally {
            lock.unlock();
        }
    }

    public void endRental(Rental rental, LocalDateTime actualReturnTime, double finalFare) {
        // Logic to end an existing rental
        Rental activeRental = getActiveRentalForVehicle(rental.getVehicle().getVehicleId());
        if(activeRental == null || !activeRental.getRentalId().equals(rental.getRentalId())){
            throw new IllegalStateException("No active rental found for vehicle: " + rental.getVehicle().getVehicleId());
        }
        rental.setStatus(RentalStatus.COMPLETED);
        rental.completeRental(actualReturnTime, finalFare);
        rentalRepository.saveRental(rental);
    }
    public Rental getActiveRentalForVehicle(String vehicleId){
        return rentalRepository.getActiveRentalForVehicle(vehicleId);
    }

    public List<Rental> getOverdueRentalsByCustomer(String customerId){
        return rentalRepository.getOverdueRentalsByCustomer(customerId);
    }

    public List<Rental> getAllRentalsByCustomer(String customerId){
        return rentalRepository.getAllRentalsByCustomer(customerId);
    }
}
