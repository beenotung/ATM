package core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import account.Account;
import sun.swing.BakedArrayList;
import ui.Screen;
import ui.UI;

// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction {
	// BalanceInquiry constructor
	public BalanceInquiry(int userAccountNumber, Screen atmScreen,
			BankDatabase atmBankDatabase) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
	} // end BalanceInquiry constructor

	@Override
	// performs the transaction
	// get references to bank database and screen from parameters
	public void execute(Vector<Account> accounts, UI ui) throws AccountNotFoundException {
		ui.screen.displayMessageLine("\nBalance Information:");

		// get & display the balance for the account on the screen
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		ui.screen.displayMessage(" - Available balance: ");
		ui.screen.displayDollarAmount(availableBalance);
		ui.screen.displayMessageLine();
		ui.screen.displayMessage(" - Total balance:     ");
		ui.screen.displayDollarAmount(totalBalance);
		ui.screen.displayMessageLine();

		// check if the account has interest rate
		if (bankDatabase.IsCurrentAccount(getAccountNumber())) {
			ui.screen.displayMessage(" - Interest rate:     ");
			ui.screen.displayMessage(bankDatabase.getInterestRate(getAccountNumber()));
			ui.screen.displayMessageLine();
		}

		// check if the account has interest rate
		if (bankDatabase.IsCurrentAccount(getAccountNumber())) {
			ui.screen.displayMessage(" - Interest rate:     ");
			ui.screen.displayMessage(bankDatabase.getInterestRate(getAccountNumber()));
			ui.screen.displayMessageLine();
		}

		// check if the account has overdraw limit
		if (bankDatabase.IsCurrentAccount(getAccountNumber())) {
			ui.screen.displayMessage(" - Overdraw limit:     ");
			ui.screen.displayMessage(bankDatabase.getOverdrawLimit(getAccountNumber()));
			ui.screen.displayMessageLine();
		}

		ui.screen.displayMessageLine();
	} // end method execute
} // end class BalanceInquiry

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
