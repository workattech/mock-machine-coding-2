package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(User payer, BigDecimal amount, List<User> payee) {
        super(payer, amount, payee);
    }

    @Override
    public List<Balance> getBalances() {
        List<Balance> balances  = new ArrayList<>();
        BigDecimal sharedAmount = amount.divide(new BigDecimal(payee.size()));
        for (User user : payee) {
            if(user.equals(payer)) {
                continue;
            }
            balances.add(new Balance(user,payer,sharedAmount));
        }
        return balances;
    }
}
