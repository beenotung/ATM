package atm.gui.monitor.mainscreen;

import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.MyGUISettings;
import atm.utils.MyStrings;

public class TransferSameAccountJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<TransferSameAccountJPanel> contents = new Vector<TransferSameAccountJPanel>();

	public TransferSameAccountJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);

		JLabel label = new JLabel(MyStrings.TRANSFER_SAME_ACCOUNT);
		label.setFont(MyGUISettings.getFont(26));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
	}

	public void showMe() {
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TRANSFER_SAME_ACCOUNT);
		TransferJPanel.waitReturnFromWrongStatic();
	}

	public static void showMeStatic() {
		for (TransferSameAccountJPanel content : contents) {
			content.showMe();
		}
	}
}
