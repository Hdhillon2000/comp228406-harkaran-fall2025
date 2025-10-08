package exercise1;

public class Life extends Insurance {

    public Life(double baseFee) {
        super(baseFee);
        this.type = "Life";
    }

    @Override
    public void setInsuranceCost() {
        // Example rule: life insurance adds 15% admin fee
        this.monthlyCost = baseFee * 1.15;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Insurance Type: %s | Base fee: $%.2f | Monthly cost: $%.2f%n",
                type, baseFee, monthlyCost);
    }
}
