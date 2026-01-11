import CommonEnums.SlotStatus;
import Payment.PaymentStrategy;
import models.ParkingLot;
import models.Slot;
import models.Ticket;
import models.Vehicle;

import java.time.LocalDateTime;

public class ParkingService {

    private final ParkingLot parkingLot;

    public ParkingService(){
        this.parkingLot = ParkingLot.getInstance();
    }

    public Ticket createTicket(Vehicle vehicle)  {
        try {
            Slot slot = parkingLot.findAvailableSlot(vehicle);
            slot.changeStatus(SlotStatus.FILLED);
            return new Ticket(slot, vehicle);
        } catch (Exception e){
            System.out.println("No slots available");
            return null;
        }
    };

    public void releaseParkingSlot(Ticket ticket, PaymentStrategy paymentStrategy){
        Vehicle vehicle = ticket.getVehicle();
        double duration = ticket.calculateDurationInMin(LocalDateTime.now());
        double fare = vehicle.getFareStrategy().calculateFare(duration);

        ticket.setPaymentStrategy(paymentStrategy);
        paymentStrategy.processPayment(fare);

        Slot slot = ticket.getSlot();
        slot.changeStatus(SlotStatus.EMPTY);
    }

    public void addFloors(int numFloor){
        parkingLot.addFloors(numFloor);
    }
}
