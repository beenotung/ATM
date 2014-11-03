package myutil;

import ui.Screen;

public class MyStaticStaff {
	public static int[] CashValues = { 100, 500, 1000 };
	public static int[] MenuCashValue = { 200, 400, 800, 1000 };

	public static void sleep() {
		MyStaticStaff.sleep(1000);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	public static String getCashValuesStrings(){
		String [] cashValuesStrings=new String [CashValues.length];
		for (int i=0;i<CashValues.length;i++)
			cashValuesStrings[i]=Screen.getDollarAmount(CashValues[i]);
		return cashValuesStrings.toString();
	}
}
