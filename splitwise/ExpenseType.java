/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.List;

/**
 *
 * @author rajeshkumar.yadav
 */
public class ExpenseType {
    
    private int amount;
    public void split(List<User> l1, List<Double> l2, User paidByUser){
        
    }
    public void split(List<User> l1, List<Integer> l2){
    }
    public void split(List<User> l1){
    }
    
    public ExpenseType(int amount) {
        this.amount = amount;
    }
    
     public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
