package Designs.Splitwise.machine;

import Designs.Splitwise.model.User;
import Designs.Splitwise.splitmodes.SplitMode;
import Designs.Splitwise.modeselector.SplitModeSelector;
import Designs.Splitwise.modeselector.SplitTypes;

import java.util.Map;

public class Splitwise implements Machine {
    private Map<String, User> usersMap;
    private SplitModeSelector modeSelector;

    public Splitwise(Map<String, User> usersMap, SplitModeSelector modeSelector) {
        this.usersMap = usersMap;
        this.modeSelector = modeSelector;
    }

    @Override
    public void showAll() {
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            User user = entry.getValue();
            user.showDebt();
        }
    }

    @Override
    public void showBalanceFor(String userId) {
        User user = usersMap.get(userId);
        if (user == null) System.out.println("User not found");
        user.showDebt();
        user.showLoan();
    }

    @Override
    public void addExpense(String[] tokens) {
        int userLength = Integer.parseInt(tokens[3]);
        SplitTypes splitType = SplitTypes.valueOf(tokens[4 + userLength]);

        SplitMode mode = modeSelector.select(splitType);
        mode.addExpense(usersMap, tokens);
    }
}
