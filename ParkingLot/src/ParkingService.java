import CommonEnums.SlotStatus;
import Payment.PaymentStrategy;
import models.ParkingLot;
import models.Slot;
import models.Ticket;
import models.Vehicle;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ParkingService(){
        this.parkingLot = ParkingLot.getInstance();
    }

    public Ticket createTicket(Vehicle vehicle)  {
        readWriteLock.writeLock().lock();
        try{
            Slot slot = parkingLot.findAvailableSlot(vehicle);
            if(slot!=null){
                slot.changeStatus(SlotStatus.FILLED);
                return new Ticket(slot, vehicle);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        throw new RuntimeException("No slots available");
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
