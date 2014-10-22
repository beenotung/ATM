package core;

import java.util.Vector;

import myutil.exception.OverdrawnException;

// Account.java
// Represents a bank account

public class Account {
	private int accountNumber; // account number
	private int pin; // PIN for authentication
	private double accountBalance; // funds available for withdrawal
	private double inwardsPending = 0; // pending money
	private double outwardsPending = 0; // pending money
	protected int overdrawnLimit = 0;

	// Account constructor initializes attributes
	public Account(int theAccountNumber, int thePIN, double accountBalance) {
		accountNumber = theAccountNumber;
		pin = thePIN;
		this.accountBalance = accountBalance;
	} // end Account constructor

	// determines whether a user-specified PIN matches PIN in Account
	public boolean validatePIN(int userPIN) {
		if (userPIN == pin)
			return true;
		else
			return false;
	} // end method validatePIN

	// returns available balance
	public double getAvailableBalance() {
		return accountBalance - outwardsPending;
	} // end getAvailableBalance

	/*
	 * // returns the total balance public double getTotalBalance() { return
	 * totalBalance; } // end method getTotalBalance
	 */

	// credits an amount to the account
	public void credit(double amount) {
		inwardsPending += amount; // add to total balance
	} // end method credit

	// debits an amount from the account
	public void debit(double amount) throws OverdrawnException {
		if ((getAvailableBalance() + overdrawnLimit) >= amount) {
			outwardsPending += amount; // subtract from balance
		} else {
			throw new OverdrawnException();
		}
	} // end method debit

	// returns account number
	public int getAccountNumber() {
		return accountNumber;
	} // end method getAccountNumber

	public static Account getAccount(Vector<Account> accounts, int accountNumber) {
		Account result = null;
		for (int i = 0; i < accounts.size(); i++)
			if (accounts.get(i).getAccountNumber() == accountNumber)
				result = accounts.get(i);
		return result;
	}

	public int getOverdrawnLimit() {
		return overdrawnLimit;
	}

	public void cleanTransfer() {
		accountBalance += inwardsPending;
		accountBalance -= outwardsPending;
		inwardsPending = outwardsPending = 0;
	}

} // end class Account

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
