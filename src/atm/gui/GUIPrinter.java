package atm.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GUIPrinter extends OutputStream {
	public JTextArea textArea;
	private static PrintStream systemPrintStream = System.out;

	public GUIPrinter(JTextArea textArea) {
		this.textArea = textArea;

	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		final String text = new String(bytes, offset, length);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				textArea.append(text);
			}
		});
	}

	@Override
	public void write(int b) throws IOException {
		write(new byte[] { (byte) b }, 0, 1);
	}

	public void start() {
		System.setOut(new PrintStream(this));
	}

	public void stop() {
		System.setOut(systemPrintStream);
	}
}
