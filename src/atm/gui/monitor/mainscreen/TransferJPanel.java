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
import atm.utils.MyInputHandler;
import atm.utils.MyStaticStuff;
import bank.account.Account;
import bank.operation.Transfer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;

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

	private Component verticalStrut;

	private JLabel lblExtraCharge;

	public TransferJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);

		lblExtraCharge = new JLabel(MyStaticStuff.getExtraChargeString());
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
		System.out.println("enter");
		switch (KeypadJFrame.getModeStatic()) {
		case KeypadJFrame.STRING_MODE_ACCOUNTNUMBER:
			System.out.println("next");
			KeypadJFrame.switchTargetStatic(amountTextField,
					KeypadJFrame.STRING_MODE_AMOUNT);
			break;
		case KeypadJFrame.STRING_MODE_AMOUNT:
			System.out.println("try");
			tryTransfer();
			break;
		}
	}

	private void tryTransfer() {
		boolean transferSuccess = false;
		try {
			System.out.println("check Transfer");
			double amount = Double.parseDouble(amountTextField.getText());
			Transfer.transferGUI(ATM.getATM(),
					receiverAccountNumberTextField.getText(), amount);
			transferSuccess = true;
		} catch (NumberFormatException e) {
			System.out.println("not double?");
			MainMenuJPanel.showMe();
		} catch (AccountNotFoundException e) {
			System.out.println("account not found");
			TransferReceiverAccountNotFoundJPanel.showMeStatic();
		} catch (TransferSameAccountException e) {
			System.out.println("tranfer same account");
			TransferSameAccountJPanel.showMeStatic();
		} catch (OverdrawnException e) {
			System.out.println("overdrawn");
			OverdrawnJPanel.showMeStatic(MainScreenCardJPanel.STRING_TRANSFER);
		}
		if (transferSuccess) {
			// transfer success
			System.out.println("transfer success");
			TransferSuccessJPanel.showMeStatic();
		}
	}

	public void showMe() {
		System.out.println("show transfer jpanel");

		if (Account.isMyBankAccount(ATM.getATM().getCurrentAccountNumber())) {
			verticalStrut.setVisible(true);
			lblExtraCharge.setVisible(false);
		} else {
			verticalStrut.setVisible(false);
			lblExtraCharge.setVisible(true);
		}

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
		if (wrongTry > MyInputHandler.MAX_WRONG_INPUT)
			MaxWrongTryJPanel.showMe();
	}

	/** static connector to instance stuff **/
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
		WaitReturnFromWrongThread returnFromWrongThread = new WaitReturnFromWrongThread();
		returnFromWrongThread.start();
	}

	/** private class **/
	private static class WaitReturnFromWrongThread extends Thread {
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
