package models;

import java.math.BigDecimal;
import java.util.List;

public abstract class Expense {
    protected User payer;
    protected BigDecimal amount;
    List<User> payee;

    public Expense(User payer, BigDecimal amount, List<User> payee) {
        if (payer == null) {
            throw new IllegalArgumentException("payer must not be null");
        }

        if(amount.compareTo(new BigDecimal(0))<=0) {
            throw new IllegalArgumentException("amount must be positive");
        }

        if (payee == null || payee.isEmpty() || payee.contains(null)) {
            throw new IllegalArgumentException("payee list must not be null or empty or containing null. ");
        }

        this.payer = payer;
        this.amount = amount;
        this.payee = payee;
    }

    abstract public List<Balance> getBalances();
}
