package interfaces;

import models.AddUserResponse;
import models.User;

import java.util.List;

public interface IUserInterface {

    AddUserResponse addUser(User user);

    List<User> fetchAllUsers();

    void removeUser(String userId);
}
