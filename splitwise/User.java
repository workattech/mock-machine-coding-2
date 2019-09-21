/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitwise;

/**
 *
 * @author rajeshkumar.yadav
 */

public class User {
    
    private int useId;
    private String name;
    private String emailId;
    private String mobNo;
    public Balance balance;

    public User(int useId, String name, String emailId, String mobNo) {
        this.useId = useId;
        this.name = name;
        this.emailId = emailId;
        this.mobNo = mobNo;
        balance = new Balance();
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public int getUseId() {
        return useId;
    }

    public void setUseId(int useId) {
        this.useId = useId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
    
}
