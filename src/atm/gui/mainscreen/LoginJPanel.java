package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.text.BadLocationException;

import java.awt.BorderLayout;
import java.util.Vector;

import atm.core.Keypad;
import atm.gui.MyGUISettings;
import atm.gui.keypad.KeypadJFrame;

public class LoginJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<LoginJPanel> contents = new Vector<LoginJPanel>();

	private JPasswordField passwordField;

	public LoginJPanel() {
		contents.add(this);

		setLayout(new BorderLayout(0, 0));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		add(horizontalBox);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);

		JLabel label = new JLabel("Please input the password");
		horizontalBox.add(label);
		label.setFont(MyGUISettings.getFont(26));

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);

		passwordField = new JPasswordField();
		add(passwordField, BorderLayout.SOUTH);
		passwordField.setFont(MyGUISettings.getFont(26));
	}

	/** instance methods **/
	public void showMe() {
		KeypadJFrame.switchTargetStatic(passwordField, KeypadJFrame.STRING_MODE_PASSWORD);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_LOGIN);
	}

	/** static connectors to instance methods **/
	public static void showMeStatic() {
		for (LoginJPanel loginJPanel : contents) {
			loginJPanel.showMe();
		}
	}
}