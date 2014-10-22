package core;

import myutil.exception.OverdrawnException;

public class CurrentAccount extends Account {

	public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance);
	}

	@Override
	public void debit(double amount) throws OverdrawnException {
		// TODO Auto-generated method stub
		super.debit(amount);
		overdrawnLimit = 10000;
	}

}
