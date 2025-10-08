package exercise3;

public abstract class Mortgage implements MortgageConstants {
    protected String mortgageNumber;
    protected String customerName;
    protected double amount;
    protected double interestRate; // as decimal, e.g. 0.05 for 5%
    protected int term; // in years

    public Mortgage(String mortgageNumber, String customerName, double amount, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        if (amount > MAX_MORTGAGE) {
            throw new IllegalArgumentException("Amount exceeds maximum allowed (" + MAX_MORTGAGE + ")");
        }
        this.amount = amount;
        if (term == SHORT_TERM || term == MEDIUM_TERM || term == LONG_TERM) {
            this.term = term;
        } else {
            this.term = SHORT_TERM;
        }
    }

    // subclasses set interest based on prime rate
    public abstract void setInterestRate(double primeRate);

    // total owed (simple interest): amount + amount * interestRate * term
    public double getTotalOwed() {
        return amount + (amount * interestRate * term);
    }

    public void getMortgageInfo() {
        System.out.println("Bank: " + BANK_NAME);
        System.out.println("Mortgage #: " + mortgageNumber);
        System.out.println("Customer: " + customerName);
        System.out.printf("Amount: $%.2f%n", amount);
        System.out.printf("Interest Rate (decimal): %.4f%n", interestRate);
        System.out.println("Term (years): " + term);
        System.out.printf("Total Owed (simple interest): $%.2f%n", getTotalOwed());
        System.out.println("--------------------------------------");
    }
}
