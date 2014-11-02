package account;

public class SavingAccount extends Account {
	private double interestRate = 0.1;

	/** constructors **/
	public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}

	public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance, double theInterest) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		interestRate = theInterest;
	}

	/** getters **/
	public double getInterestRate() {
		return interestRate;
	}
}
