package com.test;
import com.test.models.User;
import com.test.services.SplitWiseService;

import java.util.*;

public class Driver {

    public static void main(String[] args) {
        SplitWiseService splitWiseService=new SplitWiseService();
        Scanner in=new Scanner(System.in);

        //Initialise Users
        int noOfUsers=in.nextInt();
        List<User> users=new ArrayList<User>();
        for(int i=0;i<noOfUsers;i++)
        {
            users.add(new User(in.next(),in.next(),in.next(),in.next()));
        }
        splitWiseService.setUsers(users);

        //User Commands
        String userCommand="";
        in.nextLine();
        do{
            userCommand=in.nextLine();
            splitWiseService.executeCommand(userCommand);

        }while(!userCommand.equals("exit"));
    }
}
