package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Component;

import javax.swing.Box;
import java.awt.BorderLayout;
import java.util.Vector;

import atm.gui.MyGUISettings;
import atm.gui.keypad.KeypadJFrame;

public class LoginJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<LoginJPanel> contents = new Vector<LoginJPanel>();

	private JPasswordField passwordField;
	private JLabel lblWrongPassword;

	public LoginJPanel() {
		contents.add(this);

		setLayout(new BorderLayout(0, 0));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		add(horizontalBox);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);

		Box verticalBox = Box.createVerticalBox();
		horizontalBox.add(verticalBox);

		lblWrongPassword = new JLabel("Wrong PIN");
		verticalBox.add(lblWrongPassword);

		JLabel lblInputPassword = new JLabel("Please input the password");
		verticalBox.add(lblInputPassword);
		lblInputPassword.setFont(MyGUISettings.getFont(26));

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);

		passwordField = new JPasswordField();
		add(passwordField, BorderLayout.SOUTH);
		passwordField.setFont(MyGUISettings.getFont(26));
	}

	/** instance methods **/
	public void showMe() {
		lblWrongPassword.setVisible(false);
		passwordField.setText("");
		KeypadJFrame.switchTargetStatic(passwordField, KeypadJFrame.STRING_MODE_PASSWORD);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_LOGIN);
	}

	public void showMeWrong(int wrongCount) {
		lblWrongPassword.setText("Wrong PIN (" + wrongCount + ")");
		lblWrongPassword.setVisible(true);
		passwordField.setText("");
		KeypadJFrame.switchTargetStatic(passwordField, KeypadJFrame.STRING_MODE_PASSWORD);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_LOGIN);
	}

	/** static connectors to instance methods **/
	public static void showMeStatic() {
		for (LoginJPanel loginJPanel : contents) {
			loginJPanel.showMe();
		}
	}

	public static void showMeWrongStatic(int wrongCount) {
		for (LoginJPanel loginJPanel : contents) {
			loginJPanel.showMeWrong(wrongCount);
		}
	}
}