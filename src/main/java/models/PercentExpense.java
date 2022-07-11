package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PercentExpense extends Expense{
    private List<BigDecimal> percentages;
    public PercentExpense(User payer, BigDecimal amount, List<User> payee, List<BigDecimal> percentages) {
        super(payer, amount, payee);
        this.percentages = percentages;
    }

    @Override
    public List<Balance> getBalances() {
        List<Balance> balances  = new ArrayList<>();
        for (int i = 0; i < payee.size(); i++) {
            if(payee.get(i).equals(payer)) {
                continue;
            }
            BigDecimal percent = percentages.get(i).divide(new BigDecimal(100));
            balances.add(new Balance(payee.get(i),payer,amount.multiply(percent)));
        }
        return balances;

    }
}
