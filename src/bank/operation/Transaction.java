package bank.operation;

import javax.security.auth.login.AccountNotFoundException;

import bank.BankDatabase;
import atm.core.ATM;
import atm.core.Keypad;
import atm.core.Screen;
import atm.core.UI;
import atm.exception.CardOutException;
import atm.exception.CashNotesNotSupportedException;
import atm.exception.WrongInputException;

// Transaction.java
// Abstract superclass Transaction represents an ATM transaction

public abstract class Transaction {
	private String accountNumber; // indicates account involved
	private UI ui; // ATM's screen and keypad
	private BankDatabase bankDatabase; // account info database

	// Transaction constructor invoked by subclasses using super()
	public Transaction(ATM atm) {
		accountNumber = atm.getCurrentAccountNumber();
		ui = atm.getUI();
		bankDatabase = atm.getBankDatabase();
	} // end Transaction constructor

	// return account number
	public String getAccountNumber() {
		return accountNumber;
	} // end method getAccountNumber

	// return reference to screen
	public Screen getScreen() {
		return ui.screen;
	} // end method getScreen

	// return reference to keypad
	public Keypad getKeypad() {
		return ui.keypad;
	} // end method getKeypad

	// return reference to bank database
	public BankDatabase getBankDatabase() {
		return bankDatabase;
	} // end method getBankDatabase

	// perform the transaction (overridden by each subclass)
	public abstract void execute() throws WrongInputException, AccountNotFoundException,
			CardOutException, CashNotesNotSupportedException;
} // end class Transaction

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
