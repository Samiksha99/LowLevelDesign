import Fare.FareStrategy;
import Fare.HourlyFareStrategy;
import Payment.PaymentStrategy;
import Payment.UPIPaymentStrategy;
import models.FourWheeler;
import models.ParkingLot;
import models.Ticket;
import models.Vehicle;

public class Main {
    public static void main(String[] args) throws Exception {

        FareStrategy hourlyFareStrategy = new HourlyFareStrategy();
        FareStrategy standardFareStrategy = new HourlyFareStrategy(); // Replace with actual StandardFareStrategy when implemented
        Vehicle vehicle1 = new FourWheeler("KA-01-HH-1234", hourlyFareStrategy);
        Vehicle vehicle2 = new FourWheeler("KA-01-Hk-1234", hourlyFareStrategy);
        Vehicle vehicle3 = new FourWheeler("KA-01-HI-1234", standardFareStrategy);
        Vehicle vehicle4 = new FourWheeler("KA-01-HJ-1234", standardFareStrategy);
        Vehicle vehicle5 = new FourWheeler("KA-01-HK-1234", standardFareStrategy);
        Vehicle vehicle6 = new FourWheeler("KA-01-HL-1234", standardFareStrategy);

        ParkingService parkingService = new ParkingService();
        parkingService.addFloors(4);
        Ticket ticket1 = parkingService.createTicket(vehicle1);
        Ticket ticket2 = parkingService.createTicket(vehicle2);
        Ticket ticket3 = parkingService.createTicket(vehicle3);
        Ticket ticket4 = parkingService.createTicket(vehicle4);
        Ticket ticket5 = parkingService.createTicket(vehicle5);
        Ticket ticket6 = parkingService.createTicket(vehicle6);

        PaymentStrategy paymentStrategy = new UPIPaymentStrategy(); // Initialize with a concrete PaymentStrategy implementation
        parkingService.releaseParkingSlot(ticket1, paymentStrategy);
        parkingService.releaseParkingSlot(ticket2, paymentStrategy);

        parkingService.releaseParkingSlot(ticket3, paymentStrategy);

        parkingService.releaseParkingSlot(ticket4, paymentStrategy);

        parkingService.releaseParkingSlot(ticket5, paymentStrategy);

        parkingService.releaseParkingSlot(ticket6, paymentStrategy);

    }
}