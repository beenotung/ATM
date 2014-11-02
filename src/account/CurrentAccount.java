package account;

import myutil.exception.OverdrawnException;

public class CurrentAccount extends Account {

	public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		overdrawnLimit = 10000;
	}

	public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance, double theOverdrawnLimit) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		overdrawnLimit = theOverdrawnLimit;
	}

}
