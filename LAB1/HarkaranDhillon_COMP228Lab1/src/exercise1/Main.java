package exercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Singers singer1 = new Singers();

        System.out.println("Default values after no-arg constructor");
        System.out.println("ID: " + singer1.getId());
        System.out.println("Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums Published: " + singer1.getNumberOfAlbumsPublished());

        singer1.setAll(
                156,
                "Karan Aujla",
                "11 main st, Ottawa, ON",
                "1990-01-01",
                100
        );

        System.out.println("\nAfter setAll");
        printSinger(singer1);

        System.out.println("\nChange values using individual setters");
        System.out.print("Enter new ID: ");
        int newId = readInt(input);

        System.out.print("Enter new Name: ");
        String newName = input.nextLine();

        System.out.print("Enter new Address: ");
        String newAddress = input.nextLine();

        System.out.print("Enter new Date of Birth (YYYY-MM-DD): ");
        String newDob = input.nextLine();

        System.out.print("Enter new Number of Albums Published (integer): ");
        int newAlbums = readInt(input);

        singer1.setId(newId);
        singer1.setName(newName);
        singer1.setAddress(newAddress);
        singer1.setDateOfBirth(newDob);
        singer1.setNumberOfAlbumsPublished(newAlbums);

        System.out.println("\nCurrent values after individual setters");
        System.out.println("ID: " + singer1.getId());
        System.out.println("Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums Published: " + singer1.getNumberOfAlbumsPublished());

        input.close();
    }

    private static void printSinger(Singers s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Address: " + s.getAddress());
        System.out.println("Date of Birth: " + s.getDateOfBirth());
        System.out.println("Number of Albums Published: " + s.getNumberOfAlbumsPublished());
    }

    private static int readInt(Scanner input) {
        while (true) {
            String line = input.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.print("You did not enter integer, enter integer only: ");
            }
        }
    }
}
