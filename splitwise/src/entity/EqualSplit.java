package entity;

public class EqualSplit extends Split{
    public EqualSplit(final User user, Integer countOfUsers, final double totalAmount) {
        super(user, totalAmount/countOfUsers);
    }
}
