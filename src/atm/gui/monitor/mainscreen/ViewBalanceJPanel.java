package atm.gui.monitor.mainscreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JPanel;

import atm.core.ATM;
import atm.exception.CardOutException;
import atm.exception.CashNotesNotSupportedException;
import atm.exception.WrongInputException;
import atm.gui.MyGUISettings;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.gui.virtualslots.cardslot.CardSlotCardJPanel;
import atm.utils.MyImages;
import bank.operation.Transaction;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class ViewBalanceJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<ViewBalanceJPanel> contents = new Vector<ViewBalanceJPanel>();
	public static final String STRING_MAIN_MENU = "Main Menu";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", "", "", STRING_MAIN_MENU,
			STRING_TAKE_CARD, STRING_MAIN_MENU, STRING_TAKE_CARD };

	GUIPrinter guiPrinter;
	private JTextArea text;

	public ViewBalanceJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		add(topPanel);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		JLabel label = new JLabel(MyImages.viewBalance);
		topPanel.add(label);
		label.setAlignmentX(0.5f);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		add(contentPanel);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel infoPanel1 = new JPanel();
		contentPanel.add(infoPanel1);
		infoPanel1.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		infoPanel1.setLayout(new BorderLayout(0, 0));

		text = new JTextArea();
		infoPanel1.add(text);
		text.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		text.setFont(MyGUISettings.getFont(24));
		text.setSize(400, 150);
		infoPanel1.setSize(400, 150);
		text.setPreferredSize(new Dimension(400, 150));
		infoPanel1.setPreferredSize(new Dimension(40, 150));

		JPanel strucPanel = new JPanel();
		contentPanel.add(strucPanel);
		strucPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		strucPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel menuPanel1 = new JPanel();
		strucPanel.add(menuPanel1);
		menuPanel1.setLayout(new GridLayout(1, 2, 0, 0));
		menuPanel1.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Button button_1 = new Button(STRING_MAIN_MENU);
		menuPanel1.add(button_1);
		button_1.setFont(new Font("Arial", Font.PLAIN, 26));
		button_1.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Button button_2 = new Button(STRING_TAKE_CARD);
		menuPanel1.add(button_2);
		button_2.setFont(new Font("Arial", Font.PLAIN, 26));
		button_2.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JPanel menuPanel2 = new JPanel();
		strucPanel.add(menuPanel2);
		menuPanel2.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		menuPanel2.setLayout(new GridLayout(1, 2, 0, 0));

		Component verticalStrut_1 = Box.createVerticalStrut(25);
		add(verticalStrut_1);

		guiPrinter = new GUIPrinter(text);
	}

	public void loadinfo() {
		text.setText("");
		try {
			Vector<Transaction> currentTransactions;
			currentTransactions = ATM.getATM().createTransactions(
					ATM.BALANCE_INQUIRY);
			if (currentTransactions == null) {
				MainMenuJPanel.showMe();
			}
			// execute transaction
			guiPrinter.start();
			for (Transaction currentTransaction : currentTransactions)
				currentTransaction.execute();
		} catch (AccountNotFoundException e) {
			CardNotValidJPanel.showMe();
		} catch (CardOutException e) {
			CardSlotCardJPanel.popCardStatic();
		} catch (WrongInputException e) {
			MaxWrongTryJPanel.showMe();
		} catch (CashNotesNotSupportedException e) {
			// impossible
		}
		guiPrinter.stop();
	}

	private void showMe() {
		loadinfo();
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_VIEW_BALANCE;
		SideButtons.commands = ViewBalanceJPanel.commands;
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_VIEW_BALANCE);
	}

	public static void showMeStatic() {
		try {
			// contents.removeAllElements();
			// new ViewBalanceJPanel();
			for (ViewBalanceJPanel content : contents) {
				content.showMe();
			}
		} catch (ConcurrentModificationException e) {
			// this is expected to happen normally
		}
	}
}
