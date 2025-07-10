import java.util.*;

public class Main {
    public static void main(String[] args) {
        User sam = new User(1, "Samiksha", 8266);
        User alice = new User(2, "Alice", 7234);
        User bob = new User(3, "Bob", 8234);

        ExpenseManager expenseManager = new ExpenseManager();
        BalanceSheet balanceSheet = new BalanceSheet();

        expenseManager.addObserver(balanceSheet);

        List<User> participants = new ArrayList<>();
        participants.add(sam);
        participants.add(bob);
        participants.add(alice);

        Split equalSplit = SplitFactory.createSplit(SplitType.EQUAL);
        Map<String, Object> splitDetails = new HashMap<>();
        Map<User, Double> dinnerShares = equalSplit.calculateSplit(60, participants, splitDetails);

        Expense dinnerExpense = new Expense(1, "Dinner", 60, alice, participants, dinnerShares);

        expenseManager.addExpense(dinnerExpense);

        Split percentageSplit = SplitFactory.createSplit(SplitType.PERCENTAGE);
        Map<String, Object> percentageSplitDetails = new HashMap<>();
        Map<User, Double> percentage = new HashMap<>();
        percentage.put(alice, 40.0);
        percentage.put(bob, 30.0);
        percentage.put(sam, 30.0);
        percentageSplitDetails.put("percentages", percentage);

        Map<User, Double> billShares = percentageSplit.calculateSplit(45, participants, percentageSplitDetails);

        Expense billExpense = new Expense(2, "Bill", 45, bob, participants, billShares);

        expenseManager.addExpense(billExpense);

        // Get individual balances
        System.out.println("Individual balances:");
        System.out.println("Alice's total balance: $" + balanceSheet.getTotalBalance(alice));
        System.out.println("Bob's total balance: $" + balanceSheet.getTotalBalance(bob));
        System.out.println("Sam's total balance: $" + balanceSheet.getTotalBalance(sam));

        // Print specific balances between users
        System.out.println("Pairwise balances:");
        System.out.println("Alice and Bob: $" + balanceSheet.getBalance(alice, bob));
        System.out.println("Alice and Sam: $" + balanceSheet.getBalance(alice, sam));
        System.out.println("Bob and Sam: $" + balanceSheet.getBalance(bob, sam));

    }
}