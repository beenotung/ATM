package atm.core;

import java.util.Vector;

import atm.exception.CashNotEnoughException;
import atm.utils.CashCount;

// CashDispenser.java
// Represents the cash dispenser of the ATM

public class CashDispenser {
	// number of cash bills remaining
	public static Vector<CashCount> cashCounts = new Vector<CashCount>();
	public static Vector<CashCount> lastTransaction = null;

	// no-argument CashDispenser constructor initializes count to default
	public static void init() {
		// set count attribute to default
		cashCounts = new Vector<CashCount>();
		cashCounts.add(new CashCount(100, 1));
		cashCounts.add(new CashCount(500, 8));
		cashCounts.add(new CashCount(1000, 2));
	} // end CashDispenser constructor

	/** static methods **/
	// simulates dispensing of specified amount of cash
	public static Vector<CashCount> dispenseCash(int amountRequired)
			throws CashNotEnoughException {
		Vector<CashCount> result = new Vector<CashCount>();
		if (!isSufficientCashAvailable(amountRequired))
			throw new CashNotEnoughException();
		for (int i = cashCounts.size() - 1; i >= 0; i--) {
			result.add(new CashCount(cashCounts.get(i).getValue(), 0));
			while ((amountRequired >= cashCounts.get(i).getValue())
					&& (cashCounts.get(i).getCount() > 0)) {
				amountRequired -= cashCounts.get(i).getValue();
				cashCounts.get(i).remove(1);
				result.get(result.size() - 1).add(1);
			}
		}
		int amountPop = 0;
		for (CashCount cashCount : result)
			amountPop += cashCount.getValue() * cashCount.getCount();
		if (amountPop != amountRequired)
			return null;
		lastTransaction = result;
		return result;
	} // end method dispenseCash

	public static void rollback() {
		System.out.println("CashDispenser: rollback (cash not taken)");
		if (lastTransaction == null)
			return;
		for (CashCount lastTransactionIterator : lastTransaction) {
			for (CashCount cashCountIterator : cashCounts) {
				if (lastTransactionIterator.getValue() == cashCountIterator
						.getValue())
					cashCountIterator.add(lastTransactionIterator.getCount());
			}
		}
	}

	// comfirm pop cash
	public static void commit() {
		System.out.println("CashDispenser: commit (confirm cash taken)");
		lastTransaction = null;
	}

	public static Vector<CashCount> getCash() {
		return lastTransaction;
	}

	// indicates whether cash dispenser can dispense desired amount
	public static boolean isSufficientCashAvailable(double amountRequired) {
		return (getAmount() >= amountRequired);
	} // end method isSufficientCashAvailable

	public static double getAmount() {
		double amount = 0;
		for (CashCount cashCount : cashCounts)
			amount += cashCount.getValue() * cashCount.getCount();
		return amount;
	}

} // end class CashDispenser

