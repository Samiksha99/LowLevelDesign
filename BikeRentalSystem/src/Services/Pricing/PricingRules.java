package Services.Pricing;

import Enums.BikeSize;
import Enums.ScooterMotor;

import java.util.Map;

public class PricingRules {

    public static final Map<BikeSize, Double> BIKE_SIZE_MULTIPLIER =
            Map.of(
                    BikeSize.SMALL, 1.0,
                    BikeSize.MEDIUM, 1.2,
                    BikeSize.LARGE, 1.5
            );

    public static final Map<ScooterMotor, Double> SCOOTER_MOTOR_MULTIPLIER =
            Map.of(
                    ScooterMotor.GAS, 1.0,
                    ScooterMotor.ELECTRIC, 1.4
            );
}
