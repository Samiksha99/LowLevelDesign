import Enums.BikeSize;
import Enums.ScooterMotor;
import Enums.VehicleType;
import Models.*;
import Repositories.RentalRepository;
import Repositories.VehicleRepository;
import Services.InventoryService;
import Services.Pricing.PaymentService;
import Services.RentalService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(" C001", "Alice", "8266");

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        InventoryService inventoryService = new InventoryService(vehicleRepository);
        RentalService rentalService = new RentalService(rentalRepository);
        PaymentService paymentService = new PaymentService();

        RentalOrchestrator rentalOrchestrator = new RentalOrchestrator(rentalService, inventoryService, paymentService);


        Bike vehicle1 = new Bike("V001", VehicleType.BIKE, 50.0, BikeSize.MEDIUM);
        Bike vehicle2 = new Bike("V001", VehicleType.BIKE, 50.0, BikeSize.SMALL);
        Bike vehicle3 = new Bike("V001", VehicleType.BIKE, 50.0, BikeSize.LARGE);
        Scooter vehicle4 = new Scooter("V002", VehicleType.SCOOTER, 80.0, ScooterMotor.ELECTRIC);
        inventoryService.addVehicle(vehicle1);
        inventoryService.addVehicle(vehicle2);
        inventoryService.addVehicle(vehicle3);
        inventoryService.addVehicle(vehicle4);

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime expectedEndDate = startDate.plusHours(5);

        inventoryService.findAvailableVehicle(VehicleCriteriaFactory.fromInput(VehicleType.BIKE, BikeSize.MEDIUM,null));


        inventoryService.getAllAvailableVehicles();
        rentalService.getAllRentalsByCustomer(customer.getCustomerId());

        VehicleCriteria criteria1 = VehicleCriteriaFactory.fromInput(VehicleType.SCOOTER, null,ScooterMotor.ELECTRIC);

        Rental rental = rentalOrchestrator.rentVehicle(customer, criteria1, startDate, expectedEndDate);

        System.out.println("Rental created. Fare: "
                + rental.getTotalFare());

        LocalDateTime actualReturnDate = expectedEndDate.plusHours(6);
        Rental completedRental = rentalOrchestrator.returnVehicle(rental, actualReturnDate);

        System.out.println("Rental completed. Final fare: "
                + completedRental.getTotalFare());
    }

}