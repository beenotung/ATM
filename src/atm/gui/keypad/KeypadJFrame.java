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
import javax.swing.JPasswordField;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import atm.core.ATM;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.monitor.mainscreen.WithDrawalJPanel;

public class KeypadJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<KeypadJFrame> contents = new Vector<KeypadJFrame>();

	public static final String STRING_MODE_PASSWORD = "Password";
	public static final String STRING_MODE_ACCOUNTNUMBER = "AccountNumber";
	public static final String STRING_MODE_AMOUNT = "Amount";
	public static final String STRING_MODE_CASH_AMOUNT = "Cash Amount";
	public static final String STRING_MODE_NULL = "Null";
	private String mode;
	private boolean dotEnable;
	private int maxLength;

	private JComponent keys[];
	private JPanel numberKeysJPanel;
	private JPanel functionKeysJPanel;
	private JTextComponent textComponent;

	// constructor sets up GUI
	public KeypadJFrame() {
		contents.add(this);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setTitle("Keypad");
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

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
		{
			JButton button = new JButton(KeyPadButtonIcons.IMAGEICON_CANCEL);
			keys[10] = button;
			button.addActionListener(getCancelActionListener());
		}
		{
			JButton button = new JButton(KeyPadButtonIcons.IMAGEICON_CLEAR);
			keys[11] = button;
			button.addActionListener(getClearActionListener());
		}
		{
			JButton button = new JButton(KeyPadButtonIcons.IMAGEICON_ENTER);
			keys[12] = button;
			button.addActionListener(getEnterActionListener());
		}
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

		switchMode(STRING_MODE_NULL);
		switchTarget(null);
	} // end CalculatorFrame constructor

	/** instance methods **/
	private void switchMode(String stringModePassword) {
		mode = stringModePassword;
		switch (mode) {
		case STRING_MODE_PASSWORD:
			dotEnable = false;
			maxLength = 5;
			break;
		case STRING_MODE_AMOUNT:
			dotEnable = true;
			maxLength = 8;
			break;
		case STRING_MODE_ACCOUNTNUMBER:
			dotEnable = false;
			maxLength = 13;
			break;
		case STRING_MODE_CASH_AMOUNT:
			dotEnable = false;
			maxLength = 5;
			break;
		case STRING_MODE_NULL:
			dotEnable = false;
			maxLength = 0;
			break;
		}
	}

	public void switchTarget(JTextComponent textComponent) {
		this.textComponent = textComponent;
	}

	private boolean hasDot() throws BadLocationException {
		for (char c : getText().toCharArray())
			if (c == '.')
				return true;
		return false;
	}

	public String getText() throws BadLocationException {
		return textComponent.getText();
	}

	public void insertText(String str) throws BadLocationException {
		textComponent.getDocument().insertString(
				textComponent.getCaretPosition(), str, null);
	}

	public int getDecimalPlace(String text) {
		int decimalPlace = 0;
		boolean meetDot = false;
		for (char c : text.toCharArray()) {
			if (c == '.')
				meetDot = true;
			else if (meetDot)
				decimalPlace++;
		}
		return decimalPlace;
	}

	private ActionListener getNumActionListener(final String content) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textComponent.getDocument().getLength() >= maxLength)
						return;
					if ((getDecimalPlace(textComponent.getText()) < 2)
							&& !((textComponent.getDocument().getLength() == 0) && (content == "00")))
						insertText(content);
				} catch (BadLocationException e1) {
					insertTextAlternative(content);
				} catch (NullPointerException e2) {
				}
			}
		};
	}

	private ActionListener getDotActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if ((!hasDot()) && dotEnable) {
						if (textComponent.getDocument().getLength() == 0)
							insertText("0");
						insertText(".");
					}
				} catch (BadLocationException e1) {
					insertTextAlternative(".");
				} catch (NullPointerException e2) {
				}
			}
		};
	}

	private ActionListener getCancelActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MonitorJFrame.returnButtonClick();
			}
		};
	}

	private ActionListener getClearActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textComponent.setText("");
				} catch (NullPointerException e2) {
				}
			}
		};
	}

	private ActionListener getEnterActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
				case STRING_MODE_PASSWORD:
					System.out.println("[Enter] password mode");
					ATM.getATM().authenticateUser(
							String.valueOf(((JPasswordField) textComponent)
									.getPassword()));
					break;
				case STRING_MODE_CASH_AMOUNT:
					WithDrawalJPanel.enterButtonClickStatic();
					break;
				}
			}
		};
	}

	private void insertTextAlternative(String content) {
		textComponent.setText(textComponent.getText() + content);
	}

	public void calcBounds() {
		setVisible(true);
		pack();
		Rectangle client = getBounds();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().getBounds();
		int x = screen.width - client.width - 10;
		int y = screen.height - client.height - 10;
		setLocation(x, y);
	}

	/** static connector to instance methods **/
	public static void switchTargetStatic(JTextComponent textComponent,
			String mode) {
		for (KeypadJFrame keypadJFrame : contents) {
			keypadJFrame.switchTarget(textComponent);
			keypadJFrame.switchMode(mode);
		}
	}

	public static void switchTargetStatic(String mode) {
		for (KeypadJFrame keypadJFrame : contents) {
			keypadJFrame.switchMode(mode);
		}
	}
} // end class CalculatorFrame 