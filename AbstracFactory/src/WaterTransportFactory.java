public class WaterTransportFactory implements ModeTransportFactory {
    @Override
    public Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("Ship")) {
            return new Ship();
        } else if (type.equalsIgnoreCase("Boat")) {
            return new Boat();
        }
        throw new IllegalArgumentException("Unknown water vehicle type: " + type);
    }
}
