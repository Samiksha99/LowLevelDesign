public class RoadTransportFactory implements ModeTransportFactory {
    @Override
    public Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("Car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("Bus")) {
            return new Bus();
        }
        throw new IllegalArgumentException("Unknown road vehicle type: " + type);
    }

}
