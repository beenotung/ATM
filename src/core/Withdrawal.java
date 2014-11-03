package core;

import javax.security.auth.login.AccountNotFoundException;

import account.Account;
import ui.UI;
import myutil.MyInputHandler;
import myutil.MyStaticStaff;
import myutil.MyStrings;
import myutil.exception.CashNotEnoughException;
import myutil.exception.OverdrawnException;
import myutil.exception.WrongInputException;

// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
	private int amount; // amount to withdraw
	private ATM atm;
	private CashDispenser cashDispenser; // reference to cash dispenser

	// constant corresponding to menu option to cancel
	private final static int CANCELED = 6;

	// Withdrawal constructor
	// get references to keypad and cash dispenser from atm
	public Withdrawal(ATM atm) {
		// initialize superclass variables
		super(atm);
		this.atm = atm;
		cashDispenser = atm.getCashDispenser();
	} // end Withdrawal constructor

	@Override
	// perform transaction
	public void execute() throws WrongInputException, AccountNotFoundException {
		boolean cashDispensed = false; // cash was not dispensed yet

		// get references to bank database and screen

		// loop until cash is dispensed or the user cancels
		do {
			// obtain a chosen withdrawal amount from the user
			amount = displayMenuOfAmounts(ui);

			// check whether user chose a withdrawal amount or canceled
			if (amount != CANCELED) {
				// auto check whether the user has enough money in the account
				// check whether the cash dispenser has enough money
				try {
					try {
						bankDatabase.debit(getAccountNumber(), amount);
						cashDispenser.dispenseCash(amount);
						cashDispensed = true; // cash was dispensed
						atm.popCash();
					} catch (OverdrawnException e) {
						ui.screen.displayMessageLine(MyStrings
								.getOverDrawnMessage(bankDatabase.getAccount(
										accountNumber).getOverdrawnLimit()));
					}
				} catch (CashNotEnoughException e) {
					// cash dispenser does not have enough cash
					ui.screen
							.displayMessageLine("\nInsufficient cash available in the ATM."
									+ "\n Avaliabe cash:"
									+ cashDispenser.getAmount()
									+ "\n\nPlease choose a smaller amount.");
				} // dispense cash
			} else // user chose cancel menu option
			{
				ui.screen.displayMessageLine();
				ui.screen.displayMessageLine(amount);
				ui.screen.displayMessageLine("\nCanceling withdrawal...");
				return; // return to main menu because user canceled
			} // end else
		} while (!cashDispensed);

	} // end method execute

	// display a menu of withdrawal amounts and the option to cancel;
	// return the chosen amount or 0 if the user chooses to cancel
	private int displayMenuOfAmounts(UI ui) throws WrongInputException {
		int userChoice = 0; // local variable to store return value

		// array of amounts to correspond to menu numbers
		// int amounts[] = { 0, 20, 40, 60, 100, 200 };
		int amounts[] = { 0, 200, 400, 800, 1000 };

		// loop while no valid choice has been made
		while (userChoice == 0) {
			// display the menu
			String msg = "\nWithdrawal Menu:";
			msg += "\n1 - $200";
			msg += "\n2 - $400";
			msg += "\n3 - $800";
			msg += "\n4 - $1000";
			msg += "\n5 - Other";
			msg += "\n" + CANCELED + " - Cancel transaction";
			msg += "\n\nChoose a withdrawal amount: ";
			// get user input through keypad
			int input = ui.keypad.getInputInt(msg);

			// determine how to proceed based on the input value
			switch (input) {
			case 1: // if the user chose a withdrawal amount
			case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
			case 3: // corresponding amount from amounts array
			case 4:
				userChoice = amounts[input]; // save user's choice
				break;
			case 5:
				userChoice = manualInputAmount(ui);
				break;
			case CANCELED: // the user chose to cancel
				userChoice = CANCELED; // save user's choice
				break;
			default: // the user did not enter a value from 1-6
				ui.screen.displayMessageLine("\nIvalid selection. Try again.");
			} // end switch
		} // end while

		return userChoice; // return withdrawal amount or CANCELED
	} // end method displayMenuOfAmounts

	private int manualInputAmount(UI ui) throws WrongInputException {
		int result = CANCELED;
		int wrongInputCount = 0;
		do {
			ui.screen.displayMessageLine("We provide "
					+ MyStaticStaff.getCashValuesStrings() + " note"
					+ (MyStaticStaff.CashValues.length > 1 ? "s" : "") + " only");
			result = ui.keypad
					.getInputInt("Input the amount to withdraw (input 0 to cancel): ");
			if (amount <= 0)
				return CANCELED;
			if ((result % 100) != 0)
				break;
		} while (wrongInputCount <= MyInputHandler.MAXWRONGINPUT);
		return result;
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
