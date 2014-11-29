//TODO TakeCashJPanel
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

public class TakeCashJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel codeLabel;
	public static final String[] commands = { "", "", "", "", "", "", "", "" };

	private ShowCashNotesJPanel showCashNotesJPanel;

	/**
	 * Create the panel.
	 */
	public TakeCashJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JLabel lblPleaseTakeYour = new JLabel("Please the cash note(s)");
		add(lblPleaseTakeYour);
		lblPleaseTakeYour.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseTakeYour.setFont(new Font("Arial", Font.PLAIN, 26));

		showCashNotesJPanel = new ShowCashNotesJPanel();
		add(showCashNotesJPanel);
	}

	public static void showMe() {
		showCashNotesJPanel.myUpdate();
		SideButtons.commands = TakeCashJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
	}
}
