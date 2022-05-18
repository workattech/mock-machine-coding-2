package entity;

public class SplitManager {


    private Split split;

    public SplitManager(){
    }

    public Split splitExpense(User user, double amount) {
        split = new ExactSplit(user, amount);
        return split;
    }

    public Split splitExpense(User user, Integer numOfUsers, double amount) {
        split = new EqualSplit(user, numOfUsers, amount);
        return split;
    }

    public Split splitExpense(User user, double percent, double amount) {
        split = new PercentSplit(user, percent, amount);
        return split;
    }
}
