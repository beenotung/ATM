package atm.exception;

public class CashNotEnoughException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CashNotEnoughException() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "CashNotEnoughException Exception";
	}
}
