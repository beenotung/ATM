package account;

public class SavingAccount extends Account {
	private double interest = 0.1;

	public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}

	public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance, double theInterest) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		interest = theInterest;
	}
}
