package by.bsuir.iit.abramov.ppvis.calculator.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.ppvis.calculator.model.Model;
import by.bsuir.iit.abramov.ppvis.calculator.model.RPN;

public class ContentPane extends JPanel {

	private final Window	parent;
	private final Desktop	desktop;
	private final Tree		tree;
	private final Model		model;

	public ContentPane(final Window parent) {

		this.parent = parent;
		tree = new Tree();
		model = new Model(new RPN(), tree);
		desktop = new Desktop(model);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(desktop);
		add(tree);

	}

	public void expand() {

		desktop.expand();

	}

}
