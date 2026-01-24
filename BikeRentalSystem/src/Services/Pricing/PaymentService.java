package Services.Pricing;

import Enums.VehicleType;
import Models.Bike;
import Models.Scooter;
import Models.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class PaymentService {
    public double calculatePricing(Vehicle vehicle, LocalDateTime startTime, LocalDateTime expectedEndTime) {
        long hours = Duration.between(startTime, expectedEndTime).toHours();
        double hourlyRate = calculatePricePerHour(vehicle);

        return hours * hourlyRate;
    }

    private double calculatePricePerHour(Vehicle vehicle) {
        double baseRate = vehicle.getPricePerHour();

        if (vehicle.getVehicleType() == VehicleType.BIKE) {
            Bike bike = (Bike) vehicle;
            return baseRate *
                    PricingRules.BIKE_SIZE_MULTIPLIER.get(bike.getSize());
        }

        if (vehicle.getVehicleType() == VehicleType.SCOOTER) {
            Scooter scooter = (Scooter) vehicle;
            return baseRate *
                    PricingRules.SCOOTER_MOTOR_MULTIPLIER.get(scooter.getMotorType());
        }

        throw new IllegalStateException("Unknown vehicle type");
    }

    public double calculateFinalFare(Vehicle vehicle, LocalDateTime expectedReturnTime, LocalDateTime actualReturnTime) {
        double baseFare = calculatePricing(vehicle, expectedReturnTime.minusHours(Duration.between(expectedReturnTime, actualReturnTime).toHours()), expectedReturnTime);
        double lateFee = calculateLateFee(vehicle, expectedReturnTime, actualReturnTime);
        return baseFare + lateFee;
    }
    private double calculateLateFee(Vehicle vehicle, LocalDateTime expectedReturnTime, LocalDateTime actualReturnTime) {
        long lateHours = Duration.between(expectedReturnTime, actualReturnTime).toHours();
        if (lateHours <= 0) {
            return 0;
        }
        double hourlyRate = calculatePricePerHour(vehicle);
        double lateFeeRate = hourlyRate * 1.5; // 50% more for late returns
        return lateHours * lateFeeRate;
    }
}
