/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.List;
import java.util.Map;

/**
 *
 * @author rajeshkumar.yadav
 */
public class Exact extends ExpenseType{

    public Exact() {
        super(0);
    }
    
    public void split(List<User> l1, List<Double> l2, User paidByUser){
        
        Balance paidByUserBalance  = paidByUser.getBalance();
        int paidByUserId = paidByUser.getUseId();
        
        for(int i=0; i<l1.size(); i++)
        {
            Balance b = l1.get(i).getBalance();
            Map<Integer, Double> m = b.getPay();
            
            if(m.get(paidByUserId) != null)
            {
                m.put(paidByUserId, m.get(paidByUserId) + l2.get(i));
            }else
            {
                m.put(paidByUserId, l2.get(i));
            }
            
            Map<Integer, Double> paidByUserGetBackMap = paidByUserBalance.getGetBack();
            
            if(paidByUserGetBackMap.get(l1.get(i).getUseId()) != null)
            {
                paidByUserGetBackMap.put(l1.get(i).getUseId(), paidByUserGetBackMap.get(l1.get(i).getUseId()) + l2.get(i));
                
            }else
            {
                paidByUserGetBackMap.put(l1.get(i).getUseId(), l2.get(i));
            }
            
        }
    }
}
