package Fare;

import java.time.LocalDateTime;

public class DefaultFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(double duration) {
        return 20;
    }
}
