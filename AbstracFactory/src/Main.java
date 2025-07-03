public class Main {
    public static void main(String[] args)
    {
        String input = "Road";
        ModeTransportFactory transportFactory = TransportFactory.getTransport(input);
        assert transportFactory != null;
        Vehicle vehicle = transportFactory.getVehicle("Car");
        vehicle.drive();
    }
}