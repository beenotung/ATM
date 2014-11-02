package core;

import java.util.Vector;

import account.Account;
import ui.UI;
import myutil.exception.CardOutException;
import myutil.exception.OverdrawnException;
import myutil.exception.WrongInputException;

public class Transfer {
	public static Vector<Transaction> transfer(UI ui, Vector<Account> accounts,
			int accountNumberFrom) throws OverdrawnException, WrongInputException {
		Vector<Transaction> result = new Vector<Transaction>();
		int accountNumberTo;
		Account accountFrom = Account.getAccount(accounts, accountNumberFrom);
		Account accountTo;
		double amount;

		ui.screen.displayMessage("\nPlease input the account number of the receiver: ");
		accountNumberTo = ui.keypad.getInputInt();
		accountTo = Account.getAccount(accounts, accountNumberTo);
		ui.screen.displayMessage("\nPlease the amount to transfer: ");
		amount = ui.keypad.getInputReal();

		if ((accountFrom.getAvailableBalance() + accountFrom.getOverdrawnLimit()) >= amount) {
			accountFrom.debit(amount);
			accountTo.credit(amount);
		}
		return result;
	}
}
