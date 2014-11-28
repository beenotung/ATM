package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import atm.gui.MyGUISettings;
import atm.gui.monitor.sidebuttons.SideButtons;
import myutils.Utils;

public class TakeCardJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel codeLabel;
	public static final String[] commands = { "", "", "", "", "", "", "", "" };

	/**
	 * Create the panel.
	 */
	public TakeCardJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JLabel lblPleaseTakeYour = new JLabel("Please Take your Card");
		panel.add(lblPleaseTakeYour);
		lblPleaseTakeYour.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseTakeYour.setFont(new Font("Arial", Font.PLAIN, 26));

		codeLabel = new JLabel("Reference Code: ");
		codeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(codeLabel);
		codeLabel.setFont(new Font("Arial", Font.PLAIN, 26));

		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);

		genCode();
	}

	public static void genCode() {
		codeLabel.setText("Reference Code: " + (1000 + Utils.random.nextInt(8000)));
	}

	public static void showMe() {
		SideButtons.commands = TakeCardJPanel.commands;
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
	}
}
