package core;

import javax.security.auth.login.AccountNotFoundException;

// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction {
	// BalanceInquiry constructor
	public BalanceInquiry(ATM atm) {
		super(atm);
	} // end BalanceInquiry constructor

	@Override
	// performs the transaction
	// get references to bank database and screen from parameters
	public void execute() throws AccountNotFoundException {
		getScreen().displayMessageLine("\nBalance Information:");

		// get & display the balance for the account on the screen
		double availableBalance = getBankDatabase().getAvailableBalance(
				getAccountNumber());
		double totalBalance = getBankDatabase().getTotalBalance(getAccountNumber());
		getScreen().displayMessage(" - Available balance: ");
		getScreen().displayDollarAmount(availableBalance);
		getScreen().displayMessageLine();
		getScreen().displayMessage(" - Total balance:     ");
		getScreen().displayDollarAmount(totalBalance);
		getScreen().displayMessageLine();

		// check if the account has interest rate
		if (getBankDatabase().IsSavingAccount(getAccountNumber())) {
			getScreen().displayMessage(" - Interest rate:     ");
			getScreen().displayMessage(
					getBankDatabase().getInterestRate(getAccountNumber()));
			getScreen().displayMessageLine();
		}

		// check if the account has interest rate
		if (getBankDatabase().IsCurrentAccount(getAccountNumber())) {
			getScreen().displayMessage(" - Interest rate:     ");
			getScreen().displayMessage(
					getBankDatabase().getInterestRate(getAccountNumber()));
			getScreen().displayMessageLine();
		}

		// check if the account has overdraw limit
		if (getBankDatabase().IsCurrentAccount(getAccountNumber())) {
			getScreen().displayMessage(" - Overdraw limit:     ");
			getScreen().displayMessage(
					getBankDatabase().getOverdrawLimit(getAccountNumber()));
			getScreen().displayMessageLine();
		}

		getScreen().displayMessageLine();
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
