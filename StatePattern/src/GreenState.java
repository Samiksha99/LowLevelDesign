public class GreenState implements TrafficLightState{
    @Override
    public void next(TrafficLightContext trafficLightContext) {
        System.out.println("Switching from Green to Yellow. Wait!");
        trafficLightContext.setContext(new YellowState());
    }

    @Override
    public String getColor() {
        return "Green";
    }
}
