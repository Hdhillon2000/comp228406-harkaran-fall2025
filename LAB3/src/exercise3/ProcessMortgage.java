package exercise3;

import java.util.Scanner;

public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter current prime rate (as percentage, e.g. 5 for 5%): ");
        double primePercent = Double.parseDouble(sc.nextLine().trim());
        double primeRate = primePercent / 100.0; // convert to decimal

        Mortgage[] mortgages = new Mortgage[3];

        for (int i = 0; i < mortgages.length; i++) {
            System.out.printf("%nMortgage %d%n", i + 1);
            System.out.print("Enter mortgage type (B for Business, P for Personal): ");
            String type = sc.nextLine().trim();

            System.out.print("Enter mortgage number: ");
            String number = sc.nextLine().trim();

            System.out.print("Enter customer name: ");
            String name = sc.nextLine().trim();

            double amount;
            while (true) {
                System.out.print("Enter mortgage amount (<= " + MortgageConstants.MAX_MORTGAGE + "): ");
                amount = Double.parseDouble(sc.nextLine().trim());
                if (amount <= MortgageConstants.MAX_MORTGAGE) break;
                System.out.println("Amount exceeds maximum. Please enter a valid amount.");
            }

            System.out.print("Enter term in years (1, 3, or 5): ");
            int term;
            try {
                term = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                term = MortgageConstants.SHORT_TERM;
            }
            if (term != MortgageConstants.SHORT_TERM && term != MortgageConstants.MEDIUM_TERM && term != MortgageConstants.LONG_TERM) {
                System.out.println("Invalid term. Setting to short-term (1 year).");
                term = MortgageConstants.SHORT_TERM;
            }

            if (type.equalsIgnoreCase("B")) {
                mortgages[i] = new BusinessMortgage(number, name, amount, term, primeRate);
            } else {
                mortgages[i] = new PersonalMortgage(number, name, amount, term, primeRate);
            }
        }

        System.out.println("\n--- All mortgages ---");
        for (Mortgage m : mortgages) {
            m.getMortgageInfo();
        }

        sc.close();
    }
}
