package exercise3;

public class BusinessMortgage extends Mortgage {
    public BusinessMortgage(String mortgageNumber, String customerName, double amount, int term, double primeRate) {
        super(mortgageNumber, customerName, amount, term);
        setInterestRate(primeRate);
    }

    @Override
    public void setInterestRate(double primeRate) {
        // 1% (0.01) above prime
        this.interestRate = primeRate + 0.01;
    }
}
