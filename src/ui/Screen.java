package ui;

import myutils.MyStrings;

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
