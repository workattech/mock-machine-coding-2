package Designs.Splitwise;

// https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2

import Designs.Splitwise.machine.Machine;
import Designs.Splitwise.machine.Splitwise;
import Designs.Splitwise.model.User;
import Designs.Splitwise.modeselector.SplitModeSelector;
import Designs.Splitwise.modeselector.SplitTypes;
import Designs.Splitwise.parser.Parser;
import Designs.Splitwise.splitmodes.EqualSplitMode;
import Designs.Splitwise.splitmodes.ExactSplitMode;
import Designs.Splitwise.splitmodes.PercentSplitMode;

import java.util.HashMap;
import java.util.Map;

//You can create a few users in your main method. No need to take it as input.
//There will be 3 types of input:
//SplitTypes in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>
//Show balances for all: SHOW
//Show balances for a single user: SHOW <user-id>

//SHOW
//SHOW u1
//EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
//SHOW u4
//SHOW u1
//EXPENSE u1 1250 2 u2 u3 EXACT 370 880
//SHOW
//EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
//SHOW u1
//SHOW
public class Client {
    public static void main(String[] args) {
        // onboard users in the machine
        User u1 = new User("u1", "jack", "jack@gmail.com", 9289);
        User u2 = new User("u2", "Tom", "tom@gmail.com", 9282);
        User u3 = new User("u3", "kev", "kev@gmail.com", 9281);
        User u4 = new User("u4", "rees", "rees@gmail.com", 9280);

        Map<String, User> usersMap = new HashMap<>();
        usersMap.put(u1.getUserId(), u1);
        usersMap.put(u2.getUserId(), u2);
        usersMap.put(u3.getUserId(), u3);
        usersMap.put(u4.getUserId(), u4);

        // initialise SplitModeSelector
        SplitModeSelector splitModeSelector = new SplitModeSelector(
                Map.of(SplitTypes.EQUAL, new EqualSplitMode(),
                        SplitTypes.EXACT, new ExactSplitMode(),
                        SplitTypes.PERCENT, new PercentSplitMode()
                ));

        // Initialise Machine
        Machine machine = new Splitwise(usersMap, splitModeSelector);

        // Commands
        String equalExpense = "EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL";
        String exactExpense = "EXPENSE u1 1250 2 u2 u3 EXACT 370 880";
        String percentExpense = "EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20";
        String show = "SHOW";
        String show_u1 = "SHOW u1";
        String show_u4 = "SHOW u4";

        // Input to Parser
        Parser parser = new Parser(machine);
        parser.parse(equalExpense);
        parser.parse(show_u4);
        parser.parse(show_u1);
        parser.parse(exactExpense);
        parser.parse(show);
        parser.parse(percentExpense);
        parser.parse(show_u1);
        parser.parse(show);
    }
}
