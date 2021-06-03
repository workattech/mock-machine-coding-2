package Designs.Splitwise.splitmodes;

import Designs.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitMode extends SplitMode {

    @Override
    public void addExpense(Map<String, User> usersMap, String[] tokens) {
        initialise(tokens, usersMap);

        double equalShare = expenseToAdd / users.size();

        for (User borrower : users) {
            if (borrower == lender) continue;
            // lender side
            updateLenderPortfolio(lender, equalShare, borrower);

            // borrower side
            updateBorrowerPortfolio(lender, equalShare, borrower);
        }
    }

}
