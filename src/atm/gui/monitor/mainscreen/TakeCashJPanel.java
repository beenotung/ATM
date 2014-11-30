//TODO TakeCashJPanel
package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;

import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;

import atm.gui.MyGUISettings;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.utils.CashCount;
import myutils.Utils;

public class TakeCashJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String[] commands = { "", "", "", "", "", "", "", "" };

	private ShowPopCashNotesJPanel showPopCashNotesJPanel;

	/**
	 * Create the panel.
	 */
	public TakeCashJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JLabel lblPleaseTakeYour = new JLabel("Please take your cash");
		add(lblPleaseTakeYour);
		lblPleaseTakeYour.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseTakeYour.setFont(new Font("Arial", Font.PLAIN, 26));

		showPopCashNotesJPanel = new ShowPopCashNotesJPanel();
		add(showPopCashNotesJPanel);
	}

	public static void showMe(Vector<CashCount> popCashCounts) {
		ShowPopCashNotesJPanel.myUpdateStatic(popCashCounts);
		SideButtons.commands = TakeCashJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
	}
}
