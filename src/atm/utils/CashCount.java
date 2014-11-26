package atm.utils;

import myutil.exception.CashNotEnoughException;

public class CashCount {
	private final int value;
	private int count;

	public CashCount(int value, int count) {
		this.value = value;
		this.count = count;
	}

	public int getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

	public void remove(int number) throws CashNotEnoughException {
		if (number > count)
			throw new CashNotEnoughException();
		count -= number;
	}

	public void add(int number) {
		count += number;
	}
}
