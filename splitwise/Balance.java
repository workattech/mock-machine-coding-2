/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rajeshkumar.yadav
 */
public class Balance {

    Map<Integer, Double> pay;
    Map<Integer, Double> getBack;

    public Balance() {
        pay = new HashMap<Integer, Double>();
        getBack = new HashMap<Integer, Double>();
    }

    public Map<Integer, Double> getPay() {
        return pay;
    }

    public void setPay(Map<Integer, Double> pay) {
        this.pay = pay;
    }

    public Map<Integer, Double> getGetBack() {
        return getBack;
    }

    public void setGetBack(Map<Integer, Double> getBack) {
        this.getBack = getBack;
    }

}
