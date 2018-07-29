import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuessingGame extends JFrame {
	private JTextField txtGuess;
	private JLabel lblOutput;
	private int theNumber;
	private int triesLeft;
	
	
	public void checkGuess() {
		String guessText = txtGuess.getText();
	
		try {
			int guess = Integer.parseInt(guessText);
			triesLeft--;
			if(guess > theNumber) {
				lblOutput.setText("Too high. Try again. You have " + triesLeft + " times left.");
			} else if (guess < theNumber) {
				lblOutput.setText("Too low. Try again. You have " + triesLeft + " times left.");
			} else {
				int triesInTotal = 7 - triesLeft; 
				lblOutput.setText("Congratulation! The answer is: " + theNumber + ". It took " + triesInTotal + " times.");
				setTheNumberAndTries();
			}
		} catch (Exception e) {
			lblOutput.setText("Please enter the whole number between 1 and 100");
		} finally {
			txtGuess.requestFocus();
			txtGuess.selectAll();
		}	
		if (triesLeft <= 0) {
			int result = javax.swing.JOptionPane.showConfirmDialog(null , "You lost. The number was "
					+ theNumber + "\n Do you want play again?");
			if (result == JOptionPane.YES_OPTION) {
				newGame();
			} else if (result == JOptionPane.NO_OPTION) {
				dispose();
			}
			
		}
	}
	
	public int getRandomNumbet() {
		return (int)(Math.random()*100 + 1);
	}
	public void setTheNumberAndTries() {
		theNumber = this.getRandomNumbet();
		triesLeft = 7;
	}
	
	public void newGame() {
		lblOutput.setText("Enter the number and click Guess! You have 7 tries!");
		setTheNumberAndTries();
	}
	
	public GuessingGame() {
		setTitle("Guessing Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblGuessingGame = new JLabel("Guessing Game");
		lblGuessingGame.setBounds(0, 36, 434, 20);
		lblGuessingGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGuessingGame.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblGuessingGame);
		
		JButton btnGuess = new JButton("Guess");
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkGuess();
			}
		});
		btnGuess.setBounds(177, 151, 80, 23);
		getContentPane().add(btnGuess);
		
		lblOutput = new JLabel("Enter the number and click Guess!");
		lblOutput.setBounds(36, 216, 354, 14);
		getContentPane().add(lblOutput);
		
		JPanel panel = new JPanel();
		panel.setBounds(36, 96, 342, 23);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtGuess = new JTextField();
		txtGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		txtGuess.setBounds(294, 0, 48, 20);
		panel.add(txtGuess);
		txtGuess.setColumns(10);
		
		JLabel lblGuessANumber = new JLabel("Guess a number between 1 and 100:");
		lblGuessANumber.setBounds(0, 0, 268, 20);
		panel.add(lblGuessANumber);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		btnNewGame.setBounds(10, 11, 121, 23);
		getContentPane().add(btnNewGame);
		
		this.newGame();
	}

	public static void main(String[] args) {

		GuessingGame theGame = new GuessingGame();
		theGame.setSize(new Dimension(430,300));
		theGame.setVisible(true);

	}
}
