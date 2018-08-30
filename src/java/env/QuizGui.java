package env;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

public class QuizGui extends JFrame {

	private QuizEnv2 quizEnv2;
	private JLabel questionLabel;
	private JTextField answerTextField;
	
	public QuizGui(QuizEnv2 env) {
		this.quizEnv2 = env;
		initialize();
	}
	
	public void updateQuestion(String question) {
		questionLabel.setText(question.replaceAll("\"", ""));
		answerTextField.setText("");
		answerTextField.setEnabled(true);
		answerTextField.setToolTipText("Type your answer here!");
	}
	
	public void initialize() {
		
		Container c = getContentPane();
		
		//GridLayout layout = new GridLayout(3, 1);
		c.setLayout(null);
		
		questionLabel = new JLabel("Iniciando o Quiz");
		//questionLabel.setSize(new Dimension(600, 100));
		questionLabel.setBounds(100, 100, 600, 50);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setVerticalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		answerTextField = new JTextField();
		answerTextField.setBounds(100, 200, 600, 50);
		answerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		answerTextField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					quizEnv2.registerAnswer(answerTextField.getText());
					answerTextField.setEnabled(false);
				}
			}
		});
		
		c.add(questionLabel);
		c.add(answerTextField);
		
		setSize(800, 600);
		setVisible(true);
		
	}
	
}
