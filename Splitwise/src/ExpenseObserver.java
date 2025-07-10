public interface ExpenseObserver {
    void onExpenseAdded(Expense expense);
    void onExpenseUpdated(Expense expense);
}
