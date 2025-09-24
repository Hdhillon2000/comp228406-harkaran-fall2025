package Exercise_1;

import javax.swing.JOptionPane;
import java.util.Random;

public class Test {
    private String[] questions = {
            "1. What is Java?\nA. A type of OS\nB. A programming language\nC. A browser\nD. A database",
            "2. Which symbol is used for single-line comments in Java?\nA. //\nB. /* */\nC. <!-- -->\nD. #",
            "3. Which method is the entry point of a Java program?\nA. start()\nB. run()\nC. main()\nD. init()",
            "4. Which of the following is NOT a Java primitive type?\nA. int\nB. float\nC. boolean\nD. String",
            "5. Which keyword is used to create an object in Java?\nA. make\nB. new\nC. create\nD. object"
    };

    private char[] answers = { 'B', 'A', 'C', 'D', 'B' };

    private int correctCount = 0;
    private int incorrectCount = 0;

    private Random random = new Random();

    private void simulateQuestion(int index) {
        String response = JOptionPane.showInputDialog(questions[index]);
        if (response != null && !response.isEmpty()) {
            char userAnswer = Character.toUpperCase(response.charAt(0));
            checkAnswer(index, userAnswer);
        } else {
            JOptionPane.showMessageDialog(null, "No answer given. Marked incorrect.");
            incorrectCount++;
        }
    }

    private void checkAnswer(int index, char userAnswer) {
        if (userAnswer == answers[index]) {
            JOptionPane.showMessageDialog(null, generateMessage(true));
            correctCount++;
        } else {
            JOptionPane.showMessageDialog(null, generateMessage(false) +
                    "\nCorrect Answer: " + answers[index]);
            incorrectCount++;
        }
    }

    private String generateMessage(boolean correct) {
        int msgIndex = random.nextInt(4);
        if (correct) {
            switch (msgIndex) {
                case 0: return "Excellent!";
                case 1: return "Good!";
                case 2: return "Keep up the good work!";
                case 3: return "Nice work!";
            }
        } else {
            switch (msgIndex) {
                case 0: return "No. Please try again.";
                case 1: return "Wrong. Try once more.";
                case 2: return "Don't give up!";
                case 3: return "No. Keep trying..";
            }
        }
        return "";
    }

    public void inputAnswer() {
        for (int i = 0; i < questions.length; i++) {
            simulateQuestion(i);
        }
        showResults();
    }

    private void showResults() {
        int total = correctCount + incorrectCount;
        double percentage = (correctCount * 100.0) / total;

        JOptionPane.showMessageDialog(null,
                "Test Completed!\n" +
                        "Correct Answers: " + correctCount + "\n" +
                        "Incorrect Answers: " + incorrectCount + "\n" +
                        "Percentage: " + String.format("%.2f", percentage) + "%"
        );
    }
}
