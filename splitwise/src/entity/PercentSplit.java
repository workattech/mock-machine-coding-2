
package entity;

public class PercentSplit extends Split{
    double percent;
    public PercentSplit(final User user, double percent, double totalAmount) {

        super(user, (totalAmount*percent/100));
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
