package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Component;

import javax.swing.Box;

import java.awt.BorderLayout;

import atm.gui.MyGUISettings;

public class LoginJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;

	public LoginJPanel() {
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
}