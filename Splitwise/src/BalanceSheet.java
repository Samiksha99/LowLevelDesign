import java.util.HashMap;
import java.util.Map;

public class BalanceSheet implements ExpenseObserver{
    private Map<UserPair, Double> balances = new HashMap<>();
    @Override
    public void onExpenseAdded(Expense expense) {
        updateBalances(expense);
    }

    @Override
    public void onExpenseUpdated(Expense expense) {

    }

    public void updateBalances(Expense expense){
        User payer = expense.getPaidBy();
        Map<User, Double> shares = expense.getShare();
        for(Map.Entry<User, Double> entry: shares.entrySet()){
            Double amount = entry.getValue();
            User participant = entry.getKey();
            if(!participant.equals(payer)){
                UserPair userPair = new UserPair(participant, payer);
                Double currentBalance = balances.getOrDefault(userPair, 0.0);
                balances.put(userPair, currentBalance+amount);
            }
        }

    }

    public double getBalance(User user1, User user2){
        UserPair pair1 = new UserPair(user1, user2);
        UserPair pair2 = new UserPair(user2, user1);

        double balance1 = balances.getOrDefault(pair1, 0.0);
        double balance2 = balances.getOrDefault(pair2, 0.0);

        return balance1 - balance2;
    }


    public double getTotalBalance(User user) {
        double total = 0.0;
        for(Map.Entry<UserPair, Double> entry: balances.entrySet()){
            UserPair pair = entry.getKey();
            Double amount = entry.getValue();
            if(pair.getUser1() == user){
                total -=amount;
            }
            else if(pair.getUser2() == user){
                total +=amount;
            }
        }
        return total;
    }
}
