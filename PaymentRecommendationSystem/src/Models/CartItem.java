package Models;

import CommonEnums.LineOfBusiness;

import java.util.Set;

public class CartItem {
    private double price;
    private String name;

    private LineOfBusiness lineOfBusiness;
    private Set<String>supportedPaymentMethods;
    public CartItem(String name, double price, LineOfBusiness lineOfBusiness, Set<String> supportedPaymentMethods) {
        this.price = price;
        this.name = name;
        this.lineOfBusiness = lineOfBusiness;
        this.supportedPaymentMethods = supportedPaymentMethods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLineOfBusiness(LineOfBusiness lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public void setSupportedPaymentMethods(Set<String> supportedPaymentMethods) {
        this.supportedPaymentMethods = supportedPaymentMethods;
    }

    public String getName() {
        return name;
    }

    public LineOfBusiness getLineOfBusiness() {
        return lineOfBusiness;
    }

    public Set<String> getSupportedPaymentMethods() {
        return supportedPaymentMethods;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
