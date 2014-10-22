package myutil.exception;

public class OverdrawnException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Overdrawn Exception";
	}
}
