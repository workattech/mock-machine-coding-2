package Designs.Splitwise.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final Integer mobileNumber;
    private final Map<User, Double> debtMap;
    private final Map<User, Double> loanMap;

    public User(String userId, String name, String email, Integer mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.debtMap = new HashMap<>();
        this.loanMap = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;
        if (!getName().equals(user.getName())) return false;
        if (!email.equals(user.email)) return false;
        return mobileNumber.equals(user.mobileNumber);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + mobileNumber.hashCode();
        return result;
    }

    public Double debtFrom(User user) {
        return debtMap.getOrDefault(user, 0.0);
    }

    public Double loanGivenTo(User user) {
        return loanMap.getOrDefault(user, 0.0);
    }

    public void addLoanFor(User user, Double balance) {
        loanMap.put(user, balance);
    }

    public void addDebtFor(User user, Double balance) {
        debtMap.put(user, balance);
    }

    public void removeDebtFor(User user) {
        debtMap.remove(user);
    }

    public void removeLoanFor(User borrower) {
        loanMap.remove(borrower);
    }

    public void showDebt() {
        for (Map.Entry<User, Double> entry : debtMap.entrySet()) {
            System.out.println(name + " owes " + entry.getKey().name + " : " + Math.abs(entry.getValue()));
        }
    }

    public void showLoan() {
        for (Map.Entry<User, Double> entry : loanMap.entrySet()) {
            System.out.println(entry.getKey().name + " owes " + name + " : " + Math.abs(entry.getValue()));
        }
    }
}
