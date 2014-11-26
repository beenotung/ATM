package atm.utils;

import java.util.Vector;

import atm.core.Screen;

public class MyStaticStuff {
	public static int[] CashValues = { 100, 500, 1000 };
	public static final int[] MenuCashValue = { 200, 400, 800, 1000 };
	public static double EXTRA_CHARGE = 20;

	public static void sleep() {
		MyStaticStuff.sleep(1000);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	public static String getCashValuesStrings() {
		Vector<String> cashValuesStrings = new Vector<String>();
		for (int i = 0; i < CashValues.length; i++)
			cashValuesStrings.add(Screen.getDollarAmount(CashValues[i]));
		return cashValuesStrings.toString();
	}

	public static String getExtraChargeString() {
		return "Extra charge: " + Screen.getDollarAmount(EXTRA_CHARGE)
				+ " will be charged for successful transaction";
	}

	public static String getCashValuesStrings(Vector<CashCount> cashCounts) {
		Vector<String> cashValuesStrings = new Vector<String>();
		for (CashCount cashCount : cashCounts)
			if (cashCount.getCount() > 0)
				cashValuesStrings.add(Screen.getDollarAmount(cashCount.getValue()) + "x"
						+ cashCount.getCount());
		return cashValuesStrings.toString();
	}

}
