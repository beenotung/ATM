package bank.account;

public class SavingAccount extends Account {
	private double interestRate = 0.1 / 100d;

	/** constructors **/
	public SavingAccount(String theAccountNumber, String thePIN,
			double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}

	public SavingAccount(String theAccountNumber, String thePIN,
			double theAvailableBalance, double theTotalBalance, double theInterest) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		interestRate = theInterest;
	}

	/** getters **/
	public double getInterestRate() {
		return interestRate;
	}

	public String getInterestRateString() {
		return interestRate * 100 + "%";
	}
}
