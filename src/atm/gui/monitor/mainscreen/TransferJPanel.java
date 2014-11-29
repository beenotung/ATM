//TODO TransferJPanel
package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;

import java.util.Vector;

import javax.swing.BoxLayout;

import java.awt.Component;

import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TransferJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<TransferJPanel> contents = new Vector<TransferJPanel>();

	public static final String STRING_VIEW_BALANCE = "View Balance";
	public static final String STRING_WITHDRAW_CASH = "Withdraw Cash";
	public static final String STRING_TRANSFER_FUNDS = "Transfer Funds";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", STRING_VIEW_BALANCE,
			STRING_WITHDRAW_CASH, STRING_TRANSFER_FUNDS, STRING_TAKE_CARD, "",
			"" };
	private JTextField ReceiverAccountNumberTextField;
	private JTextField amountTextField;

	public TransferJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Receiver Account Number:");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);

		ReceiverAccountNumberTextField = new JTextField();
		add(ReceiverAccountNumberTextField);
		ReceiverAccountNumberTextField.setColumns(10);
		ReceiverAccountNumberTextField.setBackground(MyGUISettings
				.getATMScreenBackGroundColor());

		JLabel lblNewLabel_1 = new JLabel("Amount to transfer:");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_1);

		amountTextField = new JTextField();
		add(amountTextField);
		amountTextField.setColumns(10);
		amountTextField.setBackground(MyGUISettings
				.getATMScreenBackGroundColor());
	}

	public static void showMe() {
		System.out.println("show transfer jpanel");
		ATM.getATM().init();
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_TRANSFER;
		SideButtons.commands = TransferJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TRANSFER);
	}
}
