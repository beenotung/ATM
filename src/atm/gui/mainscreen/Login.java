package atm.gui.mainscreen;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPasswordField passwordField;

	public Login(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Please input the password");
		label.setAlignmentY(0.5f);
		label.setAlignmentX(0.5f);
		add(label);
		
		passwordField = new JPasswordField();
		passwordField.setAlignmentY(0.5f);
		passwordField.setAlignmentX(0.5f);
		add(passwordField);

	}
}