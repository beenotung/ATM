package atm.gui.monitor.mainscreen;

import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.MyGUISettings;

public class CashRequiredNotSupportedJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<CashRequiredNotSupportedJPanel> contents = new Vector<CashRequiredNotSupportedJPanel>();

	public CashRequiredNotSupportedJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);

		JLabel label = new JLabel(
				"Cash Notes required is not supported by this ATM");
		label.setFont(MyGUISettings.getFont(26));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
	}

	public static void showMe() {
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_CASH_REQUIRED_NOT_SUPPORTED);
		WithDrawalJPanel.waitReturnFromWrongStatic();
	}
}
