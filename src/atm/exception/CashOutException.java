package atm.exception;

import java.util.Vector;

import atm.utils.CashCount;

public class CashOutException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<CashCount> popCashCounts;

	public CashOutException(Vector<CashCount> popCashCounts) {
		this.popCashCounts = popCashCounts;
	}

	@Override
	public String toString() {
		return "Card Out Exception";
	}

	public Vector<CashCount> getCashCounts() {
		return popCashCounts;
	}
}
