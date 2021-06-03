package Designs.Splitwise.splitmodes;

import Designs.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitMode extends SplitMode {

    @Override
    public void addExpense(Map<String, User> usersMap, String[] tokens) {
        initialise(tokens, usersMap);

        List<Double> expenseDistribution = populateExpenseDistribution(tokens);
        boolean isValid = validate(expenseToAdd, expenseDistribution);
        if (!isValid) {
            System.out.println("Invalid input");
            return;
        }

        int j = 0;
        for (User borrower : users) {
            double exactShare = expenseDistribution.get(j++);
            if (borrower == lender) continue;
            // lender side
            updateLenderPortfolio(lender, exactShare, borrower);

            // borrower side
            updateBorrowerPortfolio(lender, exactShare, borrower);
        }
    }

    private boolean validate(double expenseToAdd, List<Double> expenseDistribution) {
        double sum = 0;
        for (Double d : expenseDistribution) {
            sum += d;
        }
        return expenseToAdd == sum;
    }
}
