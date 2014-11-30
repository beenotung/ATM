package bank.account;

public class CurrentAccount extends Account {

	public CurrentAccount(String theAccountNumber, String thePIN, double theAvailableBalance,
			double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		overdrawnLimit = 10000;
	}

	public CurrentAccount(String theAccountNumber, String thePIN, double theAvailableBalance,
			double theTotalBalance, double theOverdrawnLimit) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		overdrawnLimit = theOverdrawnLimit;
	}

}
