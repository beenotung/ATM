package atm.core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import bank.BankDatabase;
import bank.account.Account;
import bank.operation.BalanceInquiry;
import bank.operation.Transaction;
import bank.operation.Transfer;
import bank.operation.Withdrawal;
import atm.exception.CardOutException;
import atm.exception.CashNotesNotSupportedException;
import atm.exception.WrongInputException;
import atm.gui.monitor.mainscreen.CardNotValidJPanel;
import atm.gui.monitor.mainscreen.LoginJPanel;
import atm.gui.monitor.mainscreen.MainMenuJPanel;
import atm.gui.monitor.mainscreen.MainScreenCardJPanel;
import atm.gui.monitor.mainscreen.MaxWrongTryJPanel;
import atm.gui.virtualslots.cardslot.Card;
import atm.gui.virtualslots.cardslot.CardInsideJPanel;
import atm.gui.virtualslots.cardslot.CardSlotCardJPanel;
import atm.utils.CashCount;
import atm.utils.MyInputHandler;
import atm.utils.MyStaticStuff;
import atm.utils.MyStrings;

// ATM.java
// Represents an automated teller machine

public class ATM {
	private static ATM atm = null;

	public static boolean userAuthenticated; // whether user is authenticated
	public static String currentAccountNumber; // current user's account number

	private Screen screen; // ATM's screen
	private Keypad keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private BankDatabase bankDatabase; // account information database
	private UI ui;
	private int wrongCount = 0;

	// constants corresponding to main menu options
	public static final int BALANCE_INQUIRY = 1;
	public static final int WITHDRAWAL = 2;
	public static final int TRANSFER = 3;
	public static final int EXIT = 4;

	// no-argument ATM constructor initializes instance variables
	private ATM() {
		screen = new Screen(); // create screen
		keypad = new Keypad(screen); // create keypad
		cashDispenser = new CashDispenser(); // create cash dispenser
		bankDatabase = new BankDatabase(); // create acct info database
		ui = new UI(screen, bankDatabase, keypad);
		init();
	} // end no-argument ATM constructor

	public void init() {
		wrongCount = 0;
	}

	/** getters **/
	public static ATM getATM() {
		if (atm == null)
			atm = new ATM();
		return atm;
	}

	public UI getUI() {
		return ui;
	}

