package atm.gui.monitor.mainscreen;

import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.MyGUISettings;

public class TransferReceiverAccountNotFoundJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<TransferReceiverAccountNotFoundJPanel> contents = new Vector<TransferReceiverAccountNotFoundJPanel>();

	public TransferReceiverAccountNotFoundJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);

		JLabel label = new JLabel("Receiver Account Not Found");
		label.setFont(MyGUISettings.getFont(26));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
	}

	public void showMe() {
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TRANSFER_RECEIVER_ACCOUNT_NOT_FOUND);
		TransferJPanel.waitReturnFromWrongStatic();
	}

	public static void showMeStatic() {
		for (TransferReceiverAccountNotFoundJPanel content : contents) {
			content.showMe();
		}
	}
}
