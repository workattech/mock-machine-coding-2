/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rajeshkumar.yadav
 */
public class Percentage extends ExpenseType{

    public Percentage(int amount) {
        super(amount);
    }
    
    public void split(List<User> l1, List<Integer> l2){
        
        int sum = 0;
        for(int i=0; i<l2.size(); i++)
        {
            sum = sum + l2.get(i);
        }
        if(sum != 100)
        {
            System.err.println("Not a proper percentage division ");
            return;
        }
        
        List<Double> list1 = new ArrayList<Double>();
        for(int i=0; i<l2.size(); i++)
        {
            double d = (getAmount() * l2.get(i)) / 100;
            list1.add(d);
        }
        
        User paidByUser = l1.get(0);
        l1.remove(0);
        Exact ex = new Exact();
        ex.split(l1, list1, paidByUser);
    }
    
    
}
