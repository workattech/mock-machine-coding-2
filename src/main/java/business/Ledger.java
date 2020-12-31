package business;

import models.Balance;
import models.Expense;
import models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    // TODO hashmap implementation for performance
    List<Balance> balances;

    public Ledger() {
        this.balances = new ArrayList<>();
    }

    public void ProcessExpense(Expense expense) {
        List<Balance> balances = expense.getBalances();
        for (Balance balance : balances) {
            Balance existingBalance = getBalanceOfUsers(balance.getBorrower(), balance.getGiver());
            if (existingBalance == null) {
                this.balances.add(balance);
                continue;
            }
            if(existingBalance.getBorrower().equals(balance.getBorrower())) {
                existingBalance.addAmount(balance.getAmount());
            } else {
                BigDecimal amount = existingBalance.getAmount().subtract(balance.getAmount());
                if(amount.compareTo(new BigDecimal(0))==0) {
                    this.balances.remove(existingBalance);
                } else if(amount.compareTo(new BigDecimal(0))>0) {
                    existingBalance.setAmount(amount);
                } else {
                    this.balances.remove(existingBalance);
                    this.balances.add(new Balance(balance.getBorrower(), balance.getGiver(), amount.negate()));
                }
            }
        }
    }

    private Balance getBalanceOfUsers(User user1, User user2) {
        Balance balance = null;
        for (Balance balanceLoop : balances) {
            if(balanceLoop.isUsersInvolved(user1, user2)) {
                balance = balanceLoop;
            }
        }
        return balance;
    }

    public void showBalances(){
        printBalance(balances);
    }

    public void getBalancesOfUser(User user) {
        List<Balance> balancesOfUser = new ArrayList<>();
        for (Balance balance : balances) {
            if (balance.isUserInvolved(user)) {
                balancesOfUser.add(balance);
            }
        }
        printBalance(balancesOfUser);
    }

    public void getAllBalances() {
        printBalance(this.balances);
    }

    private void printBalance(List<Balance> balances) {
        if(balances.isEmpty()) {
            System.out.println("No balances");
        }
        else {
            for (Balance balance : balances) {
                System.out.println(balance);
            }
        }
        System.out.println("-");
    }


}
