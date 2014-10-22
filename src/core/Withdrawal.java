package core;

import java.util.Vector;

import ui.Keypad;
import ui.Screen;
import ui.UI;
import myutil.MyInputHandler;
import myutil.exception.CardOutException;
import myutil.exception.OverdrawnException;

// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
	private static final int[] validCashValues = { 100, 500, 1000 };

	private int amount; // amount to withdraw
	private Keypad keypad; // reference to keypad
	private CashDispenser cashDispenser; // reference to cash dispenser

	// constant corresponding to menu option to cancel
	private final static int CANCELED = 6;

	// Withdrawal constructor
	public Withdrawal(int userAccountNumber, Screen atmScreen,
			BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
		// initialize superclass variables
		super(userAccountNumber, atmScreen, atmBankDatabase);

		// initialize references to keypad and cash dispenser
		keypad = atmKeypad;
		cashDispenser = atmCashDispenser;
	} // end Withdrawal constructor

	// perform transaction
	public void execute(Vector<Account> accounts, UI ui) throws CardOutException, OverdrawnException {
		boolean cashDispensed = false; // cash was not dispensed yet
		double availableBalance; // amount available for withdrawal

		// get references to bank database and screen
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();

		// loop until cash is dispensed or the user cancels
		do {
			// obtain a chosen withdrawal amount from the user
			amount = displayMenuOfAmounts(ui);

			// check whether user chose a withdrawal amount or canceled
			if (amount != CANCELED) {
				// get available balance of account involved
				availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

				// auto check whether the user has enough money in the account
				//if (amount <= availableBalance) {
					// check whether the cash dispenser has enough money
					if (cashDispenser.isSufficientCashAvailable(amount)) {
						// update the account involved to reflect withdrawal
													bankDatabase.debit(getAccountNumber(), amount);
							cashDispenser.dispenseCash(amount); // dispense cash
							cashDispensed = true; // cash was dispensed

							// instruct user to take cash
							screen.displayMessageLine("\nPlease take your cash now.");
											} // end if
					else
						// cash dispenser does not have enough cash
						screen.displayMessageLine("\nInsufficient cash available in the ATM."
								+ "\n\nPlease choose a smaller amount.");
				/*} // end if
				else // not enough money available in user's account
				{
					screen.displayMessageLine("\nInsufficient funds in your account."
							+ "\nPlease choose a smaller amount.");
				} // end else
*/			} // end if
			else // user chose cancel menu option
			{
				System.out.println();
				System.out.println(amount);
				screen.displayMessageLine("\nCanceling transaction...");
				return; // return to main menu because user canceled
			} // end else
		} while (!cashDispensed);

	} // end method execute

	// display a menu of withdrawal amounts and the option to cancel;
	// return the chosen amount or 0 if the user chooses to cancel
	private int displayMenuOfAmounts(UI ui) throws CardOutException {
		int userChoice = 0; // local variable to store return value

		Screen screen = getScreen(); // get screen reference

		// array of amounts to correspond to menu numbers
		// int amounts[] = { 0, 20, 40, 60, 100, 200 };
		int amounts[] = { 0, 200, 400, 800, 1000 };

		// loop while no valid choice has been made
		while (userChoice == 0) {
			// display the menu
			screen.displayMessageLine("\nWithdrawal Menu:");
			screen.displayMessageLine("1 - $200");
			screen.displayMessageLine("2 - $400");
			screen.displayMessageLine("3 - $800");
			screen.displayMessageLine("4 - $1000");
			screen.displayMessageLine("5 - Other");
			screen.displayMessageLine(CANCELED + " - Cancel transaction");
			screen.displayMessage("\nChoose a withdrawal amount: ");

			int input = keypad.getInputInt(); // get user input through keypad

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
				screen.displayMessageLine("\nIvalid selection. Try again.");
			} // end switch
		} // end while

		return userChoice; // return withdrawal amount or CANCELED
	} // end method displayMenuOfAmounts

	private int manualInputAmount(UI ui) throws CardOutException {
		int result = CANCELED;
		int wrongInputCount = 0;
		do {
			ui.screen.displayMessage("Input the amount to withdraw: ");
			result = keypad.getInputInt();
			if ((result % 100) == 0)
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
