package com.sameerpandit;

import com.sameerpandit.db.InMemRepository;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;

@Service
public class Driver {
    @Inject
    Splitwise splitwise;
    @Inject
    InMemRepository repository;
    public void run(){
        System.out.println("Running Driver");
        test1();
        repository.clear();
        test2();
    }

    public void test1(){
        System.out.println("------------------------------------------------------");
        String user1 = splitwise.addUser("User1", "user1@gmail.com", "9986368688");
        String user2 = splitwise.addUser("User2", "user2@gmail.com", "9986368689");
        String user3 = splitwise.addUser("User3", "user3@gmail.com", "9986368682");
        String user4 = splitwise.addUser("User4", "user4@gmail.com", "9986368683");
        splitwise.show();
//        System.out.println("------------------------------------------------------");
        splitwise.show(user1);
//        System.out.println("------------------------------------------------------");
        //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        splitwise.addExpense(1000,"EQUAL",user1, new String[]{user1, user2, user3,user4});
        splitwise.show(user4);
//        System.out.println("------------------------------------------------------");
        splitwise.show(user1);
//        System.out.println("------------------------------------------------------");
        //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
        splitwise.addExpense(1250,"EXACT",user1, new String[]{user1, user2, user3}, new Integer[]{0, 370, 880});
        splitwise.show();
//        System.out.println("------------------------------------------------------");
        //EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
        splitwise.addExpense(1200,"PERCENT",user4, new String[]{user1, user2, user3, user4}, new Integer[]{40, 20, 20, 20});
        splitwise.show(user1);
//        System.out.println("------------------------------------------------------");
        splitwise.show();
//        System.out.println("------------------------------------------------------");
    }

    public void test2(){
        System.out.println("------------------------------------------------------");
        String user1 = splitwise.addUser("User1", "user1@gmail.com", "9986368688");
        String user2 = splitwise.addUser("User2", "user2@gmail.com", "9986368689");
        String user3 = splitwise.addUser("User3", "user3@gmail.com", "9986368682");
        splitwise.show();
        //        System.out.println("------------------------------------------------------");
        splitwise.show(user1);
        //        System.out.println("------------------------------------------------------");
        //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        splitwise.addExpense(1000,"EQUAL",user1, new String[]{user1, user2, user3});
        splitwise.show(user3);
        //        System.out.println("------------------------------------------------------");
        splitwise.show(user1);
        //        System.out.println("------------------------------------------------------");
        //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
        splitwise.addExpense(1250,"EXACT",user1, new String[]{user1, user2,user3 }, new Integer[]{0, 370, 880});
        splitwise.show();
        //        System.out.println("------------------------------------------------------");
        //EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
        splitwise.addExpense(1200,"PERCENT",user3, new String[]{user1, user2, user3}, new Integer[]{60, 20, 20});
        splitwise.show(user1);
        //        System.out.println("------------------------------------------------------");
        splitwise.show();
        //        System.out.println("------------------------------------------------------");
    }
}
