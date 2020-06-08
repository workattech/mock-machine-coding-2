package com.splitwise.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRegistry {
    public Set<User> getUserRegistry() {
        return userRegistry;
    }

    public void setUserRegistry(Set<User> userRegistry) {
        this.userRegistry = userRegistry;
    }

    private Set<User> userRegistry = new HashSet();

    public Optional<User> searchUserFromRegistry(String userName){
        for(User user : userRegistry){
            if (user.getName().equalsIgnoreCase(userName)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
