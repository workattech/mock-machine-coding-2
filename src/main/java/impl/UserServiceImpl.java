package impl;

import interfaces.IUserInterface;
import models.AddUserResponse;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserInterface {

    private static final Integer SUCCESS_CODE = 0;
    private List<User> userList;

    @Override
    public AddUserResponse addUser(User user) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        // Add any validations if required like email validations before adding
        userList.add(user);
        return AddUserResponse.builder()
                .status(SUCCESS_CODE)
                .message("User added successfully")
                .build();
    }

    @Override
    public List<User> fetchAllUsers() {
        return userList;
    }

    public User fetchUserById(String userId) {
        if (userList != null && userId != null && !userId.isEmpty()) {
            return userList.stream()
                    .filter(user -> user.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public List<User> fetchUserByIds(List<String> userIdList) {
        if (userList != null && userIdList != null && !userIdList.isEmpty()) {
            return userList.stream()
                    .filter(user -> userIdList.contains(user.getUserId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void removeUser(String userId) {

    }

    public void printPassBook(User user) {

    }
}
