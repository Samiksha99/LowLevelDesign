package Models;

import Enums.PaymentStatus;

public class Payment {
    private String paymentId;
    private Rental rental;
    private double amount;
    private PaymentStatus status;
    private String paymentMethod; // e.g., Credit Card, PayPal, etc.

}
