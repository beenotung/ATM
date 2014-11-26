package bank.account;

import java.util.Arrays;
import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import atm.utils.MyStaticStuff;
import myutil.exception.OverdrawnException;

// Account.java
// Represents a bank account

public class Account {
	protected String accountNumber; // account number
	private char[] pin; // PIN for authentication
	protected double availableBalance; // funds available for withdrawal
	protected double totalBalance; // funds available + pending deposits
	protected double overdrawnLimit;

	/** Account constructor initializes attributes **/
	public Account(String theAccountNumber, String thePIN, double theAvailableBalance, double theTotalBalance) {
		accountNumber = theAccountNumber;
		pin = thePIN.toCharArray();
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
		overdrawnLimit = 0.0;
	} // end Account constructor

	/** Static methods **/
	public static Account getAccount(Vector<Account> accounts, String accountNumber)
			throws AccountNotFoundException {
		for (Account account : accounts)
			if (account.getAccountNumber() == accountNumber)
				return account;
		throw new AccountNotFoundException();
	}

	public static boolean isMyBankAccount(String accountNumber) {
		return accountNumber.charAt(0) == '1';
	}

	/** instance methods **/

	// determines whether a user-specified PIN matches PIN in Account
	@Deprecated
	public boolean validatePIN(String userPIN) {
		return Arrays.equals(pin, userPIN.toCharArray());
	} // end method validatePIN

	public boolean validatePIN(char[] userPIN) {
		return Arrays.equals(pin, userPIN);
	}

	public boolean isMyBankAccount() {
		String accountStr = String.valueOf(accountNumber);
		return accountStr.charAt(0) == '1';
	}

	public boolean isEnough(double amount) {
		double requiredAmount = amount;
		if (!isMyBankAccount())
			requiredAmount += MyStaticStuff.EXTRA_CHARGE;
		return ((availableBalance + overdrawnLimit) >= requiredAmount);
	}

	// credits an amount to the account
	public void credit(double amount) {
		availableBalance += amount; // add to available balance
		totalBalance += amount; // add to total balance
	} // end method credit

	// debits an amount from the account
	public void debit(double amount) throws OverdrawnException {
		if ((availableBalance + overdrawnLimit) < amount)
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

	public String getAccountNumber() {
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
