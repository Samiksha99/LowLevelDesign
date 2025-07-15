package TrafficSignal;

import TrafficSignal.TrafficLightContext;

public interface TrafficLightState {
    void next(TrafficLightContext trafficLightContext);
    String getColor();
}
