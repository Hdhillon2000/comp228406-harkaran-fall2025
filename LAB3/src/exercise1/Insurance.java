package exercise1;

public abstract class Insurance {
    protected String type;
    protected double monthlyCost;
    protected double baseFee;

    public Insurance(double baseFee) {
        this.baseFee = baseFee;
    }

    public String getType() {
        return type;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public abstract void setInsuranceCost();
    public abstract void displayInfo();
}
