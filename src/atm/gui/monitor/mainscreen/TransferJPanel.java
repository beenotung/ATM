//TODO TransferJPanel
package atm.gui.monitor.mainscreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JPanel;

import java.util.Vector;

import javax.swing.BoxLayout;

import java.awt.Component;

import atm.core.ATM;
import atm.exception.OverdrawnException;
import atm.exception.TransferSameAccountException;
import atm.gui.MyGUISettings;
import atm.gui.keypad.KeypadJFrame;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.utils.MyStaticStuff;
import bank.operation.Transfer;

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

	private int wrongTry;
	private JTextField receiverAccountNumberTextField;
	private JTextField amountTextField;

	public TransferJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblExtraCharge = new JLabel(MyStaticStuff.getExtraChargeString());
		lblExtraCharge.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblExtraCharge);
		lblExtraCharge.setFont(MyGUISettings.getFont(18));

		JLabel lblNewLabel = new JLabel("Receiver Account Number:");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);
		lblNewLabel.setFont(MyGUISettings.getFont(26));

		receiverAccountNumberTextField = new JTextField();
		add(receiverAccountNumberTextField);
		receiverAccountNumberTextField.setColumns(10);
		receiverAccountNumberTextField.setBackground(MyGUISettings
				.getATMScreenBackGroundColor());

		JLabel lblNewLabel_1 = new JLabel("Amount to transfer:");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(MyGUISettings.getFont(26));

		amountTextField = new JTextField();
		add(amountTextField);
		amountTextField.setColumns(10);
		amountTextField.setBackground(MyGUISettings
				.getATMScreenBackGroundColor());

		wrongTry = 0;
	}

	/** instance methods **/
	public void enterKeyPressed() {
		switch (KeypadJFrame.getModeStatic()) {
		case KeypadJFrame.STRING_MODE_ACCOUNTNUMBER:
			KeypadJFrame.switchTargetStatic(amountTextField,
					KeypadJFrame.STRING_MODE_AMOUNT);
			break;
		case KeypadJFrame.STRING_MODE_AMOUNT:
			tryTransfer();
			break;
		}
	}

	private void tryTransfer() {
		try {
			double amount = Double.parseDouble(amountTextField.getText());
			// TODO Auto-generated method stub
			Transfer.transferGUI(ATM.getATM(),
					receiverAccountNumberTextField.getText(), amount);
		} catch (NumberFormatException e) {
			MainMenuJPanel.showMe();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransferSameAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OverdrawnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showMe() {
		System.out.println("show transfer jpanel");

		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_TRANSFER;
		SideButtons.commands = TransferJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TRANSFER);

		receiverAccountNumberTextField.setText("");
		amountTextField.setText("");
		KeypadJFrame.switchTargetStatic(receiverAccountNumberTextField,
				KeypadJFrame.STRING_MODE_ACCOUNTNUMBER);
	}

	public void showMeWrong() {
		int oldWrongTry = wrongTry;
		showMe();
		wrongTry = oldWrongTry + 1;
	}

	public static void showMeStatic() {
		for (TransferJPanel content : contents) {
			content.showMe();
		}
	}

	public static void showMeWrongStatic() {
		for (TransferJPanel content : contents) {
			content.showMeWrong();
		}
	}

	public static void enterKeyPressedStatic() {
		for (TransferJPanel transferJPanel : contents) {
			transferJPanel.enterKeyPressed();
		}
	}

	/** static methods **/
	public static void waitReturnFromWrongStatic() {
		waitReturnFromWrongThread returnFromWrongThread = new waitReturnFromWrongThread();
		returnFromWrongThread.start();
	}

	/** private class **/
	private static class waitReturnFromWrongThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			showMeWrongStatic();
		}
	}
}
