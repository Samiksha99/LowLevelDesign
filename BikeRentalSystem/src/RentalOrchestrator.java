import Models.Customer;
import Models.Rental;
import Models.Vehicle;
import Models.VehicleCriteria;
import Services.InventoryService;
import Services.Pricing.PaymentService;
import Services.RentalService;

import java.time.LocalDateTime;

public class RentalOrchestrator {

    private final RentalService rentalService;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;

    public RentalOrchestrator(RentalService rentalService, InventoryService inventoryService, PaymentService paymentService) {
        this.rentalService = rentalService;
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }

    public Rental rentVehicle(Customer customer, VehicleCriteria vehicleCriteria,
                              LocalDateTime startDate, LocalDateTime expectedEndDate) {

        Vehicle availableVehicle = null;
        try {
            availableVehicle = inventoryService.findAvailableVehicle(vehicleCriteria);

            if (availableVehicle == null) {
                throw new IllegalStateException("No available vehicles match the criteria");
            }

            double initialFare = paymentService.calculatePricing(availableVehicle, startDate, expectedEndDate);

            Rental rental = rentalService.createRental(customer, availableVehicle, startDate, expectedEndDate, initialFare);
            inventoryService.reserveVehicle(availableVehicle.getVehicleId());
            return  rental;
        } catch (Exception e) {

            if (availableVehicle != null) {
                inventoryService.releaseVehicle(availableVehicle.getVehicleId());
            }
            throw e;
        }
    }

    public Rental returnVehicle(Rental rental, LocalDateTime actualReturnDate) {
        Vehicle vehicle = rental.getVehicle();
        double finalFare = paymentService.calculateFinalFare(vehicle, rental.getEndDate(), actualReturnDate);
        rentalService.endRental(rental, actualReturnDate, finalFare);
        inventoryService.releaseVehicle(vehicle.getVehicleId());
        return rental;
    }
}
