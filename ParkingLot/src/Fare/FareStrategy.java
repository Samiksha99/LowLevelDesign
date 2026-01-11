package Fare;

import java.time.LocalDateTime;

public interface FareStrategy {

    double calculateFare(double duration);
}
