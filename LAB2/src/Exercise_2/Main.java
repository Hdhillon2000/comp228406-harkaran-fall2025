package Exercise_2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean hasWon = false;
        int userGuess = 0;

        for (int attempts = 0; attempts < 5; attempts++) {
            String input = JOptionPane.showInputDialog(null,
                    "Attempt " + (attempts + 1) + ": Enter a number between 3 and 27:",
                    "Lotto Game", JOptionPane.QUESTION_MESSAGE);

            try {
                userGuess = Integer.parseInt(input);
                if (userGuess < 3 || userGuess > 27) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 3 and 27.");
                    attempts--;
                    continue;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                attempts--;
                continue;
            }

            Lotto lotto = new Lotto();
            int[] numbers = lotto.getNumbers();
            int sum = lotto.getSum();

            String nums = numbers[0] + ", " + numbers[1] + ", " + numbers[2];

            if (userGuess == sum) {
                JOptionPane.showMessageDialog(null,
                        "You WON! \nYour guess: " + userGuess +
                                "\nLotto numbers: " + nums + "\nSum: " + sum);
                hasWon = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null,
                        "No match this time.\nYour guess: " + userGuess +
                                "\nLotto numbers: " + nums + "\nSum: " + sum);
            }
        }

        if (!hasWon) {
            JOptionPane.showMessageDialog(null,
                    "Sorry, you lost. The computer wins!");
        }
    }
}
