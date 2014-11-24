package atm.gui.utils;

import javax.swing.JFrame;

import myutils.connection.MyDatabaseConnector;
import wholesalerapplication.utils.MyStaticStuff;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

public class AbstractJFrame implements Runnable {
	private Thread thread;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	@Override
	public void run() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public AbstractJFrame() {
		thread = new Thread(this);
	}

	public void pack() {
		frame.pack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void init() throws MalformedURLException, SQLException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		// frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// frame.getContentPane().setLayout(new BorderLayout(0, 0));

		logoPanel = new LogoJPanel(this);
		frame.getContentPane().add(logoPanel, BorderLayout.NORTH);

		controlJPanel = new ControlJPanel(this);
		frame.getContentPane().add(controlJPanel, BorderLayout.WEST);

		contentJPanel = new ContentJPanel(this);
		frame.getContentPane().add(contentJPanel, BorderLayout.CENTER);
	}

	public LogoJPanel getLogoJPanel() {
		return logoPanel;
	}

	public ControlJPanel getControlJPanel() {
		return controlJPanel;
	}

	public ContentJPanel getContentJPanel() {
		return contentJPanel;
	}

	public void switchUser() {
		System.out.println("Show Welcome menu of user type: " + MyStaticStuff.getUserType());
		switch (MyStaticStuff.getUserType()) {
		case "Guest":
			logoPanel.switchToCard(LogoJPanel.STRING_GUEST);
			controlJPanel.switchToCard(ControlJPanel.STRING_WELCOME);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		case "Admin":
			logoPanel.switchToCard(LogoJPanel.STRING_ADMIN);
			controlJPanel.switchToCard(ControlJPanel.STRING_ADMIN_MAIN_MENU);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		case "Agent":
			logoPanel.switchToCard(LogoJPanel.STRING_AGENT);
			controlJPanel.switchToCard(ControlJPanel.STRING_AGENT_MAIN_MENU);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		case "Customer":
			logoPanel.switchToCard(LogoJPanel.STRING_CUSTOMER);
			controlJPanel.switchToCard(ControlJPanel.STRING_CUSTOMER_MAIN_MENU);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		case "Vendor":
			logoPanel.switchToCard(LogoJPanel.STRING_VENDOR);
			controlJPanel.switchToCard(ControlJPanel.STRING_VENDOR_MAIN_MENU);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		case "CEO":
			logoPanel.switchToCard(LogoJPanel.STRING_CEO);
			controlJPanel.switchToCard(ControlJPanel.STRING_CEO_MAIN_MENU);
			contentJPanel.switchToCard(ContentJPanel.STRING_WELCOME);
			break;
		default:
			break;
		}
	}

	public void start() {
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void end() {
		try {
			MyDatabaseConnector.disconnect();
		} catch (SQLException e) {
		}
		frame.dispose();
		thread.destroy();
		System.exit(0);
	}

}
