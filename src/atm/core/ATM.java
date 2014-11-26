package atm.core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import bank.account.Account;
import bank.database.BankDatabase;
import bank.operation.BalanceInquiry;
import bank.operation.Transaction;
import bank.operation.Transfer;
import bank.operation.Withdrawal;
import atm.gui.mainscreen.MainScreenCardJPanel;
import atm.gui.virtualslots.Card;
import atm.gui.virtualslots.CardSlotCardJPanel;
import atm.utils.CashCount;
import atm.utils.MyInputHandler;
import atm.utils.MyStaticStuff;
import atm.utils.MyStrings;
import myutil.exception.CardOutException;
import myutil.exception.WrongInputException;

// ATM.java
// Represents an automated teller machine

public class ATM {
	private Vector<ATM> atms = new Vector<ATM>();

	private boolean userAuthenticated; // whether user is authenticated
	public static String currentAccountNumber; // current user's account number
	private Screen screen; // ATM's screen
	private Keypad keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private BankDatabase bankDatabase; // account information database
	private UI ui;

	// constants corresponding to main menu options
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int TRANSFER = 3;
	private static final int EXIT = 4;

	// no-argument ATM constructor initializes instance variables
	public ATM() {
		atms.add(this);
		userAuthenticated = false; // user is not authenticated to start
		currentAccountNumber = "0"; // no current account number to start
		screen = new Screen(); // create screen
		keypad = new Keypad(screen); // create keypad
		cashDispenser = new CashDispenser(); // create cash dispenser
		bankDatabase = new BankDatabase(); // create acct info database
		ui = new UI(screen, bankDatabase, keypad);
	} // end no-argument ATM constructor

	/** getters **/
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
		return currentAccountNumber;
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
				authenticateUser();
			} // end while

			try {
				try {
					try {
						performTransactions(BankDatabase.getAccounts());
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
			currentAccountNumber = "0"; // reset before next ATM session
			popCard();
			showBye();
		} // end while
	} // end method run

	// attempts to authenticate user against database
	private void authenticateUser() {
		// get account number from user
		String accountNumber = "";
		String pin = "";
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				accountNumber = String.valueOf(keypad.getInputInt("\nPlease enter your account number: "));
			} catch (WrongInputException e) {
				ok = false;
				wrongCount++;
				screen.displayMessageLine();
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
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
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok) {
			userAuthenticated = false;
			return;
		}
		// set userAuthenticated to boolean value returned by database
		userAuthenticated = BankDatabase.authenticateUser(accountNumber, pin);

		// check whether authentication succeeded
		if (userAuthenticated) {
			currentAccountNumber = accountNumber; // save user's account #
		} // end if
		else {
			screen.displayMessageLine("Invalid account number or PIN. Please try again.");
			MyStaticStuff.sleep();
		}
	} // end method authenticateUser

	// display the main menu and perform transactions
	private void performTransactions(Vector<Account> accounts) throws CardOutException, WrongInputException,
			AccountNotFoundException {
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
				if (++wrongCount > MyInputHandler.MAXWRONGINPUT)
					mainMenuSelection = EXIT;
			}
			// decide how to proceed based on user's menu selection
			switch (mainMenuSelection) {
			// user chose to perform one of three transaction types
			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case TRANSFER:
				currentTransactions = createTransactions(mainMenuSelection, accounts);
				if (currentTransactions == null) {
					userExited = false;
					break;
				}
				// execute transaction
				for (Transaction currentTransaction : currentTransactions)
					currentTransaction.execute();
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
		if (!Account.isMyBankAccount(currentAccountNumber))
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
	private Vector<Transaction> createTransactions(int type, Vector<Account> accounts)
			throws CardOutException, WrongInputException, AccountNotFoundException {
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
		screen.displayMessageLine(MyStrings.TAKE_CASH + " " + MyStaticStuff.getCashValuesStrings(cashPop));
		MyStaticStuff.sleep();
		throw new CardOutException();
	}

	public void showBye() {
		screen.displayMessageLine("\n" + MyStrings.BYE);
		MyStaticStuff.sleep();
	}

	public static boolean checkUserValid(String accountNumber) {
		try {
			Integer.parseInt(accountNumber);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void readCard(Card card) {
		System.out.println("inserted card:" + card.accountNumber);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_READCARD);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		if (checkUserValid(card.accountNumber)) {
			System.out.println("the card is valid");
			MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_LOGIN);
		} else {
			System.out.println(MyStrings.CARD_NOT_VALID);
			MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_CARD_NOT_VALID);
			CardSlotCardJPanel.popCardStatic();
		}
	}
} // end class ATM