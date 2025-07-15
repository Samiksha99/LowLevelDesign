public class YellowState implements TrafficLightState{
    @Override
    public void next(TrafficLightContext trafficLightContext) {
        System.out.println("Switching from Yellow to Red. Stop!");
        trafficLightContext.setContext(new RedState());
    }

    @Override
    public String getColor() {
        return "Yellow";
    }
}
