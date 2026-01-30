package models;

import java.util.ArrayList;

public class ParkingLot {

    private static ParkingLot instance;
    private ArrayList<Floor> floors;

    private ParkingLot() {};

    public void addFloors(int numFloors){
        this.floors = new ArrayList<>();
        for(int i=0;i<numFloors;i++){
            this.floors.add(new Floor(5, 5, 10));
        }
    }
    public static synchronized ParkingLot getInstance() {
        if(instance ==null){
            instance =  new ParkingLot();
        }
        return instance;
    }
    public Slot findAvailableSlot(Vehicle vehicle){
        for(Floor floor: floors){
            System.out.println("Searching on floor" + floor);
            Slot slot = floor.findAvailableSlotOnFloor(vehicle);
            if(slot!=null){
                return slot;
            }
        }
        return null;
    };
}
