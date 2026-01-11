package Payment;

public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment processed");
    }
}
