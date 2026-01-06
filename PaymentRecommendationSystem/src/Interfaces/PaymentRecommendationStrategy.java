package Interfaces;

import Models.Cart;
import Models.User;

import java.util.List;

public interface PaymentRecommendationStrategy {
    public List<PaymentInstrument> recommendPaymentStrategy(Cart cart, User user);
}
