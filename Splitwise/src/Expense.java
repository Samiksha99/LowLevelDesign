import java.util.List;
import java.util.Map;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private User paidBy;
    private List<User> sharedBy;

    private Map<User, Double> share;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getSharedBy() {
        return sharedBy;
    }

    public Map<User, Double> getShare() {
        return share;
    }

    public Expense(int id, String description, double amount, User paidBy, List<User> sharedBy, Map<User, Double> share) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
        this.share = share;
    }
}
