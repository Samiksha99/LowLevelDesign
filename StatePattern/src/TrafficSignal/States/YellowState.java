package TrafficSignal.States;

import TrafficSignal.TrafficLightContext;
import TrafficSignal.TrafficLightState;

public class YellowState implements TrafficLightState {
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
