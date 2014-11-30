package atm.exception;

public class WrongInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongInputException() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "WrongInput Exception";
	}
}