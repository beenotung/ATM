package myutil;

import ui.Screen;

public class MyStrings {
	public static String DOLLAR_SIGN = "HKD $";
	public static String TAKE_CARD = "Please take your card";
	public static String TAKE_CASH = "Please take your cash";
	public static String TAKE_RECEIPT = "Please take your receipt";
	public static String WRONG_INPUT = "Invalid inputs, please contact CC Bank (9876-5432)";
	public static String BYE = "Thank you for using our service. Have a good day!";
	public static String ACCOUNT_NOT_FOUND = "The account is not found";
	public static String TRANSFER_SAME_ACCOUNT = "Please do not transfer to the same account";

	public static String getOverDrawnMessage(Double limit) {
		String msg = "Overdrawn (Insufficient funds in your account)";
		if (limit > 0)
			msg += ", your overdrawn limit is: " + Screen.getDollarAmount(limit);
		return msg;
	}
}
