package models;

import CommonEnums.SlotStatus;
import CommonEnums.SlotType;

import java.util.ArrayList;
import java.util.UUID;

public class Floor {
    private String floorNumber;
    private ArrayList<Slot> slots;

    public Floor(int fourWheelerSlot, int heavyVehicleSlot, int otherVehicleSlot){
        this.floorNumber = UUID.randomUUID().toString();
        this.slots = new ArrayList<>();
        for(int i=0; i<fourWheelerSlot; i++){
            this.slots.add(new Slot(i, SlotType.FOUR_WHEELER));
        }
        for(int j=0; j<heavyVehicleSlot; j++){
            this.slots.add(new Slot(j, SlotType.HEAVY_VEHICLE));
        }
    }

    // addFourWheelerSlots
    // addHeavyVehicleSlots

    public Slot findAvailableSlotOnFloor(Vehicle vehicle)  {
        for(Slot slot: slots){
            if(slot.getStatus() == SlotStatus.EMPTY && slot.getSlotType() == (vehicle instanceof FourWheeler ? SlotType.FOUR_WHEELER : SlotType.HEAVY_VEHICLE)){
                System.out.println("Available slot found: " + slot.getSlotNumber());
                return slot;
            }
        }
        return null;
    };

}
