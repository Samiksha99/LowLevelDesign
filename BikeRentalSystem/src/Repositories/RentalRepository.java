package Repositories;

import Models.Rental;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RentalRepository {
    private final Map<String , Rental> rentals = new ConcurrentHashMap<>();

    public void saveRental(Rental rental){
        rentals.put(rental.getRentalId(), rental);
    }

    public Rental getActiveRentalForVehicle(String vehicleId){
        for(Rental rental: rentals.values()){
            if(rental.getVehicle().getVehicleId().equalsIgnoreCase(vehicleId)
                    && rental.getEndDate().isAfter(LocalDateTime.now())){
                return rental;
            }
        }
        return null;
    }
    public List<Rental> getOverdueRentalsByCustomer(String customerId){
        return rentals.values().stream()
                .filter(rental -> rental.getCustomer().getcustomerId().equalsIgnoreCase(customerId)
                        && rental.getEndDate().isBefore(LocalDateTime.now()))
                .toList();
    }
    public List<Rental> getAllRentalsByCustomer(String customerId){
        return rentals.values().stream()
                .filter(rental -> rental.getCustomer().getcustomerId().equalsIgnoreCase(customerId)).toList();
    }
}