	public BankDatabase getBankDatabase() {
		return bankDatabase;
	}

	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}

	public String getCurrentAccountNumber() {
		try {
			return CardInsideJPanel.getCard().accountNumber;
		} catch (NullPointerException e) {
			return "0";
		}
	}

	/** setters **/
	public void removeAuthentication() {
		userAuthenticated = false;
	}

	/** instance methods **/
	// start ATM
	public void run() {
		screen.clear();
		// welcome and authenticate user; perform transactions
		while (true) {
			// loop while user is not yet authenticated
			while (!userAuthenticated) {
				screen.displayMessageLine("\nWelcome to CC Bank ATM!");
				authenticateUser_old();
			} // end while

			try {
				try {
					try {
						performTransactions();
					} catch (AccountNotFoundException e) {
						screen.displayMessageLine(MyStrings.ACCOUNT_NOT_FOUND);
					}
				} catch (WrongInputException e) {
					screen.displayMessageLine(MyStrings.WRONG_INPUT);
				}
			} // user is now
			catch (CardOutException e) {
				userAuthenticated = false;
			}
			// authenticated
			userAuthenticated = false; // reset before next ATM session
			ATM.currentAccountNumber = "0"; // reset before next ATM session
			popCard();
			showBye();
		} // end while
	} // end method run

	// attempts to authenticate user against database
	@Deprecated
	private void authenticateUser_old() {
		// get account number from user
		String accountNumber = "";
		String pin = "";
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				accountNumber = String.valueOf(keypad
						.getInputInt("\nPlease enter your account number: "));
			} catch (WrongInputException e) {
				ok = false;
				wrongCount++;
				screen.displayMessageLine();
			}
		} while ((wrongCount <= MyInputHandler.MAX_WRONG_INPUT) && (!ok));
		if (!ok) {
			userAuthenticated = false;
			return;
		}
		// end of input account number
		// prompt for PIN
		wrongCount = 0;
		do {
			try {
				pin = String.valueOf(keypad.getInputInt("\nEnter your PIN: "));// input
																				// PIN
			} catch (WrongInputException e) {
				ok = false;
				wrongCount++;
				screen.displayMessageLine();
			}
		} while ((wrongCount <= MyInputHandler.MAX_WRONG_INPUT) && (!ok));
		if (!ok) {
			userAuthenticated = false;
			return;
		}
		// set userAuthenticated to boolean value returned by database
		try {
			userAuthenticated = BankDatabase.authenticateUser_old(
					accountNumber, pin);
		} catch (AccountNotFoundException e) {
			System.out.println("Account not Found");
		}

		// check whether authentication succeeded
		if (userAuthenticated) {
			ATM.currentAccountNumber = accountNumber; // save user's account #
		} // end if
		else {
			screen.displayMessageLine("Invalid account number or PIN. Please try again.");
			MyStaticStuff.sleep();
		}
	} // end method authenticateUser

	public void authenticateUser(String pin) {
		System.out.println("attend to login");
		try {
			// userAuthenticated =
			// BankDatabase.authenticateUser_old(ATM.currentAccountNumber, pin);
			userAuthenticated = BankDatabase.authenticateUser(pin);
		} catch (AccountNotFoundException e) {
			CardNotValidJPanel.showMe();
		}
		if (!userAuthenticated) {
			wrongCount++;
			if (wrongCount <= MyInputHandler.MAX_WRONG_INPUT) {
				System.out.println("wrong pin");
				LoginJPanel.showMeWrongStatic(wrongCount);
			} else {
				System.out.println("too many wrong try");
				MaxWrongTryJPanel.showMe();
			}
		} else {
			System.out.println("logged in");
			MainMenuJPanel.showMe();
		}

	}

	// display the main menu and perform transactions
	private void performTransactions() throws CardOutException,
			WrongInputException, AccountNotFoundException {
		// local variable to store transaction currently being processed
		Vector<Transaction> currentTransactions = null;

		boolean userExited = false; // user has not chosen to exit

		// loop while user has not chosen option to exit system
		while (!userExited) {
			// show main menu and get user selection
			int mainMenuSelection = -1;
			int wrongCount = 0;
			try {
				mainMenuSelection = displayMainMenu();
			} catch (WrongInputException e) {
				if (++wrongCount > MyInputHandler.MAX_WRONG_INPUT)
					mainMenuSelection = EXIT;
			}
			// decide how to proceed based on user's menu selection
			switch (mainMenuSelection) {
			// user chose to perform one of three transaction types
			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case TRANSFER:
				currentTransactions = createTransactions(mainMenuSelection);
				if (currentTransactions == null) {
					userExited = false;
					break;
				}
				// execute transaction
				for (Transaction currentTransaction : currentTransactions)
					try {
						currentTransaction.execute();
					} catch (CashNotesNotSupportedException e) {
						// unimplemented
						System.out
								.println("Cash Notes Not Supported by this ATM");
					}
				if (mainMenuSelection == TRANSFER)
					throw new CardOutException();
				// auto finish the transaction if with WITHDRAWAL success (card
				// out expection)
				break;
			case EXIT: // user chose to terminate session
				userExited = true; // this ATM session should end
				break;
			default: // user did not enter an integer from 1-4
				screen.displayMessageLine("\nYou did not enter a valid selection. Please try again.");
				break;
			} // end switch
			MyStaticStuff.sleep();
		} // end while
	} // end method performTransactions

	// display the main menu and return an input selection
	private int displayMainMenu() throws WrongInputException {
		screen.clear();
		if (!Account.isMyBankAccount(ATM.currentAccountNumber))
			screen.displayMessageLine(MyStaticStuff.getExtraChargeString());
		String msg = "\nMain menu:";
		msg += "\n1 - View my balance";
		msg += "\n2 - Withdraw cash";
		msg += "\n3 - Transfer funds";
		msg += "\n4 - Exit";
		msg += "\n\nEnter a choice: ";
		return keypad.getInputInt(msg); // return user's selection
	} // end method displayMainMenu

	// return object of specified Transaction subclass
	public Vector<Transaction> createTransactions(int type)
			throws CardOutException, WrongInputException,
			AccountNotFoundException {
		System.out.println("ATM:createTransactions (type:" + type + ")");
		// temporary Transaction variable
		Vector<Transaction> result = new Vector<Transaction>();

		// determine which type of Transaction to create
		switch (type) {
		case BALANCE_INQUIRY: // create new BalanceInquiry transaction
			result.add(new BalanceInquiry(this));
			break;
		case WITHDRAWAL: // create new Withdrawal transaction
			result.add(new Withdrawal(this));
			break;
		case TRANSFER: // create new Deposit transaction
			result = Transfer.transfer(this);
			break;
		} // end switch

		return result; // return the newly created object
	} // end method createTransaction

	/** Skipped featured (will be added in GUI) **/
	// instruct user to take card
	public void popCard() {
		screen.displayMessageLine(MyStrings.TAKE_CARD);
		MyStaticStuff.sleep();
	}

	// instruct user to take cash
	public void popCash(Vector<CashCount> cashPop) throws CardOutException {
		screen.displayMessageLine(MyStrings.TAKE_CASH + " "
				+ MyStaticStuff.getCashValuesStrings(cashPop));
		MyStaticStuff.sleep();
		throw new CardOutException();
	}

	@SuppressWarnings("deprecation")
	public void showBye() {
		screen.displayMessageLine("\n" + MyStrings.BYE);
		MyStaticStuff.sleep();
	}

	public static void readCard(Card card) {
		System.out.println("reading inserted card:" + card.accountNumber);
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_READCARD);
		(new WaitReadCard(card)).start();
	}

	public static void checkCard(Card card) {
		try {
			ATM.currentAccountNumber = String.valueOf(Integer
					.parseInt(card.accountNumber));
			System.out.println("the card [" + ATM.currentAccountNumber
					+ "] is valid");
			LoginJPanel.showMeStatic();
		} catch (NumberFormatException e) {
			CardNotValidJPanel.showMe();
		}
	}

	public static void popCardStatic() {
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_CARD_NOT_VALID);
		CardSlotCardJPanel.popCardStatic();
	}

	/** private class **/
	private static class WaitReadCard extends Thread {
		private Card card;

		public WaitReadCard(Card card) {
			this.card = card;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			ATM.checkCard(card);
		}
	}

	/** static connectors to instance methods **/
	public static void initStatic() {
		ATM.atm = new ATM();
		// user is not authenticated to start/restart
		ATM.userAuthenticated = false;
		// no current account number to start/restart
		ATM.currentAccountNumber = "0";
		if (CardSlotCardJPanel.hasCard())
			currentAccountNumber = CardInsideJPanel.getCard().accountNumber;
		atm.init();
	}

} // end class ATM