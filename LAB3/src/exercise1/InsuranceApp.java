package exercise1;

import java.util.Scanner;

public class InsuranceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many insurance policies will you enter? ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) n = 1;
        } catch (Exception e) {
            n = 1;
        }

        Insurance[] policies = new Insurance[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("%nPolicy %d%n", i + 1);
            System.out.print("Enter type (Life or Health): ");
            String type = sc.nextLine().trim();

            System.out.print("Enter base monthly fee (e.g. 100.50): ");
            double fee;
            try {
                fee = Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                fee = 0.0;
            }

            if (type.equalsIgnoreCase("Life")) {
                policies[i] = new Life(fee);
            } else {
                // default to Health if input isn't Life
                policies[i] = new Health(fee);
            }
        }

        System.out.println("\n--- Applying setInsuranceCost() polymorphically and displaying results ---");
        for (Insurance ins : policies) {
            ins.setInsuranceCost();    // polymorphic call
            ins.displayInfo();         // polymorphic call
        }

        sc.close();
    }
}
