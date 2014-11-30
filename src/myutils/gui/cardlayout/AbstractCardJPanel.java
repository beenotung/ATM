package myutils.gui.cardlayout;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

public abstract class AbstractCardJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6243990180583440256L;
	public CardLayout cardLayout;

	public AbstractCardJPanel() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		myInit();
	}

	protected abstract void myInit();

	public void addToCards(Component component, String label) {
		add(component);
		cardLayout.addLayoutComponent(component, label);
	}

	public void switchToCard(String label) {
		cardLayout.show(this, label);		
	}
}
