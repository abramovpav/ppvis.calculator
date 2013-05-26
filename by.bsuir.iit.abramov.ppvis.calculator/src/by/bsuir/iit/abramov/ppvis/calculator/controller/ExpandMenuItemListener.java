package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.util.ExtJMenuItem;
import by.bsuir.iit.abramov.ppvis.calculator.view.ContentPane;

public class ExpandMenuItemListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

		final ExtJMenuItem item = (ExtJMenuItem) e.getSource();
		final ContentPane contentPane = item.getContentPane();
		contentPane.expand();
	}

}
