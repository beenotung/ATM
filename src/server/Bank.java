package server;

import java.util.Arrays;
import java.util.Calendar;

public class Bank {
	public static final int[] HOLIDAY_DAY_OF_WEEKs = { Calendar.SUNDAY, Calendar.SATURDAY };

	public static boolean isOperate() {
		boolean result = true;
		Calendar now = Calendar.getInstance();
		if (Arrays.asList(HOLIDAY_DAY_OF_WEEKs).contains(now.get(Calendar.DAY_OF_WEEK)))
			result = false;
		int time = 10000 + now.get(Calendar.HOUR) * 100 + now.get(Calendar.MINUTE);
		if ((time < 10900) || (time >= 11630))
			result = false;
		return result;
	}
}
