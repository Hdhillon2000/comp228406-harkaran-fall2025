package exercise1;

public class Health extends Insurance {

    public Health(double baseFee) {
        super(baseFee);
        this.type = "Health";
    }

    @Override
    public void setInsuranceCost() {
        // Example rule: health insurance adds 10% admin fee
        this.monthlyCost = baseFee * 1.10;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Insurance Type: %s | Base fee: $%.2f | Monthly cost: $%.2f%n",
                type, baseFee, monthlyCost);
    }
}
