package Interfaces;

public interface PaymentInstrumentFactory {
    public PaymentInstrument createPaymentInstrument(String type, String issuer, double relevanceScore);
}
