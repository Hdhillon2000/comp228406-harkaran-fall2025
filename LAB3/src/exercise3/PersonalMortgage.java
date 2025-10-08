package exercise3;

public class PersonalMortgage extends Mortgage {
    public PersonalMortgage(String mortgageNumber, String customerName, double amount, int term, double primeRate) {
        super(mortgageNumber, customerName, amount, term);
        setInterestRate(primeRate);
    }

    @Override
    public void setInterestRate(double primeRate) {
        // 2% (0.02) above prime
        this.interestRate = primeRate + 0.02;
    }
}
