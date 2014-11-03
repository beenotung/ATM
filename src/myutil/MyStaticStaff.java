package myutil;

public class MyStaticStaff {
	public static int[] CashValues = { 100, 500, 1000 };
	public static int[] MenuCashValue = { 200, 400, 800, 1000 };

	public static void sleep() {
		sleep();
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
