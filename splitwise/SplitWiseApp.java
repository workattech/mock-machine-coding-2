/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rajeshkumar.yadav
 */
public class SplitWiseApp {

    Map<Integer, User> userSet;
    Map<String, User> uniqueConstarin;

    public SplitWiseApp() {

        userSet = new HashMap<>();
        uniqueConstarin = new HashMap<>();
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidEmail(String mail) {
        return true;
    }

    public void createUser(int id, String name, String emailId, String mobNo) {

        if (uniqueConstarin.get(emailId) != null) {
            System.err.println("This email Id is alredy registered with some other user, Plase select  a diiferent one ");
            return;
        }
        if (uniqueConstarin.get(mobNo) != null) {
            System.err.println("This Mobile No is alredy registered with some other user, Plase select  a diiferent one ");
            return;
        }
        if (userSet.get(id) != null) {
            System.err.println("This User Id is alredy registered with some other user, Plase select  a diiferent one ");
            return;
        }

        if (!isNumeric(mobNo)) {
            System.err.println("This mobile number is not valid ");
            return;
        }

        if (!isValidEmail(emailId)) {
            System.err.println("This Email Id is not valid ");
            return;
        }

        User u1 = new User(id, name, emailId, mobNo);
        userSet.put(id, u1);

    }

    public void processEaxctInput(int paidByUserId, int amount, List<Integer> userIdList, List<Double> amountList) {

        ExpenseType expType = null;
        expType = new Exact();
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < userIdList.size(); i++) {
            userList.add(userSet.get(userIdList.get(i)));
        }
        expType.split(userList, amountList, userSet.get(paidByUserId));

    }

    public void processPercentageInput(int paidByUserId, int amount, List<Integer> userIdList, List<Integer> amountList) {

        ExpenseType expType = null;
        expType = new Percentage(amount);
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < userIdList.size(); i++) {
            userList.add(userSet.get(userIdList.get(i)));
        }
        expType.split(userList, amountList);

    }
    
    public void processEqualInput(int paidByUserId, int amount, List<Integer> userIdList) {

        ExpenseType expType = null;
        expType = new Equal(amount);
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < userIdList.size(); i++) {
            userList.add(userSet.get(userIdList.get(i)));
        }
        expType.split(userList);

    }
    
   public void show()
   {
       System.out.println("splitwise.SplitWiseApp.show() start "); 
       for(Integer i : userSet.keySet())
       {
           User u = userSet.get(i);
           Map<Integer, Double> payMap = u.getBalance().getPay();
           for(Integer j: payMap.keySet())
           {
               System.out.println(u.getName() + " owe rs " + payMap.get(j) + " to user " + userSet.get(j).getName());
           }
       }
       //System.out.println(" ");
       System.out.println(" ");
       System.out.println(" ");
   }
   
   public void show(int id)
   {
       System.out.println("splitwise.SplitWiseApp.show(id) start ");
           User u = userSet.get(id);
           Balance b = u.getBalance();
           Map<Integer, Double> payMap = b.getPay();
           for(Integer j: payMap.keySet())
           {
               //System.err.println(" j =" + j);
               System.out.println(u.getName() + " owe rs " + payMap.get(j) + " to user " + userSet.get(j).getName());
           }
           Map<Integer, Double> getBackMap = b.getGetBack();
           for(Integer j: getBackMap.keySet())
           {
               //System.err.println(" j =" + j);
               System.out.println(u.getName() + " will get back rs" + getBackMap.get(j) + " from user " + userSet.get(j).getName());
           }
       System.out.println(" ");
       System.out.println(" ");
   }
/*
   
   SHOW
SHOW u1
EXPENSE u1 1250 4 u1 u2 u3 u4 EQUAL
SHOW u4
SHOW u1
EXPENSE u1 1250 2 u2 u3 EXACT 370 880
SHOW
EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
SHOW u1
SHOW
   
   */
    public static void main(String[] args) {

        SplitWiseApp app = new SplitWiseApp();
        app.createUser(1, "Rajesh", "Rajesh1@gmail.com", "9902543838");
        app.createUser(2, "Rajesh2", "Rajesh2@gmail.com", "9902543839");
        app.createUser(3, "Rajesh3", "Rajesh3@gmail.com", "9902543837");
        app.createUser(4, "Rajesh4", "Rajesh4@gmail.com", "9902543836");
        
        app.show();
        app.show(1);
        
        List<Integer> l1 = new ArrayList<Integer>();
        List<Double> l2 = new ArrayList<Double>();
        List<Integer> l3 = new ArrayList<Integer>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        app.processEqualInput(1, 1250, l1);
        
        app.show(4);
        app.show(1);
        
        l1.clear();
        l1.add(2);
        l1.add(3);
        l2.add(370.0);
        l2.add(880.0);
        app.processEaxctInput(1, 1250, l1, l2);
        l1.clear();
        
        app.show();
        
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l3.add(40);l3.add(20);l3.add(20);l3.add(20);
        app.processPercentageInput(1, 1200, l1, l3);
        
        app.show(1);
        app.show();

    }

}
