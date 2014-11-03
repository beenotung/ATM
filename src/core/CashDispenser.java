package core;

import java.util.Vector;

import myutil.exception.CashNotEnoughException;
import myutils.CashCount;

// CashDispenser.java
// Represents the cash dispenser of the ATM

public class CashDispenser {
	private Vector<CashCount> cashCounts; // number of cash bills remaining

	// no-argument CashDispenser constructor initializes count to default
	public CashDispenser() {
		// set count attribute to default
		cashCounts = new Vector<CashCount>();
		cashCounts.add(new CashCount(100, 4));
		cashCounts.add(new CashCount(500, 2));
		cashCounts.add(new CashCount(1000, 1));
	} // end CashDispenser constructor

	/** instance methods **/

	// simulates dispensing of specified amount of cash
	public Vector<CashCount> dispenseCash(int amountRequired)
			throws CashNotEnoughException {
		Vector<CashCount> result = new Vector<CashCount>();
		if (!isSufficientCashAvailable(amountRequired))
			throw new CashNotEnoughException();
		for (int i = cashCounts.size() - 1; i >= 0; i--) {
			result.add(new CashCount(cashCounts.get(i).getValue(), 0));
			while ((amountRequired >= cashCounts.get(i).getValue())
					&& (cashCounts.get(i).getCount() > 0)) {
				amountRequired -= cashCounts.get(i).getValue();
				cashCounts.get(i).count--;
				result.get(result.size() - 1).count++;
			}
		}
		return result;
	} // end method dispenseCash

	// indicates whether cash dispenser can dispense desired amount
	public boolean isSufficientCashAvailable(double amountRequired) {
		return (getAmount() >= amountRequired);
	} // end method isSufficientCashAvailable

	public double getAmount() {
		double amount = 0;
		for (CashCount cashCount : cashCounts)
			amount += cashCount.getValue() * cashCount.count;
		return amount;
	}

} // end class CashDispenser

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
