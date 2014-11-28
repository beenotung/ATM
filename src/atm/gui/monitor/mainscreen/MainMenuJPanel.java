package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Button;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.gui.keypad.KeypadJFrame;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;

import java.awt.Font;

public class MainMenuJPanel extends JPanel {
	private static Vector<MainMenuJPanel> contents = new Vector<MainMenuJPanel>();

	public static final String STRING_VIEW_BALANCE = "View Balance";
	public static final String STRING_WITHDRAW_CASH = "Withdraw Cash";
	public static final String STRING_TRANSFER_FUNDS = "Transfer Funds";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", STRING_VIEW_BALANCE, STRING_WITHDRAW_CASH,
			STRING_TRANSFER_FUNDS, STRING_TAKE_CARD, "", "" };

	public MainMenuJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component verticalStrut_1 = Box.createVerticalStrut(75);
		add(verticalStrut_1);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		panel.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		for (String string : commands) {
			Button button = new Button(string);
			button.setFont(new Font("Arial", Font.PLAIN, 26));
			panel.add(button);
		}

		Component verticalStrut = Box.createVerticalStrut(25);
		add(verticalStrut);
	}

	public static void showMe() {
		ATM.getATM().init();
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_MAIN_MENU;
		SideButtons.commands = MainMenuJPanel.commands;
		KeypadJFrame.switchTargetStatic(KeypadJFrame.STRING_MODE_NULL);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_MAIN_MENU);
	}
}
