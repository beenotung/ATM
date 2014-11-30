package bank.operation;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import com.thoughtworks.xstream.InitializationException;

import bank.BankDatabase;
import bank.account.Account;
import atm.core.ATM;
import atm.core.CashDispenser;
import atm.core.Screen;
import atm.core.UI;
import atm.exception.CardOutException;
import atm.exception.CashNotEnoughException;
import atm.exception.CashOutException;
import atm.exception.OverdrawnException;
import atm.exception.WrongInputException;
import atm.utils.CashCount;
import atm.utils.MyInputHandler;
import atm.utils.MyStaticStuff;
import atm.utils.MyStrings;

// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
	private int amount; // amount to withdraw
	private ATM atm;

	// constant corresponding to menu option to cancel
	private static int CANCELED;
	private boolean commandMode = true;

	// Withdrawal constructor
	// get references to keypad and cash dispenser from atm
	public Withdrawal(ATM atm) {
		// initialize superclass variables
		super(atm);
		this.atm = atm;
		CANCELED = MyStaticStuff.MenuCashValue.length + 2;
	} // end Withdrawal constructor

	public void setAmount(String amountStr) throws NumberFormatException {
		commandMode = false;
		this.amount = Integer.parseInt(amountStr);
	}

	@Override
	// perform transaction
	public void execute() throws WrongInputException, AccountNotFoundException,
			CardOutException {
		boolean cashDispensed = false; // cash was not dispensed yet
		int tryCount = 0;
		// loop until cash is dispensed or the user cancels
		do {
			tryCount++;
			// obtain a chosen withdrawal amount from the user
			amount = displayMenuOfAmounts(atm.getUI());

			// check whether user chose a withdrawal amount or canceled
			if (amount == CANCELED)
				return;

			// auto check whether the user has enough money in the account
			// check whether the cash dispenser has enough money
			try {
				try {
					if (!Account.isMyBankAccount(getAccountNumber()))
						if (!BankDatabase.getAccount(getAccountNumber())
								.isEnough(amount))
							throw new OverdrawnException();
					Vector<CashCount> cashPop = CashDispenser
							.dispenseCash(amount);
					if (!Account.isMyBankAccount(getAccountNumber()))
						BankDatabase.debit(getAccountNumber(),
								MyStaticStuff.EXTRA_CHARGE);
					BankDatabase.debit(getAccountNumber(), amount);
					cashDispensed = true; // cash was dispensed
					atm.popCash(cashPop);
				} catch (OverdrawnException e) {
					getScreen().displayMessageLine(
							MyStrings.getOverDrawnMessage(BankDatabase
									.getAccount(getAccountNumber())
									.getOverdrawnLimit()));
					MyStaticStuff.sleep();
				}
			} catch (CashNotEnoughException e) {
				// cash dispenser does not have enough cash
				getScreen().displayMessageLine(
						"\nInsufficient cash available in the ATM."
								+ "\n Avaliabe cash:"
								+ CashDispenser.getAmount()
								+ "\n\nPlease choose a smaller amount.");
				MyStaticStuff.sleep();
			} // dispense cash
		} while ((!cashDispensed)
				&& (tryCount < MyInputHandler.MAX_WRONG_INPUT));
	} // end method execute

	public void executeGUI() throws AccountNotFoundException,
			OverdrawnException, CashNotEnoughException, CashOutException {
		// throw overdrawexception, cashnotenoughexception
		if (commandMode)
			throw new InitializationException(
					"WithDrawal: amount has not be initialized");
		@SuppressWarnings("unused")
		boolean cashDispensed = false; // cash was not dispensed yet
		if (!Account.isMyBankAccount(getAccountNumber()))
			if (!BankDatabase.getAccount(getAccountNumber()).isEnough(amount))
				throw new OverdrawnException();
		Vector<CashCount> cashPop = CashDispenser.dispenseCash(amount);
		if (!Account.isMyBankAccount(getAccountNumber()))
			BankDatabase.debit(getAccountNumber(), MyStaticStuff.EXTRA_CHARGE);
		BankDatabase.debit(getAccountNumber(), amount);
		cashDispensed = true; // cash was dispensed
		throw new CashOutException(cashPop);
	}

	// display a menu of withdrawal amounts and the option to cancel;
	// return the chosen amount or 0 if the user chooses to cancel
	private int displayMenuOfAmounts(UI ui) throws WrongInputException {
		int userChoice = 0; // local variable to store return value

		// loop while no valid choice has been made
		while (userChoice == 0) {
			// display the menu
			String msg = "\nWithdrawal Menu:";
			int i = 0;
			for (Integer cashValue : MyStaticStuff.MenuCashValue)
				msg += "\n" + (++i) + " - " + Screen.getDollarAmount(cashValue);
			msg += "\n" + (++i) + " - Other";
			msg += "\n" + CANCELED + " - Cancel withdrawal";
			msg += "\n\nChoose a withdrawal amount: ";
			// get user input through keypad
			int input = ui.keypad.getInputInt(msg);

			// determine how to proceed based on the input value
			if (input == CANCELED)
				userChoice = CANCELED;
			else if (input == CANCELED - 1)
				userChoice = manualInputAmount(ui);
			else if ((input >= 1)
					&& (input <= MyStaticStuff.MenuCashValue.length))
				userChoice = MyStaticStuff.MenuCashValue[input - 1];
			else
				ui.screen.displayMessageLine("\nIvalid selection. Try again.");
		} // end while
		return userChoice; // return withdrawal amount or CANCELED
	} // end method displayMenuOfAmounts

	private int manualInputAmount(UI ui) throws WrongInputException {
		int amount = CANCELED;
		int wrongInputCount = 0;
		boolean ok;
		do {
			ok = true;
			String msg = "We provide " + MyStaticStuff.getCashValuesStrings()
					+ " note"
					+ (MyStaticStuff.CashValues.length > 1 ? "s" : "")
					+ " only";
			msg += "\nInput the amount to withdraw (input 0 to cancel): ";
			try {
				amount = ui.keypad.getInputInt(msg);
				if (amount == 0)
					return CANCELED;
				else if ((amount < 0) || ((amount % 100) != 0))
					throw new WrongInputException();
			} catch (WrongInputException e) {
				ok = false;
				ui.screen.displayMessageLine("Invalid input");
				MyStaticStuff.sleep();
			}
		} while ((wrongInputCount <= MyInputHandler.MAX_WRONG_INPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		return amount;
	}
} // end class Withdrawal