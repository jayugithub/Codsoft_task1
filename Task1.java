import javax.swing.*;
import java.util.Random;

public class GuessNumber {
    private int lowerBound;
    private int upperBound;
    private int targetNumber;
    private int maxAttempts;
    private int score;

    public GuessNumber(int lowerBound, int upperBound, int maxAttempts) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.maxAttempts = maxAttempts;
        this.score = 0;
        generateTargetNumber();
    }

    private void generateTargetNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public void playGame() {
        for (int round = 1; ; round++) {
            int attempts = 0;
            int guess;
            boolean isCorrect = false;

            while (attempts < maxAttempts) {
                String input = JOptionPane.showInputDialog(null,
                        "Round " + round + " - Guess the number between " + lowerBound + " and " + upperBound);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                }

                guess = Integer.parseInt(input);
                attempts++;

                if (guess == targetNumber) {
                    isCorrect = true;
                    break;
                } else if (guess < targetNumber) {
                    JOptionPane.showMessageDialog(null, "Try a higher number.");
                } else {
                    JOptionPane.showMessageDialog(null, "Try a lower number.");
                }
            }

            if (isCorrect) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.");
                score += (maxAttempts - attempts + 1);
            } else {
                JOptionPane.showMessageDialog(null, "Out of attempts. The number was: " + targetNumber);
            }

            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play another round?", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                break;
            } else {
                generateTargetNumber();
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over!\nFinal Score: " + score);
    }

    public static void main(String[] args) {
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 7;

        GuessNumber game = new GuessNumber(lowerBound, upperBound, maxAttempts);
        game.playGame();
    }
}