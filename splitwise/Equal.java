/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rajeshkumar.yadav
 */

public class Equal extends ExpenseType{

    public Equal(int amount) {
        super(amount);
    }
    
    public void split(List<User> l1){
        
        User PaidByUSer = l1.get(0);
        double divamount = getAmount()/l1.size();
        l1.remove(0);
        List<Double> amount = new ArrayList<>();
        for(int i=0; i<l1.size(); i++)
            amount.add(divamount);
        
        Exact ex = new Exact();
        ex.split(l1, amount, PaidByUSer);
        
    }
    
   
    
}
