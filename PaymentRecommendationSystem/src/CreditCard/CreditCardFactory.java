package CreditCard;

import Interfaces.PaymentInstrument;
import Interfaces.PaymentInstrumentFactory;

public class CreditCardFactory implements PaymentInstrumentFactory {
    @Override
    public PaymentInstrument createPaymentInstrument(String type, String issuer, double relevanceScore) {
        return new CreditCardPaymentInstrument(type, issuer, relevanceScore);
    }
}
