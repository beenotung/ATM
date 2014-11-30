package atm.utils;

import java.io.IOException;

public class FetchImageRunnable implements Runnable {
	private FetchImageNeeder needer;
	private boolean finished = false;

	public FetchImageRunnable(FetchImageNeeder needer) {
		this.needer = needer;
	}

	@Override
	public void run() {
		try {
			needer.fetchImage();
		} catch (IOException e) {
			System.out.println("Failed to get to internet");
			e.printStackTrace();
		}
		finished = true;
	}

	public boolean isFinished() {
		return finished;
	}
}
