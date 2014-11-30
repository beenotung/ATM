package atm.gui.monitor.mainscreen;

import java.util.Vector;
import java.awt.Component;

import myutils.gui.cardlayout.AbstractCardJPanel;
import atm.gui.MyGUISettings;
import atm.gui.monitor.MonitorJFrame;

public class MainScreenCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<MainScreenCardJPanel> contents = new Vector<MainScreenCardJPanel>();

	public static final String STRING_WELCOME = "Welcome";
	public static final String STRING_READCARD = "Read Card";
	public static final String STRING_CARD_NOT_VALID = "Card Not Valid";
	public static final String STRING_LOGIN = "Login";
	public static final String STRING_MAIN_MENU = "Main Menu";
	public static final String STRING_VIEW_BALANCE = MainMenuJPanel.STRING_VIEW_BALANCE;
	public static final String STRING_TAKE_CARD = "Take Card";
	public static final String STRING_BYE = "Bye";
	public static final String STRING_MAX_WRONG_TRY = "Max Wrong Try";
	public static final String STRING_WITHDRAWAL = MainMenuJPanel.STRING_WITHDRAW_CASH;
	public static final String STRING_TRANSFER = MainMenuJPanel.STRING_TRANSFER_FUNDS;
	public static final String STRING_TAKE_CASH = "Take Cash";
	public static final String STRING_OVERDRAWN = "Overdrawn";
	public static final String STRING_CASH_NOT_ENOUGH = "Cash Not Enough";
	public static final String STRING_TRANSFER_RECEIVER_ACCOUNT_NOT_FOUND = "Transfer Receiver Account Not Found";
	public static final String STRING_TRANSFER_SAME_ACCOUNT = "Transfer Same Account";
	public static final String STRING_TRANSFER_SUCCESS = "Trasfer Success";

	private ViewBalanceJPanel viewBalanceJPanel;

	public MainScreenCardJPanel() {
		super();
		contents.add(this);
	}

	@Override
	protected void myInit() {
		addToCards(new WelcomeJPanel(), STRING_WELCOME);
		addToCards(new ReadCardJPanel(), STRING_READCARD);
		addToCards(new CardNotValidJPanel(), STRING_CARD_NOT_VALID);
		addToCards(new LoginJPanel(), STRING_LOGIN);
		addToCards(new MainMenuJPanel(), STRING_MAIN_MENU);
		viewBalanceJPanel = new ViewBalanceJPanel();
		addToCards(new MaxWrongTryJPanel(), STRING_MAX_WRONG_TRY);
		addToCards(new TakeCardJPanel(), STRING_TAKE_CARD);
		addToCards(new ByeJPanel(), STRING_BYE);
		addToCards(new WithDrawalJPanel(), STRING_WITHDRAWAL);
		addToCards(new TransferJPanel(), STRING_TRANSFER);
		addToCards(new TakeCashJPanel(), STRING_TAKE_CASH);
		addToCards(new OverdrawnJPanel(), STRING_OVERDRAWN);
		addToCards(new CashNotEnoughJPanel(), STRING_CASH_NOT_ENOUGH);
		addToCards(new TransferReceiverAccountNotFoundJPanel(),
				STRING_TRANSFER_RECEIVER_ACCOUNT_NOT_FOUND);
		addToCards(new TransferSameAccountJPanel(),
				STRING_TRANSFER_SAME_ACCOUNT);
		addToCards(new TransferSuccessJPanel(), STRING_TRANSFER_SUCCESS);

		// switchToCard(STRING_WELCOME);
		WelcomeJPanel.showMeStatic();
	}

	public void renewViewBalanceJPanel() {
		cardLayout.removeLayoutComponent(viewBalanceJPanel);
		viewBalanceJPanel = new ViewBalanceJPanel();
		addToCards(viewBalanceJPanel, STRING_VIEW_BALANCE);
		viewBalanceJPanel.loadinfo();
	}

	@Override
	public void switchToCard(String label) {
		if (label.equals(STRING_VIEW_BALANCE))
			renewViewBalanceJPanel();
		MonitorJFrame.STATE = label;
		super.switchToCard(label);
	}

	/** static connector to instance stuff **/

	public static void switchToCardStatic(String label) {
		for (MainScreenCardJPanel content : contents)
			content.switchToCard(label);
	}

	@Override
	public void addToCards(Component component, String label) {
		super.addToCards(component, label);
		component.setBackground(MyGUISettings.getATMScreenBackGroundColor());
	}

}
