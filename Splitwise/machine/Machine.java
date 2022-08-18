package Designs.Splitwise.machine;

public interface Machine {
    void showAll();

    void showBalanceFor(String userId);

    void addExpense(String[] tokens);
}
