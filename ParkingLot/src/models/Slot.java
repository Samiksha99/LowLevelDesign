package models;

import CommonEnums.SlotStatus;
import CommonEnums.SlotType;

public class Slot {
    private int slotNumber;
    private SlotStatus status;
    private SlotType slotType;

    public Slot(int slotNumber, SlotType slotType){
        this.slotNumber = slotNumber;
        this.status = SlotStatus.EMPTY;
        this.slotType = slotType;
    }

    public void changeStatus(SlotStatus status){
        this.status = status;

    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public SlotType getSlotType() {
        return slotType;
    }
}
