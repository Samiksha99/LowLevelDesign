package ExamplePayments.PaymentsMethods;

import ExamplePayments.PaymentStrategy;

public class UPIPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing upi payment...");
    }
}
