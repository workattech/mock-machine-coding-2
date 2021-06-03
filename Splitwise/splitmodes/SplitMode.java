package Designs.Splitwise.splitmodes;

import Designs.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// i have specifically created this as abstract class instead of interface, because i wanted to have state in this class
// restricted to specific object, so cant be static, restricted its accessibility to only child classes so protected
// and the state shouldn't be final, as i want the state to be present temporary only.
// So, dont require state to be public,static,final that interface provides.

// this design is flexible,so in future if any diff mode comes it can accomodate by creating another class by just
// extending this base abstract class
public abstract class SplitMode {
    protected User lender;
    protected double expenseToAdd;
    List<User> users = new ArrayList<>();

    public abstract void addExpense(Map<String, User> usersMap, String[] tokens);

    protected void initialise(String[] tokens, Map<String, User> usersMap) {
        lender = usersMap.get(tokens[1]);
        expenseToAdd = Double.parseDouble(tokens[2]);
        int userLength = Integer.parseInt(tokens[3]);
        int i;
        for (i = 4; i < 4 + userLength; i++) {
            users.add(usersMap.get(tokens[i]));
        }
    }

    protected void updateLenderPortfolio(User lender, double equalShare, User borrower) {
        Double creditAmountForLender = lender.loanGivenTo(borrower);
        Double debitAmountForLender = lender.debtFrom(borrower);
        double lenderAmount = creditAmountForLender + debitAmountForLender + equalShare;
        if (lenderAmount > 0) {
            lender.removeDebtFor(borrower);
            lender.addLoanFor(borrower, lenderAmount);
        } else if (lenderAmount < 0) {
            lender.removeLoanFor(borrower);
            lender.addDebtFor(borrower, lenderAmount);
        } else {
            lender.removeLoanFor(borrower);
            lender.removeDebtFor(borrower);
        }
    }

    protected void updateBorrowerPortfolio(User lender, double equalShare, User borrower) {
        Double loanAmountBorrower = borrower.loanGivenTo(lender);
        Double debtAmountBorrower = borrower.debtFrom(lender);
        double borrowerAmount = loanAmountBorrower + debtAmountBorrower - equalShare;
        if (borrowerAmount > 0) {
            borrower.removeDebtFor(lender);
            borrower.addLoanFor(lender, borrowerAmount);
        } else if (borrowerAmount < 0) {
            borrower.removeLoanFor(lender);
            borrower.addDebtFor(lender, borrowerAmount);
        } else {
            borrower.removeDebtFor(lender);
            borrower.removeLoanFor(lender);
        }
    }

    protected List<Double> populateExpenseDistribution(String[] tokens) {
        int i = 4 + users.size() + 1;
        List<Double> expenseDistribution = new ArrayList<>();
        while (i < tokens.length) {
            expenseDistribution.add(Double.parseDouble(tokens[i]));
            i++;
        }
        return expenseDistribution;
    }
}
