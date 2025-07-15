package TrafficSignal;

import TrafficSignal.States.RedState;

public class TrafficLightContext {
    private TrafficLightState currentState;
    public TrafficLightContext(){
        currentState = new RedState();
    }

    public void setContext(TrafficLightState state){
        this.currentState = state;
    }

    public void next() {
        currentState.next(this);
    }
}
