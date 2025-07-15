package ExamplePayments.PaymentsMethods;

import ExamplePayments.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing Credit card payment...");
    }
}
