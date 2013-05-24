package by.bsuir.iit.abramov.ppvis.calculator.view;

import javax.swing.JPanel;

public class ContentPane extends JPanel {

	private final Window	parent;

	public ContentPane(final Window parent) {

		this.parent = parent;
		final Desktop desktop = new Desktop();
		add(desktop);
	}

}
