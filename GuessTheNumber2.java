import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumber2 extends JFrame {
    private Random random;
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private int score;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;

    public GuessTheNumber2() {
        random = new Random();
        attempts = 0;
        maxAttempts = 10;
        score = 0;

        setTitle("Guess the Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel promptLabel = new JLabel("I have generated a random number between 1 and 100.");
        promptLabel.setHorizontalAlignment(JLabel.CENTER);
        add(promptLabel);

        guessTextField = new JTextField();
        add(guessTextField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        add(guessButton);

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        add(resultLabel);

        generateTargetNumber();

        setVisible(true);
    }

    private void generateTargetNumber() {
        targetNumber = random.nextInt(100) + 1;
    }

    private void checkGuess() {
        if (attempts < maxAttempts) {
            attempts++;
            int guess;
            try {
                guess = Integer.parseInt(guessTextField.getText());
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter a number.");
                return;
            }

            if (guess == targetNumber) {
                score = (maxAttempts - attempts) * 10;
                resultLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.\nYour score is: " + score);
                guessButton.setEnabled(false);
            } else if (guess < targetNumber) {
                resultLabel.setText("Incorrect guess. The number is higher than " + guess);
            } else {
                resultLabel.setText("Incorrect guess. The number is lower than " + guess);
            }
        }

        if (attempts == maxAttempts) {
            resultLabel.setText("You have reached the maximum number of attempts.\nThe correct number was: " + targetNumber);
            guessButton.setEnabled(false);
        }

        guessTextField.setText("");
        guessTextField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheNumber2();
            }
        });
    }
}
