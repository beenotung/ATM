package ui;

// Keypad.java
// Represents the keypad of the ATM
import java.util.Scanner; // program uses Scanner to obtain user input

import myutil.MyInputHandler;
import myutil.MyStaticStaff;
import myutil.exception.WrongInputException;

public class Keypad {
	private Screen screen;
	private Scanner input; // reads data from the command line

	// no-argument constructor initializes the Scanner
	public Keypad(Screen screen) {
		this.screen = screen;
		input = new Scanner(System.in);
	} // end no-argument Keypad constructor

	// return an integer value entered by user
	public int getInputInt(String msg) throws WrongInputException {
		int result = 0;
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				screen.displayMessage(msg);
				result = Integer.valueOf(input.next());
			} catch (NumberFormatException e) {
				screen.displayMessageLine("Please input an integer only.");
				wrongCount++;
				ok = false;
				MyStaticStaff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		else
			return result; // we don't assume that user enters an integer
	} // end method getInput

	// return an positive integer value entered by user
	public int getInputIntPositive(String msg) throws WrongInputException {
		int result = 0;
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				screen.displayMessage(msg);
				result = Integer.valueOf(input.next());
				if (result <= 0)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				screen.displayMessageLine("Please input an positive integer only.");
				wrongCount++;
				ok = false;
				MyStaticStaff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		else
			return result; // we don't assume that user enters an integer
	} // end method getInputIntPositive

	public double getInputDouble(String msg) throws WrongInputException {
		double result = 0;
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				screen.displayMessage(msg);
				result = Double.valueOf(input.next());
			} catch (NumberFormatException e) {
				System.out.println("Please input an real number only.");
				wrongCount++;
				ok = false;
				MyStaticStaff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		else
			return result;
	}

	public double getInputDoublePositive(String msg) throws WrongInputException {
		double result = 0;
		int wrongCount = 0;
		boolean ok;
		do {
			ok = true;
			try {
				screen.displayMessage(msg);
				result = Double.valueOf(input.next());
				if (result <= 0)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				System.out.println("Please input a positive real number only.");
				wrongCount++;
				ok = false;
				MyStaticStaff.sleep();
			}
		} while ((wrongCount <= MyInputHandler.MAXWRONGINPUT) && (!ok));
		if (!ok)
			throw new WrongInputException();
		else
			return result;
	}
} // end class Keypad

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
