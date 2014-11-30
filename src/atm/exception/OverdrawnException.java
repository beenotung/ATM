package atm.exception;

public class OverdrawnException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OverdrawnException() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Overdrawn Exception";
	}
}
