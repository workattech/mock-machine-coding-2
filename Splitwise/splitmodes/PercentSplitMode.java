package Designs.Splitwise.splitmodes;

import Designs.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentSplitMode extends SplitMode {

    @Override
    public void addExpense(Map<String, User> usersMap, String[] tokens) {
        initialise(tokens, usersMap);

        List<Double> expenseDistribution = populateExpenseDistribution(tokens);
        boolean isValid = validate(expenseDistribution);
        if (!isValid) {
            System.out.println("Invalid input");
            return;
        }

        int j = 0;
        for (User borrower : users) {
            double percent = expenseDistribution.get(j++);
            double exactShare = percent / 100 * expenseToAdd;
            if (borrower == lender) continue;
            // lender side
            updateLenderPortfolio(lender, exactShare, borrower);

            // borrower side
            updateBorrowerPortfolio(lender, exactShare, borrower);
        }
    }

    private boolean validate(List<Double> expenseDistribution) {
        double sum = 0;
        for (Double d : expenseDistribution) {
            sum += d;
        }
        return sum == 100.0;
    }

}
