package atm.exception;

import atm.utils.MyStrings;

public class TransferSameAccountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferSameAccountException() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Transfer Same Account Exception";
	}

	
}
