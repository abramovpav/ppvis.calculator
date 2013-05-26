package by.bsuir.iit.abramov.ppvis.calculator.util;

import javax.swing.JMenuItem;

import by.bsuir.iit.abramov.ppvis.calculator.view.ContentPane;

public class ExtJMenuItem extends JMenuItem {
	private final ContentPane	contentPane;

	public ExtJMenuItem(final String text, final ContentPane contentPane) {

		super(text);
		this.contentPane = contentPane;
	}

	public final ContentPane getContentPane() {

		return contentPane;
	}
}
