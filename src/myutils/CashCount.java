package myutils;

public class CashCount {
	private final int value;
	public int count;

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
}
