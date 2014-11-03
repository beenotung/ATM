package core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import account.Account;
import ui.UI;
import myutil.MyInputHandler;
import myutil.MyStrings;
import myutil.exception.OverdrawnException;
import myutil.exception.WrongInputException;

public class Transfer {

	public static Vector<Transaction> transfer(ATM atm) throws OverdrawnException,
			WrongInputException, AccountNotFoundException {
		Vector<Transaction> result = new Vector<Transaction>();
		UI ui = atm.getUI();
		BankDatabase bankDatabase = atm.getBankDatabase();

		int accountNumberTo;
		Account accountFrom = bankDatabase.getAccount(atm.getCurrentAccountNumber());
		Account accountTo = null;
		double amount;

		boolean ok;
		int wrongCount = 0;
		// get target account to be transfered from user
		do {
			ok = true;
			atm.getUI().screen
					.displayMessage("\nPlease input the account number of the receiver: ");
			accountNumberTo = ui.keypad.getInputInt();
			try {
				accountTo = bankDatabase.getAccount(accountNumberTo);
			} catch (AccountNotFoundException e) {
				wrongCount++;
				ok = false;
				ui.screen.displayMessageLine(MyStrings.ACCOUNT_NOT_FOUND);
			}
			if (accountFrom.getAccountNumber() == accountTo.getAccountNumber()) {
				wrongCount++;
				ok = false;
				ui.screen.displayMessageLine(MyStrings.TRANSFER_SAME_ACCOUNT);
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();

		// get amount to be transfered from user
		ui.screen.displayMessage("\nPlease the amount to transfer: ");
		amount = ui.keypad.getInputDoublePositive();

		// auto throw OverdrawnException if the accountFrom has not enough
		// available balance
		accountFrom.debit(amount);
		accountTo.credit(amount);

		return result;
	}

}
