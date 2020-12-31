package models;

import java.math.BigDecimal;

public class Balance {
    User borrower;
    User giver;
    BigDecimal amount;

    public Balance(User borrower, User giver, BigDecimal amount) {
        this.borrower = borrower;
        this.giver = giver;
        this.amount = amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public User getGiver() {
        return giver;
    }

    @Override
    public String toString() {
        return String.format("%s owes %s: %s", borrower.getName(), giver.getName(), amount);
    }

    public boolean isUserInvolved(User user) {
        if(borrower.equals(user) || giver.equals(user)) {
            return true;
        }
        return false;
    }

    public boolean isUsersInvolved(User user1, User user2) {
        if((borrower.equals(user1)&& giver.equals(user2))||(borrower.equals(user2)&&giver.equals(user1))) {
            return true;
        }
        return false;
    }

    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
