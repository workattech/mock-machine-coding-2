import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.ExpenseManager;
import entity.User;

public class SplitwiseMain {
    public static void main(String[] args) throws IOException {
        List<User> userList = new ArrayList<>();
        User user1 = new User("U1", "User1", "User1@gmail.com",
                "9798512549");
        User user2 = new User("U2", "User2", "User2@gmail.com",
                "9798512548");
        User user3 = new User("U3", "User3", "User3@gmail.com",
                "9798512547");
        User user4 = new User("U4", "User4", "User4@gmail.com",
                "9798512546");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        ExpenseManager expenseManager = new ExpenseManager(userList);

        CommandManager commandManager = new CommandManager(expenseManager);

        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String command;
        while((command = br.readLine()) != null) {
            if (command.equals("exit")) {
                System.exit(0);
            } else {
                commandManager.runCommand(command);
            }
        }
    }
}
