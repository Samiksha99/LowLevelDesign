package Models;

import Enums.RentalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime actualReturnDate;

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public Rental(String rentalId, Customer customer, Vehicle vehicle, LocalDateTime startDate, LocalDateTime endDate, double totalFare, RentalStatus status) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFare = totalFare;
        this.status = status;
    }

    public void completeRental(LocalDateTime actualReturnDate, double finalFare){
        this.actualReturnDate = actualReturnDate;
        this.totalFare = finalFare;
        this.status = RentalStatus.COMPLETED;
    }
    private double totalFare;
    private RentalStatus status;

}
