package models;

import CommonEnums.SlotStatus;
import Payment.PaymentStrategy;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {
    private Slot slot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Vehicle vehicle;

    private PaymentStrategy paymentStrategy;
    public Slot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Ticket (Slot slot, Vehicle vehicle) {
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
    }

    public double calculateDurationInMin(LocalDateTime exitTime){
        this.exitTime = exitTime;
        Duration duration = Duration.between(entryTime, exitTime);

        // Convert duration to different double units
        return duration.toMinutes() / 60.0;
    };

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
}
