package TrafficSignal.States;


import TrafficSignal.TrafficLightContext;
import TrafficSignal.TrafficLightState;

public class RedState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext trafficLightContext) {
        System.out.println("Switching from Red to Green. Go!");
        trafficLightContext.setContext(new GreenState());
    }

    @Override
    public String getColor() {
        return "Red";
    }
}
