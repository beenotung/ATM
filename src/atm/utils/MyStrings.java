package atm.utils;

import atm.core.Screen;

public class MyStrings {
	/** Business part **/
	public static final String DOLLAR_SIGN = "HKD $";
	public static final String TAKE_CARD = "Please take your card";
	public static final String TAKE_CASH = "Please take your cash";
	public static final String TAKE_RECEIPT = "Please take your receipt";
	public static final String WRONG_INPUT = "Invalid inputs, please contact CC Bank (9876-5432)";
	public static final String BYE = "Thank you for using our service. Have a good day!";
	public static final String ACCOUNT_NOT_FOUND = "The account is not found";
	public static final String TRANSFER_SAME_ACCOUNT = "Please do not transfer to the same account";
	public static final String TRANSFER_SUCCEED = "The transfer has been done";

	/** System part **/
	public static final String INTERNET_ERROR = "Internet connection is not stable, Please contact 9876-5432";

	public static String getOverDrawnMessage(Double limit) {
		String msg = "Overdrawn (Insufficient funds in your account)";
		if (limit > 0)
			msg += ", your overdrawn limit is: "
					+ Screen.getDollarAmount(limit);
		return msg;
	}
}
