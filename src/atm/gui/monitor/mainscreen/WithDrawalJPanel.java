package atm.gui.monitor.mainscreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Button;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import atm.core.ATM;
import atm.exception.CardOutException;
import atm.exception.WrongInputException;
import atm.gui.MyGUISettings;
import atm.gui.keypad.KeypadJFrame;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.utils.MyStaticStuff;
import atm.utils.MyStrings;
import bank.operation.Withdrawal;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;

public class WithDrawalJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<WithDrawalJPanel> contents = new Vector<WithDrawalJPanel>();

	public static final String STRING_MAIN_MENU = "Main Menu";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = {
			String.valueOf(MyStaticStuff.MenuCashValue[0]),
			String.valueOf(MyStaticStuff.MenuCashValue[1]),
			String.valueOf(MyStaticStuff.MenuCashValue[2]),
			String.valueOf(MyStaticStuff.MenuCashValue[3]), STRING_MAIN_MENU,
			STRING_TAKE_CARD, "", "" };
	private JTextField textField;
	private Withdrawal withdrawalOperation;

	/** constructor **/
	public WithDrawalJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(75);
		panel_1.add(verticalStrut_1);

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		panel.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Box horizontalBox = Box.createHorizontalBox();
		add(horizontalBox, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("HKD $ ");
		horizontalBox.add(lblNewLabel);

		textField = new JTextField("");
		horizontalBox.add(textField);
		textField.setPreferredSize(new Dimension(400, 25));
		textField.setColumns(10);
		textField.setBackground(new Color(135, 206, 250));

		for (int i = 0; i < 4; i++) {
			Button button = new Button(MyStrings.DOLLAR_SIGN + " "
					+ commands[i]);
			button.setFont(new Font("Arial", Font.PLAIN, 26));
			panel.add(button);
		}
		for (int i = 4; i < 8; i++) {
			Button button = new Button(commands[i]);
			button.setFont(new Font("Arial", Font.PLAIN, 26));
			panel.add(button);
		}
	}

	/** instance methods **/
	public void sideButtonClick(String command) {
		textField.setText(command);
		tryWithDrawal();
	}

	public void enterButtonClick() {
		tryWithDrawal();
	}

	public void showMe() {
		withdrawalOperation = new Withdrawal(ATM.getATM());
		ATM.getATM().init();
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_WITHDRAWAL;
		SideButtons.commands = WithDrawalJPanel.commands;
		KeypadJFrame.switchTargetStatic(textField,
				KeypadJFrame.STRING_MODE_CASH_AMOUNT);
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_WITHDRAWAL);
	}

	public void tryWithDrawal() {
		// TODO tryWithDrawal
		try {
			withdrawalOperation.setAmount(textField.getText());
			withdrawalOperation.execute();
		} catch (NumberFormatException e) {
			System.out.println("Error! cash amount is not int?");
			textField.setText("");
		} catch (AccountNotFoundException e) {
			CardNotValidJPanel.showMe();

			e.printStackTrace();
		} catch (WrongInputException e) {
			e.printStackTrace();
		} catch (CardOutException e) {
			e.printStackTrace();
		}
	}

	/** static methods **/
	public static void sideButtonClickStatic(String command) {
		for (WithDrawalJPanel withDrawalJPanel : contents) {
			withDrawalJPanel.sideButtonClick(command);
		}
	}

	public static void showMeStatic() {
		for (WithDrawalJPanel withDrawalJPanel : contents) {
			withDrawalJPanel.showMe();
		}
	}

	public static void enterButtonClickStatic() {
		for (WithDrawalJPanel withDrawalJPanel : contents) {
			withDrawalJPanel.enterButtonClick();
		}
	}
}