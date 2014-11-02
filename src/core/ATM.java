package core;

import java.util.Vector;

import account.Account;
import ui.Keypad;
import ui.Screen;
import ui.UI;
import myutil.exception.CardOutException;
import myutil.exception.OverdrawnException;

// ATM.java
// Represents an automated teller machine

public class ATM {
	private boolean userAuthenticated; // whether user is authenticated
	private int currentAccountNumber; // current user's account number
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
		userAuthenticated = false; // user is not authenticated to start
		currentAccountNumber = 0; // no current account number to start
		screen = new Screen(); // create screen
		keypad = new Keypad(); // create keypad
		cashDispenser = new CashDispenser(); // create cash dispenser
		bankDatabase = new BankDatabase(); // create acct info database
		ui = new UI(screen, bankDatabase, keypad);
	} // end no-argument ATM constructor

	// start ATM
	public void run() {
		// welcome and authenticate user; perform transactions
		while (true) {
			// loop while user is not yet authenticated
			while (!userAuthenticated) {
				screen.displayMessageLine("\nWelcome!");
				try {
					authenticateUser();
				} catch (CardOutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println();
				} // authenticate user
			} // end while

			try {
				performTransactions(bankDatabase.getAccounts());
			} // user is now
			catch (CardOutException e) {
				exit();
			}
			// authenticated
			userAuthenticated = false; // reset before next ATM session
			currentAccountNumber = 0; // reset before next ATM session
			screen.displayMessageLine("\nThank you! Goodbye!");
		} // end while
	} // end method run

	// attempts to authenticate user against database
	private void authenticateUser() throws CardOutException {
		screen.displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInputInt(); // input account number
		screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
		int pin = keypad.getInputInt(); // input PIN

		// set userAuthenticated to boolean value returned by database
		userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

		// check whether authentication succeeded
		if (userAuthenticated) {
			currentAccountNumber = accountNumber; // save user's account #
		} // end if
		else
			screen.displayMessageLine("Invalid account number or PIN. Please try again.");
	} // end method authenticateUser

	// display the main menu and perform transactions
	private void performTransactions(Vector<Account> accounts) throws CardOutException {
		// local variable to store transaction currently being processed
		Vector<Transaction> currentTransactions = null;

		boolean userExited = false; // user has not chosen to exit

		// loop while user has not chosen option to exit system
		while (!userExited) {
			// show main menu and get user selection
			int mainMenuSelection;
			try {
				mainMenuSelection = displayMainMenu();
			} catch (CardOutException e) {
				mainMenuSelection = EXIT;
			}
			// decide how to proceed based on user's menu selection
			switch (mainMenuSelection) {
			// user chose to perform one of three transaction types
			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case TRANSFER:
				// initialize as new object of chosen type
				try {
					currentTransactions = createTransactions(mainMenuSelection, accounts);
					for (Transaction currentTransaction : currentTransactions)
						try {
							currentTransaction.execute(accounts, ui);
						} catch (CardOutException e) {
							exit();
						}
				} catch (OverdrawnException e1) {
					screen.displayMessageLine("\nOverdrawn(Insufficient funds in your account), your overdrawn limit is: "
							+ screen.getDollarAmount(Account.getAccount(accounts,
									currentAccountNumber).getOverdrawnLimit()));
				}
				// execute transaction
				break;
			case EXIT: // user chose to terminate session
				exit();
				userExited = true; // this ATM session should end
				break;
			default: // user did not enter an integer from 1-4
				screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
				break;
			} // end switch
		} // end while
	} // end method performTransactions

	// display the main menu and return an input selection
	private int displayMainMenu() throws CardOutException {
		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - View my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Transfer funds");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessage("Enter a choice: ");
		return keypad.getInputInt(); // return user's selection
	} // end method displayMainMenu

	// return object of specified Transaction subclass
	private Vector<Transaction> createTransactions(int type, Vector<Account> accounts)
			throws OverdrawnException, CardOutException {
		Vector<Transaction> result = new Vector<Transaction>(); // temporary
																// Transaction
																// variable

		// determine which type of Transaction to create
		switch (type) {
		case BALANCE_INQUIRY: // create new BalanceInquiry transaction
			result.add(new BalanceInquiry(currentAccountNumber, screen, bankDatabase));
			break;
		case WITHDRAWAL: // create new Withdrawal transaction
			result.add(new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad,
					cashDispenser));
			break;
		case TRANSFER: // create new Deposit transaction
			result = Transfer.transfer(ui, accounts, currentAccountNumber);
			break;
		} // end switch

		return result; // return the newly created object
	} // end method createTransaction

	public void exit() {
		screen.displayMessageLine("\nPlease take your card before you leave.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
} // end class ATM

/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
