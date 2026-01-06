package PaymentMethodComputation;

import Interfaces.PaymentComputationStrategy;
import Models.CartItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonPaymentComputationStrategy implements PaymentComputationStrategy {
    @Override
    public Set<String> computePaymentMethods(List<CartItem> cartItems) {
        if(cartItems.isEmpty()) return new HashSet<>();

        // all common supported payment methods of cart Items

        Set<String> commonMethods = cartItems.get(0).getSupportedPaymentMethods();
        for(CartItem cartItem : cartItems){
            commonMethods.retainAll(cartItem.getSupportedPaymentMethods());
        }


        return commonMethods;
    }
}
