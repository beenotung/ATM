package atm.exception;

import java.util.Vector;

import atm.utils.CashCount;

public class CashNotesNotSupportedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CashNotesNotSupportedException() {
		System.out.println(toString());
	}

	public CashNotesNotSupportedException(int amountRequired,
			Vector<CashCount> result) {
		System.out.println("CashNotesNotSupportedException!");
		System.out.println("amountRequired: " + amountRequired);
		System.out.println("result set: " + result.toString());
	}

	@Override
	public String toString() {
		return "CashNotEnough Exception";
	}
}
