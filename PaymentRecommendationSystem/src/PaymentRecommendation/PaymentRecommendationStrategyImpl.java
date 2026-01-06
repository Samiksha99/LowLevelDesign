package PaymentRecommendation;

import CreditCard.CreditCardFactory;
import Interfaces.PaymentComputationStrategy;
import Interfaces.PaymentInstrument;
import Interfaces.PaymentInstrumentFactory;
import Interfaces.PaymentRecommendationStrategy;
import Models.Cart;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PaymentRecommendationStrategyImpl implements PaymentRecommendationStrategy {

    private final PaymentComputationStrategy paymentComputationStrategy;
    public PaymentRecommendationStrategyImpl(PaymentComputationStrategy paymentComputationStrategy) {
        this.paymentComputationStrategy = paymentComputationStrategy;
    }

    @Override
    public List<PaymentInstrument> recommendPaymentStrategy(Cart cart, User user) {
        Set<String> commonMethods = paymentComputationStrategy.computePaymentMethods(cart.getCartItems());

        List<PaymentInstrument> recommendedPaymentMethods = new ArrayList<>();
        PaymentInstrumentFactory creditCardFactory = new CreditCardFactory();
        // recommendation logic here based on common methods
        // for simplicity using cc
        for(String method: commonMethods){
            String[] parts = method.split(":");
            String type = parts[0];
            String issuer = parts[1];
            double relevanceScore = parts.length>2 ? Double.parseDouble(parts[2]) : 0.0;

            recommendedPaymentMethods.add(creditCardFactory.createPaymentInstrument(type, issuer, relevanceScore));
        }
        return recommendedPaymentMethods;

    }
}
