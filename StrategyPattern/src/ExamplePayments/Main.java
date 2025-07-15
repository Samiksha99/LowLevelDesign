package ExamplePayments;

import ExamplePayments.PaymentsMethods.CreditCardPayment;
import ExamplePayments.PaymentsMethods.UPIPayment;

public class Main {
    public static void main(String[] args) {

        PaymentStrategy creditCardPayment = new CreditCardPayment();
        PaymentStrategy upiPayment = new UPIPayment();
        PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPayment);
        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStrategy(upiPayment);
        paymentProcessor.processPayment();
    }
}
