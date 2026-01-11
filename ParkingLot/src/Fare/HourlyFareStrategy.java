package Fare;

public class HourlyFareStrategy implements FareStrategy{
    @Override
    public double calculateFare(double duration) {
        return 20 * duration; // in min
    }
}
