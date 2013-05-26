package by.bsuir.iit.abramov.ppvis.calculator.view;

import javax.swing.JMenuBar;

import by.bsuir.iit.abramov.ppvis.calculator.controller.MenuItemsActionListener;
import by.bsuir.iit.abramov.ppvis.calculator.util.ExtJMenuItem;
import by.bsuir.iit.abramov.ppvis.calculator.util.MenuItem;

public class Menu extends JMenuBar {
	private final ContentPane	contentPane;

	public Menu(final ContentPane contentPane) {

		this.contentPane = contentPane;
		initialize();
	}

	public void initialize() {

		for (final MenuItem item : MenuItem.values()) {
			final ExtJMenuItem menuItem = new ExtJMenuItem(item.getText(), contentPane);
			add(menuItem);
			menuItem.addActionListener(new MenuItemsActionListener(item));
		}
	}
}
