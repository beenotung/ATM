package core;

import java.util.Vector;

import javax.security.auth.login.AccountNotFoundException;

import account.Account;
import ui.Screen;
import ui.UI;
import myutil.exception.CardOutException;
import myutil.exception.CashNotEnoughException;
import myutil.exception.OverdrawnException;
import myutil.exception.WrongInputException;
import myutils.CashCount;
import myutils.MyInputHandler;
import myutils.MyStaticStuff;
import myutils.MyStrings;

// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
	private int amount; // amount to withdraw
	private ATM atm;
	private CashDispenser cashDispenser; // reference to cash dispenser

	// constant corresponding to menu option to cancel
	private static int CANCELED;

	// Withdrawal constructor
	// get references to keypad and cash dispenser from atm
	public Withdrawal(ATM atm) {
		// initialize superclass variables
		super(atm);
		this.atm = atm;
		cashDispenser = atm.getCashDispenser();
		CANCELED = MyStaticStuff.MenuCashValue.length + 2;
	} // end Withdrawal constructor

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
						if (!getBankDatabase().getAccount(getAccountNumber()).isEnough(
								amount))
							throw new OverdrawnException();
					Vector<CashCount> cashPop = cashDispenser.dispenseCash(amount);
					if (!Account.isMyBankAccount(getAccountNumber()))
						getBankDatabase().debit(getAccountNumber(),
								MyStaticStuff.EXTRA_CHARGE);
					getBankDatabase().debit(getAccountNumber(), amount);
					cashDispensed = true; // cash was dispensed
					atm.popCash(cashPop);
				} catch (OverdrawnException e) {
					getScreen().displayMessageLine(
							MyStrings.getOverDrawnMessage(getBankDatabase().getAccount(
									getAccountNumber()).getOverdrawnLimit()));
					MyStaticStuff.sleep();
				}
			} catch (CashNotEnoughException e) {
				// cash dispenser does not have enough cash
				getScreen().displayMessageLine(
						"\nInsufficient cash available in the ATM." + "\n Avaliabe cash:"
								+ cashDispenser.getAmount()
								+ "\n\nPlease choose a smaller amount.");
				MyStaticStuff.sleep();
			} // dispense cash
		} while ((!cashDispensed) && (tryCount < MyInputHandler.MAXWRONGINPUT));
	} // end method execute

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
			else if ((input >= 1) && (input <= MyStaticStuff.MenuCashValue.length))
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
			String msg = "We provide " + MyStaticStuff.getCashValuesStrings() + " note"
					+ (MyStaticStuff.CashValues.length > 1 ? "s" : "") + " only";
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
		} while ((wrongInputCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		return amount;
	}
} // end class Withdrawal

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
