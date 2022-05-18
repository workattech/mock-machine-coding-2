import java.util.ArrayList;
import java.util.List;

import entity.ExpenseManager;
import entity.Split;
import entity.SplitManager;
import entity.SplitType;
import entity.User;

public class CommandManager {

    private ExpenseManager expenseManager;

    private SplitManager splitManager;

    public CommandManager(final ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        this.splitManager = new SplitManager();
    }

    public void runCommand(String command) {
        String[] splitCommand = command.split(" ");
        if(splitCommand[0].equals("EXPENSE")){
            addExpense(command);
        } else if(splitCommand[0].equals("SHOW")){
            showExpense(command);
        }
    }

    public void showExpense(String command) {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length==1) {
            expenseManager.showForAllUsers();
        } else {
            User user = new User(splitCommand[1]);
            expenseManager.showForUser(user);
        }
    }

    public void addExpense(String command) {
        String[] splitCommand = command.split(" ");
        User userWhoPaid = new User(splitCommand[1]);
        double totalAmountPaid = Double.valueOf(splitCommand[2]);
        Integer totalNumOfUsers = Integer.valueOf(splitCommand[3]);
        int index = 4;
        List<User> userList = new ArrayList<>();
        for (int i=1; i<=totalNumOfUsers; i++){
            User user = new User(splitCommand[index++]);
            userList.add(user);
        }
        if(splitCommand[index].equals(SplitType.EQUAL.toString())) {
            for(User user: userList) {
                Split split = splitManager.splitExpense(user, totalNumOfUsers, totalAmountPaid);
                if(!split.getUser().equals(userWhoPaid)) {
                    expenseManager.addSplitExpense(userWhoPaid, split);
                }
            }
        } else if(splitCommand[index].equals(SplitType.EXACT.toString())) {
            index++;
            for (int i=1; i<=totalNumOfUsers; i++){
                Double amount = Double.valueOf(splitCommand[index++]);
                Split split = splitManager.splitExpense(userList.get(i-1), amount);
                if(!split.getUser().equals(userWhoPaid)) {
                    expenseManager.addSplitExpense(userWhoPaid, split);
                }
            }
        } else if(splitCommand[index].equals(SplitType.PERCENT.toString())) {
            index++;
            for (int i=1; i<=totalNumOfUsers; i++){
                Double percent = Double.valueOf(splitCommand[index++]);
                Split split = splitManager.splitExpense(userList.get(i-1), percent,
                        totalAmountPaid);
                if(!split.getUser().equals(userWhoPaid)) {
                    expenseManager.addSplitExpense(userWhoPaid, split);
                }
            }
        }

    }
}
