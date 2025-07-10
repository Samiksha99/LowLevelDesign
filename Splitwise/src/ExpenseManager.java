import java.util.ArrayList;
import java.util.List;

public class ExpenseManager implements ExpenseObservable{
    List<ExpenseObserver> observers = new ArrayList<>();
    List<Expense> expenses = new ArrayList<>();
    @Override
    public void addObserver(ExpenseObserver expenseObserver) {
        observers.add(expenseObserver);
    }

    @Override
    public void removeObserver(ExpenseObserver expenseObserver) {
        observers.remove(expenseObserver);
    }

    @Override
    public void notifyExpenseAdded(Expense expense) {
        for(ExpenseObserver observer: observers){
            observer.onExpenseAdded(expense);
        }
    }

    @Override
    public void notifyExpenseUpdated(Expense expense) {
        for(ExpenseObserver observer: observers){
            observer.onExpenseUpdated(expense);
        }
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
        notifyExpenseAdded(expense);
    }

    public void updateExpense(Expense expense) {
        for (int i=0; i<expenses.size();i++){
            if(expenses.get(i).getId() == expense.getId()){
                expenses.set(i, expense);
                notifyExpenseUpdated(expense);
                return;
            }
        }
        throw new IllegalArgumentException("Expense with ID " + expense.getId() + " not found.");

    }

}
