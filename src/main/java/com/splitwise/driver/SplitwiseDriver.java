package com.splitwise.driver;


import com.splitwise.io.InputTxtReader;
import com.splitwise.model.User;
import com.splitwise.model.UserRegistry;

public class SplitwiseDriver{
    private static UserRegistry userRegistry;

    public static UserRegistry getUserRegistry(){
        return userRegistry;
    }
    public static void main(String[] args) {
            User u1 = new User("u1","88888888881","u1@abc.com");
            User u2 = new User("u2","88888888882","u3@abc.com");
            User u3 = new User("u3","88888888883","u2@abc.com");
            User u4 = new User("u4","88888888884","u4@abc.com");
            User u5 = new User("u5","88888888885","u5@abc.com");


            userRegistry = new UserRegistry();
            userRegistry.getUserRegistry().add(u1);
            userRegistry.getUserRegistry().add(u2);
            userRegistry.getUserRegistry().add(u3);
            userRegistry.getUserRegistry().add(u4);
            userRegistry.getUserRegistry().add(u5);

            InputTxtReader reader = new InputTxtReader();
            reader.readInputTxtFile("input.txt");


        }
    }