package atm.exception;

public class CardOutException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardOutException() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Card Out Exception";
	}
}
