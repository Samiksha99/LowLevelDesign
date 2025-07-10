import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements Split{
    @Override
    public Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails) {
        double amountPerPerson = amount/participants.size();
        Map<User, Double> split = new HashMap<>();
        for(User user: participants){
            split.put(user, amountPerPerson);
        }
        return split;
    }
}
