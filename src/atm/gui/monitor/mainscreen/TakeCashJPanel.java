//TODO TakeCashJPanel
package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import webs.layout.CenterLayout;
import atm.gui.MyGUISettings;
import atm.gui.monitor.sidebuttons.SideButtons;

public class TakeCashJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String[] commands = { "", "", "", "", "", "", "", "" };

	/**
	 * Create the panel.
	 */
	public TakeCashJPanel() {
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new CenterLayout());
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JLabel lblPleaseTakeYour = new JLabel("Please take your cash");
		add(lblPleaseTakeYour);
		// lblPleaseTakeYour.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseTakeYour.setFont(new Font("Arial", Font.PLAIN, 26));
	}

	public static void showMe() {
		SideButtons.commands = TakeCashJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CASH);		
	}
}
