package com.sameerpandit.service;

import com.sameerpandit.db.InMemRepository;
import com.sameerpandit.models.User;

import org.jvnet.hk2.annotations.Service;

import java.util.List;

import jakarta.inject.Inject;

@Service
public class UserService {
    @Inject
    InMemRepository repository;

    public String addUser(String name, String email, String phone) {
        return repository.addUser(name,email,phone);
    }

    public User getUserDetails(String userId) {
        return repository.getUserDetailsById(userId);
    }

    public List<User> getUsers(){
        return repository.getUsers();
    }
}
