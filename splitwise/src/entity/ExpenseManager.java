package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private List<User> users;

    private Map<Map<User, User> , Double>  userOweMap;

    public ExpenseManager() {
        users = new ArrayList<>();
        userOweMap = new HashMap<>();
    }

    public ExpenseManager(List<User> userList) {
        users = userList;
        userOweMap = new HashMap<>();
    }

    public ExpenseManager(List<User> userList, Map<Map<User, User> , Double>  userOweMap) {
        users = userList;
        userOweMap = userOweMap;
    }

    public Map<Map<User, User> , Double>  getWhoOweWhomMap() {
        return userOweMap;
    }

    public List<User> getUsers() {
        return users;
    }

    private void showForUsers(List<User> userList) {
        boolean isAnyBalanceDue = false;
        for (User owedUser : users) {
            for (User oweUser : users) {
                if ((userList.contains(owedUser) || userList.contains(oweUser))
                        && (userOweMap.containsKey(Map.of(owedUser, oweUser)))) {
                    Double amount = userOweMap.get(Map.of(owedUser, oweUser));
                    isAnyBalanceDue = true;
                    if (amount > 0) {
                        System.out.printf("%s owes %s: %s %n", owedUser.getUserId(),
                                oweUser.getUserId(), amount);
                    }
                }
            }
        }

        if(!isAnyBalanceDue) {
                System.out.println("No Balances");
        }
    }
    public void showForAllUsers() {
        showForUsers(users);
    }

    public void showForUser(User user) {
        showForUsers(Collections.singletonList(user));
    }

    public void addSplitExpense(User owedUser, Split split) {
        double prevAmt = 0;
        if(userOweMap.containsKey(Map.of(owedUser, split.getUser()))){
            prevAmt = userOweMap.get(Map.of(owedUser, split.getUser()));
        }
        userOweMap.put(Map.of(owedUser, split.getUser()), prevAmt-split.getAmount());
        userOweMap.put(Map.of(split.getUser(), owedUser), split.getAmount()-prevAmt);
    }
}
