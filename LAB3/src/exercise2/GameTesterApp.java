package exercise2;

import java.util.Scanner;

public class GameTesterApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many game testers will you enter? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        GameTester[] testers = new GameTester[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("%nTester %d%n", i + 1);
            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter type (F for full-time, P for part-time): ");
            String type = sc.nextLine().trim();

            if (type.equalsIgnoreCase("F")) {
                testers[i] = new FullTimeGameTester(name);
            } else {
                System.out.print("Enter hours worked (integer): ");
                int hours = Integer.parseInt(sc.nextLine().trim());
                testers[i] = new PartTimeGameTester(name, hours);
            }
        }

        System.out.println("\n--- Game testers summary ---");
        for (GameTester t : testers) {
            t.displayInfo();
        }

        sc.close();
    }
}
