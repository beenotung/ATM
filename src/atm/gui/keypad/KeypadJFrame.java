package atm.gui.keypad;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class KeypadJFrame extends JFrame {
	public static final String STRING_MODE_PASSWORD = "Password";
	public static final String STRING_MODE_ACCOUNTNUMBER = "AccountNumber";
	public static final String STRING_MODE_Amount = "Amount";
	private String mode;
	private boolean dotEnable;
	private int maxLength;

	private JComponent keys[];
	private JPanel numberKeysJPanel;
	private JPanel functionKeysJPanel;
	private String text;

	// constructor sets up GUI
	public KeypadJFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setTitle("Keypad");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		numberKeysJPanel = new JPanel(new GridLayout(4, 3));
		getContentPane().add(numberKeysJPanel);

		functionKeysJPanel = new JPanel(new GridLayout(4, 1));
		getContentPane().add(functionKeysJPanel);

		keys = new JComponent[16]; // array keys contains 16 JButtons
		// initialize all digit key buttons
		for (int i = 0; i <= 9; i++) {
			JButton button = new JButton(String.valueOf(i));
			keys[i] = button;
			button.addActionListener(getNumActionListener(String.valueOf(i)));
		}
		// initialize all function key buttons
		keys[10] = new JButton("Cancel");
		keys[11] = new JButton("Correct");
		keys[12] = new JButton("Enter");
		keys[13] = new JPanel();
		{
			JButton button = new JButton("00");
			keys[14] = button;
			button.addActionListener(getNumActionListener("00"));
		}
		{
			JButton button = new JButton(".");
			keys[15] = button;
			button.addActionListener(getDotActionListener());
		}

		// add buttons to keyPadJPanel panel
		// 7 8 9
		for (int i = 7; i <= 9; i++)
			numberKeysJPanel.add(keys[i]);
		// 4 5 6
		for (int i = 4; i <= 6; i++)
			numberKeysJPanel.add(keys[i]);
		// 1 2 3
		for (int i = 1; i <= 3; i++)
			numberKeysJPanel.add(keys[i]);

		// 0 . 00
		numberKeysJPanel.add(keys[0]);
		numberKeysJPanel.add(keys[15]);
		numberKeysJPanel.add(keys[14]);

		for (int i = 10; i <= 13; i++)
			functionKeysJPanel.add(keys[i]);

		text = "";
		switchMode(STRING_MODE_PASSWORD);
	} // end CalculatorFrame constructor

	private void switchMode(String stringModePassword) {
		mode = stringModePassword;
		switch (mode) {
		case STRING_MODE_PASSWORD:
			dotEnable = false;
			maxLength=5;
			break;
		case STRING_MODE_Amount:
			dotEnable = true;
			//maxLength
			break;
		case STRING_MODE_ACCOUNTNUMBER:
			dotEnable = false;
			break;
		}
	}

	private boolean hasDot() {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '.')
				return true;
		}
		return false;
	}

	private ActionListener getNumActionListener(final String content) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!hasDot())
					text += content;
				else {

				}
			}
		};
	}

	private ActionListener getDotActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!hasDot())
					text += '.';
			}
		};
	}

	public void calcBounds() {
		pack();
		setVisible(true);
		Rectangle client = getBounds();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getBounds();
		int x = screen.width - client.width;
		int y = screen.height - client.height;
		setLocation(x, y);
	}
} // end class CalculatorFrame 