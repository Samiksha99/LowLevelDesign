import CommonEnums.LineOfBusiness;
import Interfaces.PaymentComputationStrategy;
import Interfaces.PaymentInstrument;
import Models.Cart;
import Models.CartItem;
import Models.User;
import PaymentMethodComputation.PaymentComputationStrategyFactory;
import PaymentRecommendation.PaymentRecommendationStrategyImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> supportedMethods1 = new HashSet<>(Arrays.asList("CREDIT_CARD:VISA", "UPI:GOOGLE_PAY"));
        CartItem cartItem1 = new CartItem("Item1", 100.0, LineOfBusiness.COMMERCE_PURCHASE, supportedMethods1);

        Set<String> supportedMethods2 = new HashSet<>(Arrays.asList("CREDIT_CARD:VISA", "CREDIT_CARD:Mastercard", "UPI:GOOGLE_PAY"));
        CartItem cartItem2 = new CartItem("Item2", 200.0, LineOfBusiness.CREDIT_CARD_BILL, supportedMethods2);

        Cart cart = new Cart();
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);

        User user = new User("Sam", 1);

        PaymentComputationStrategyFactory paymentComputationStrategyFactory = new PaymentComputationStrategyFactory();
        PaymentComputationStrategy paymentComputationStrategy = paymentComputationStrategyFactory.createPaymentComputationStrategy("common");

        PaymentRecommendationStrategyImpl paymentRecommendationStrategy = new PaymentRecommendationStrategyImpl(paymentComputationStrategy);
        List<PaymentInstrument> recommendedInstruments = paymentRecommendationStrategy.recommendPaymentStrategy(cart, user);

        for(PaymentInstrument paymentInstrument: recommendedInstruments) {
            paymentInstrument.makePayment(cart.getTotalAmount());
        }
    }
}