package core;

import java.util.Calendar;

public class SavingAccount extends Account {
	public static final double INTEREST = 0.1 / 100.0;

	public static Calendar getAnnum() {
		Calendar annum;
		annum = Calendar.getInstance();
		annum.set(1900, Calendar.MARCH, 31, 23, 59, 59);
		return annum;
	}

	public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance);
	}

	public void interestPaying() {
		double amount = getAvailableBalance() * INTEREST;
		credit(amount);
	}
}
