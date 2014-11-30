package atm.gui.monitor.mainscreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import bank.BankDatabase;
import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.utils.MyStrings;

import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;

public class OverdrawnJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<OverdrawnJPanel> contents = new Vector<OverdrawnJPanel>();

	public OverdrawnJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);

		JLabel label = new JLabel("Overdrawn: Loading...");
		label.setFont(MyGUISettings.getFont(26));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
	}

	public void myUpdate() throws AccountNotFoundException {
		double overdrawnLimit = BankDatabase.getAccount(
				ATM.getATM().getCurrentAccountNumber()).getOverdrawnLimit();
		Vector<JLabel> overDrawnMessageLabels = MyStrings
				.getOverDrawnMessageLabels(overdrawnLimit);
		removeAll();
		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);
		for (JLabel jLabel : overDrawnMessageLabels) {
			add(jLabel);
			jLabel.setFont(MyGUISettings.getFont(26));
			jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			Component verticalGlue_1 = Box.createVerticalGlue();
			add(verticalGlue_1);
		}
	}

	public void showMe() {
		try {
			myUpdate();
			MainScreenCardJPanel
					.switchToCardStatic(MainScreenCardJPanel.STRING_OVERDRAWN);
			WithDrawalJPanel.waitReturnFromWrongStatic();
		} catch (AccountNotFoundException e) {
			CardNotValidJPanel.showMe();
		}

	}

	public static void showMeStatic() {
		for (OverdrawnJPanel overdrawnExceptionJPanel : contents) {
			overdrawnExceptionJPanel.showMe();
		}
	}
}
