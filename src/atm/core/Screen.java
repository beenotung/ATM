package atm.core;

import atm.utils.MyStrings;

// Screen.java
// Represents the screen of the ATM

public class Screen {

	public void clear() {
		for (int i = 0; i < 5; i++)
			displayMessageLine();
	}

	// displays a message without a carriage return
	public void displayMessage(String message) {
		System.out.print(message);
	} // end method displayMessage

	// display a message with a carriage return
	public void displayMessageLine(String message) {
		System.out.println(message);
	}

	public void displayMessageLine(int i) {
		displayMessageLine(i + "");
	}

	public void displayMessage(double d) {
		displayMessageLine(d + "");
	}

	public void displayMessageLine() {
		displayMessageLine("");
	} // end method displayMessageLine

	// display a dollar amount
	public void displayDollarAmount(double amount) {
		displayMessage(Screen.getDollarAmount(amount));
	} // end method displayDollarAmount

	// display a dollar amount
	public void displayDollarAmount(int amount) {
		displayMessage(Screen.getDollarAmount(amount));
	} // end method displayDollarAmount

	// return a dollar amount as String
	public static String getDollarAmount(double amount) {
		return String.format(MyStrings.DOLLAR_SIGN + "%,.2f", amount);
	} // end method displayDollarAmount

	// return a dollar amount as String
	public static String getDollarAmount(int amount) {
		return MyStrings.DOLLAR_SIGN + amount;
	} // end method displayDollarAmount

} // end class Screen