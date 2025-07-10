import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercenatgeSplit implements Split {
    @Override
    public Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails) {
        Map<User, Double> percentage = (Map<User, Double>) splitDetails.get("percentages");
        Map<User, Double> split = new HashMap<>();
        for(User user: participants){
            double percent = percentage.getOrDefault(user, 0.0);
            split.put(user, amount * percent/100.0);
        }
        return split;
    }
}
