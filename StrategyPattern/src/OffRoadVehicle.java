import UserLevel.SportsDriveStrategy;

public class OffRoadVehicle extends Vehicle{
    OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }
}
