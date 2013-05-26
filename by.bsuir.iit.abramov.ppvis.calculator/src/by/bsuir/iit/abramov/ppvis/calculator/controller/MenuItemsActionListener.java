package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.util.MenuItem;

public class MenuItemsActionListener implements ActionListener {

	private ActionListener	listener;

	public MenuItemsActionListener(final MenuItem item) {

		listener = null;
		switch (item) {
			case EXPAND:
				listener = new ExpandMenuItemListener();
			break;
		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		if (listener != null) {
			listener.actionPerformed(e);
		}

	}

}
