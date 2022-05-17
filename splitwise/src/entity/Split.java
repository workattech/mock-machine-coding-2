package entity;
public abstract class Split {
    private User user;

    double amount;

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }
}
