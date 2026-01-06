package Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;
    private double totalAmount;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    public void addItem(CartItem cartItem){
        cartItems.add(cartItem);
        totalAmount+=cartItem.getPrice();
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
