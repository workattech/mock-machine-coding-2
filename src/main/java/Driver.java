import business.Ledger;
import models.*;

import java.math.BigDecimal;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {

        Ledger ledger = new Ledger();
        Users users = new Users();
        users.addUser(new User("u1"));
        users.addUser(new User("u2"));
        users.addUser(new User("u3"));
        users.addUser(new User("u4"));
        ledger.showBalances();
        ledger.getBalancesOfUser(users.getUser("u1"));
        Expense expense1 = new EqualExpense(
                users.getUser("u1")
                ,new BigDecimal(1000)
                ,Arrays.asList(
                        users.getUser("u1")
                        ,users.getUser("u2")
                        ,users.getUser("u3")
                        ,users.getUser("u4")
                )
        );
        ledger.ProcessExpense(expense1);
        ledger.getBalancesOfUser(users.getUser("u4"));
        ledger.getBalancesOfUser(users.getUser("u1"));
        Expense expense2 = new ExactExpense(
                users.getUser("u1")
                ,new BigDecimal(1250)
                ,Arrays.asList(
                        users.getUser("u2")
                        ,users.getUser("u3")
                )
                ,Arrays.asList(
                    new BigDecimal(370)
                    , new BigDecimal(880)
                )
        );
        ledger.ProcessExpense(expense2);
        ledger.getAllBalances();
        Expense expense3 = new PercentExpense(
                users.getUser("u4")
                ,new BigDecimal(1200)
                ,Arrays.asList(
                    users.getUser("u1")
                    ,users.getUser("u2")
                    ,users.getUser("u3")
                    ,users.getUser("u4")
                )
                ,Arrays.asList(
                    new BigDecimal(40)
                    ,new BigDecimal(20)
                    ,new BigDecimal(20)
                    ,new BigDecimal(20)
                )
        );
        ledger.ProcessExpense(expense3);
        ledger.getBalancesOfUser(users.getUser("u1"));
        ledger.getAllBalances();
    }
}
