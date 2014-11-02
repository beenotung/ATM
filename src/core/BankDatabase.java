package core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import account.Account;
import account.SavingAccount;
import myutil.exception.OverdrawnException;
// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase {
	private Vector<Account> accounts; // Vector of Accounts

	// no-argument BankDatabase constructor initializes accounts
	public BankDatabase() {
		accounts = new Vector<Account>();
		accounts.add(new Account(12345, 54321, 1000.0, 1000.0));
		accounts.add(new Account(98765, 56789, 200.0, 200.0));
		accounts.add(new SavingAccount(54321, 24680, 100.0, 100.0));
	} // end no-argument BankDatabase constructor

	// retrieve Account object containing specified account number
	private Account getAccount(int accountNumber) throws AccountNotFoundException {
		// loop through accounts searching for matching account number
		for (Account currentAccount : accounts) {
			// return current account if match found
			if (currentAccount.getAccountNumber() == accountNumber)
				return currentAccount;
		} // end for
		throw new AccountNotFoundException(); // if no matching account was
												// found, throw exception
	} // end method getAccount

	// determine whether user-specified account number and PIN match
	// those of an account in the database
	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		// attempt to retrieve the account with the account number
		Account userAccount;
		try {
			userAccount = getAccount(userAccountNumber);
			// if account exists, return result of Account method validateIN
			return userAccount.validatePIN(userPIN);
		} catch (AccountNotFoundException e) {
			return false; // account number not found, so return false
		}
	} // end method authenticateUser

	// return available balance of Account with specified account number
	public double getAvailableBalance(int userAccountNumber) throws AccountNotFoundException {
		return getAccount(userAccountNumber).getAvailableBalance();
	} // end method getAvailableBalance

	// return total balance of Account with specified account number
	public double getTotalBalance(int userAccountNumber) throws AccountNotFoundException {
		return getAccount(userAccountNumber).getAvailableBalance();
	} // end method getTotalBalance

	// credit an amount to Account with specified account number
	public void credit(int userAccountNumber, double amount) throws AccountNotFoundException {
		getAccount(userAccountNumber).credit(amount);
	} // end method credit

	// debit an amount from of Account with specified account number
	public void debit(int userAccountNumber, double amount) throws OverdrawnException, AccountNotFoundException {
		getAccount(userAccountNumber).debit(amount);
	} // end method debit

	public Vector<Account> getAccounts() {
		return accounts;
	}

} // end class BankDatabase

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
