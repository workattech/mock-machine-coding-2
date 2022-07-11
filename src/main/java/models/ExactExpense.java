package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExactExpense extends  Expense{
    private List<BigDecimal> amounts;
    public ExactExpense(User payer, BigDecimal amount, List<User> payee,List<BigDecimal> amounts) {
        super(payer, amount, payee);
        this.amounts = amounts;
    }

    @Override
    public List<Balance> getBalances() {
        List<Balance> balances  = new ArrayList<>();
        for (int i = 0; i < payee.size(); i++) {
            if(payee.get(i).equals(payer)) {
                continue;
            }
            balances.add(new Balance(payee.get(i),payer,amounts.get(i)));
        }
        return balances;
    }
}
