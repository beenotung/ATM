package atm.gui.mainscreen;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Button;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import atm.core.ATM;
import atm.gui.MonitorJFrame;
import atm.gui.MyGUISettings;
import atm.gui.sidebuttons.SideButtons;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TransferJPanel extends JPanel {
	private static Vector<TransferJPanel> contents = new Vector<TransferJPanel>();

	public static final String STRING_VIEW_BALANCE = "View Balance";
	public static final String STRING_WITHDRAW_CASH = "Withdraw Cash";
	public static final String STRING_TRANSFER_FUNDS = "Transfer Funds";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", STRING_VIEW_BALANCE, STRING_WITHDRAW_CASH,
			STRING_TRANSFER_FUNDS, STRING_TAKE_CARD, "", "" };
	private JTextField textField;
	private JTextField textField_1;

	public TransferJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Receiver Ammo");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);

		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);

	}

	public static void showMe() {
		ATM.getATM().init();
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_TRANSFER;
		SideButtons.commands = TransferJPanel.commands;
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_TRANSFER);
	}
}
