package Interfaces;

import Models.CartItem;

import java.util.List;
import java.util.Set;

public interface PaymentComputationStrategy {
    public Set<String> computePaymentMethods(List<CartItem> cartItems);
}
