package bank.operation;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import bank.BankDatabase;
import bank.account.Account;
import atm.core.ATM;
import atm.core.UI;
import atm.exception.OverdrawnException;
import atm.exception.WrongInputException;
import atm.utils.MyInputHandler;
import atm.utils.MyStaticStuff;
import atm.utils.MyStrings;

public class Transfer {

	public static Vector<Transaction> transfer(ATM atm) throws WrongInputException, AccountNotFoundException {
		Vector<Transaction> result = new Vector<Transaction>();
		UI ui = atm.getUI();

		String accountNumberTo;
		Account accountFrom = BankDatabase.getAccount(atm.getCurrentAccountNumber());
		Account accountTo = null;
		double amount = 0;

		boolean ok;
		int wrongCount = 0;
		// get target account to be transfered from user
		do {
			ok = true;
			accountNumberTo = String.valueOf(ui.keypad
					.getInputInt("\nPlease input the account number of the receiver: "));
			try {
				accountTo = BankDatabase.getAccount(accountNumberTo);
				if (accountFrom.getAccountNumber() == accountTo.getAccountNumber()) {
					wrongCount++;
					ok = false;
					ui.screen.displayMessageLine(MyStrings.TRANSFER_SAME_ACCOUNT);
				}
			} catch (AccountNotFoundException e) {
				wrongCount++;
				ok = false;
				ui.screen.displayMessageLine(MyStrings.ACCOUNT_NOT_FOUND);
				MyStaticStuff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAX_WRONG_INPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();

		// get amount to be transfered from user
		// auto throw OverdrawnException if the accountFrom has not enough
		// available balance
		wrongCount = 0;
		do {
			ok = true;
			try {
				amount = ui.keypad.getInputDouble("\nPlease the amount to transfer (input 0 to cancel): ");
				if (amount == 0)
					return null;
				else if (amount < 0) {
					wrongCount++;
					ok = false;
					ui.screen.displayMessageLine("The amount should be positive. Please try again.");
				} else if (accountFrom.isEnough(amount)) {
					accountFrom.debit(amount);
					accountTo.credit(amount);
				} else {
					throw new OverdrawnException();
				}
			} catch (OverdrawnException e) {
				wrongCount++;
				ok = false;
				ui.screen.displayMessageLine(MyStrings.getOverDrawnMessage(BankDatabase.getAccount(
						atm.getCurrentAccountNumber()).getOverdrawnLimit()));
				MyStaticStuff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAX_WRONG_INPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		else {
			if (!accountFrom.isMyBankAccount())
				ui.screen.displayMessageLine(MyStaticStuff.getExtraChargeString());
			ui.screen.displayMessageLine(MyStrings.TRANSFER_SUCCEED);
		}
		return result;
	}

}
