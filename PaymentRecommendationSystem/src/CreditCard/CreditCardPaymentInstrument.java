package CreditCard;

import Interfaces.PaymentInstrument;

public class CreditCardPaymentInstrument implements PaymentInstrument {
    private String type;
    private String issuer;
    private double relevanceScore;

    public CreditCardPaymentInstrument(String type, String user, double relevanceScore) {
        this.type = type;
        this.issuer = user;
        this.relevanceScore = relevanceScore;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Payment of "+ amount + " made using " + issuer + " " + type + " credit card with relevance score "+ relevanceScore);
    }
}
