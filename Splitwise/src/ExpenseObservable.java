public interface ExpenseObservable {
    void addObserver(ExpenseObserver expenseObserver);

    void removeObserver(ExpenseObserver expenseObserver);
    void notifyExpenseAdded(Expense expense);
    void notifyExpenseUpdated(Expense expense);
}
