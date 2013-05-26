package by.bsuir.iit.abramov.ppvis.calculator.util;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNode extends DefaultMutableTreeNode {

	private final TokenCell	cell;

	public TreeNode(final TokenCell cell) {

		super(cell.getValue());
		this.cell = cell;
	}

	public final TokenCell getCell() {

		return cell;
	}
}
