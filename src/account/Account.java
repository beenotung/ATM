package account;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import myutil.exception.OverdrawnException;
import myutils.MyStaticStaff;

// Account.java
// Represents a bank account

public class Account {
	private int accountNumber; // account number
	private int pin; // PIN for authentication
	private double availableBalance; // funds available for withdrawal
	private double totalBalance; // funds available + pending deposits
	protected double overdrawnLimit;

	/** Static methods **/
	public static Account getAccount(Vector<Account> accounts, int accountNumber)
			throws AccountNotFoundException {
		for (Account account : accounts)
			if (account.getAccountNumber() == accountNumber)
				return account;
		throw new AccountNotFoundException();
	}

	public static boolean isMyBankAccount(int accountNumber) {
		String accountStr = String.valueOf(accountNumber);
		return accountStr.charAt(0) == '1';
	}

	// Account constructor initializes attributes
	public Account(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance) {
		accountNumber = theAccountNumber;
		pin = thePIN;
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
		overdrawnLimit = 0.0;
	} // end Account constructor

	/** instance methods **/
	// determines whether a user-specified PIN matches PIN in Account
	public boolean validatePIN(int userPIN) {
		if (userPIN == pin)
			return true;
		else
			return false;
	} // end method validatePIN

	public boolean isEnough(double amount) {
		double requiredAmount = amount;
		if (isMyBankAccount(getAccountNumber()))
			requiredAmount += MyStaticStaff.EXTRA_CHARGE;
		return (getAvailableBalance() >= requiredAmount);
	}

	// credits an amount to the account
	public void credit(double amount) {
		availableBalance += amount; // add to available balance
		totalBalance += amount; // add to total balance
	} // end method credit

	// debits an amount from the account
	public void debit(double amount) throws OverdrawnException {
		if (availableBalance < amount)
			throw new OverdrawnException();
		availableBalance -= amount; // subtract from available balance
		totalBalance -= amount; // subtract from total balance
	} // end method debit

	/** getters **/
	// returns available balance
	public double getAvailableBalance() {
		return availableBalance;
	} // end getAvailableBalance

	// returns the total balance
	public double getTotalBalance() {
		return totalBalance;
	} // end method getTotalBalance
		// returns account number

	public int getAccountNumber() {
		return accountNumber;
	} // end method getAccountNumber

	public double getOverdrawnLimit() {
		return overdrawnLimit;
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
