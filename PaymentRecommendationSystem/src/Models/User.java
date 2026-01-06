package Models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int id;
    private Map<String, Double>paymentPreferences;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaymentPreferences(Map<String, Double> paymentPreferences) {
        this.paymentPreferences = paymentPreferences;
    }

    public int getId() {
        return id;
    }

    public Map<String, Double> getPaymentPreferences() {
        return paymentPreferences;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.paymentPreferences = new HashMap<>();
        paymentPreferences.put("CREDIT_CARD:VISA", 0.9);
        paymentPreferences.put("CREDIT_CARD:Mastercard", 0.8);
        paymentPreferences.put("UPI: GooglePay", 0.7);
    }
}
