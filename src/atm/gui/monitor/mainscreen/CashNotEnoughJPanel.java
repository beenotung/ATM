package atm.gui.monitor.mainscreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import bank.BankDatabase;
import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.utils.MyImages;
import atm.utils.MyStrings;

import java.awt.Button;
import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;

import java.awt.GridLayout;

public class CashNotEnoughJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<CashNotEnoughJPanel> contents = new Vector<CashNotEnoughJPanel>();

	public static final String STRING_MAIN_MENU = "Main Menu";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", "", "", STRING_MAIN_MENU,
			STRING_TAKE_CARD, "", "" };

	public CashNotEnoughJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JLabel label = new JLabel(MyImages.cashNotEnough);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		JPanel panel = new JPanel();
		add(panel);
		panel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		panel.setLayout(new GridLayout(4, 2, 0, 0));

		for (int i = 0; i < 4; i++)
			panel.add(new Button());
		panel.add(new Button(STRING_MAIN_MENU));
		panel.add(new Button(STRING_TAKE_CARD));
		for (int i = 0; i < 2; i++)
			panel.add(new Button());

		Component verticalStrut = Box.createVerticalStrut(25);
		add(verticalStrut);
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
		SideButtons.commands = WithDrawalJPanel.commands;
		MonitorJFrame.STATE=MainScreenCardJPanel.STRING_CASH_NOT_ENOUGH;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_CASH_NOT_ENOUGH);
	}

	public static void showMeStatic() {
		for (CashNotEnoughJPanel overdrawnExceptionJPanel : contents) {
			overdrawnExceptionJPanel.showMe();
		}
	}
}
