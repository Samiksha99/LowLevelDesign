public class TransportFactory {
    public static ModeTransportFactory getTransport(String mode) {
        if(mode.contains("Water")){
            return new WaterTransportFactory();
        }
        else if(mode.contains("Road")){
            return new RoadTransportFactory();
        }
        return null;
    }
}
