package by.bsuir.iit.abramov.ppvis.calculator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeSelectionModel;

import by.bsuir.iit.abramov.ppvis.calculator.model.RPN;
import by.bsuir.iit.abramov.ppvis.calculator.model.TreeModel;
import by.bsuir.iit.abramov.ppvis.calculator.util.Couple;
import by.bsuir.iit.abramov.ppvis.calculator.util.TokenCell;
import by.bsuir.iit.abramov.ppvis.calculator.util.TokenType;
import by.bsuir.iit.abramov.ppvis.calculator.util.TreeNode;

public class Tree extends JPanel {

	class ListModel extends AbstractListModel {
		private final Map<Integer, Couple>	steps;

		public ListModel(final Map<Integer, Couple> steps) {

			this.steps = new HashMap<Integer, Couple>();
			this.steps.putAll(steps);
		}

		@Override
		public Object getElementAt(final int index) {

			return "123";// steps.get(index).showString;
		}

		@Override
		public int getSize() {

			return steps.size();
		}

		public void setSteps(final Map<Integer, Couple> steps) {

			this.steps.clear();
			this.steps.putAll(steps);
		}
	}

	public static final String			BUTTON_CALCULATE	= "Calculate";
	public static final String			BUTTON_PREV			= "Prev";
	public static final String			BUTTON_NEXT			= "Next";
	private TreeNode					root;
	private int							stepNum;
	private List<TokenCell>				rpn_c;
	private final TreeModel				treeModel;
	private final JTree					tree;
	private RPN							RPN					= null;
	private final Map<String, JButton>	buttons				= new HashMap<String, JButton>();

	public Tree() {

		setLayout(new BorderLayout());

		root = new TreeNode(new TokenCell(TokenType.NUM, "root"));// this.rpn.get(this.rpn.size()
																	// - 1));

		final JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPane, BorderLayout.SOUTH);

		createButton(buttonPane, Tree.BUTTON_NEXT);
		createButton(buttonPane, Tree.BUTTON_PREV);
		createButton(buttonPane, Tree.BUTTON_CALCULATE);

		final JPanel contPanel = new JPanel();
		add(contPanel, BorderLayout.CENTER);
		contPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contPanel.setLayout(new BoxLayout(contPanel, BoxLayout.X_AXIS));

		final JPanel panel = new JPanel();
		contPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		treeModel = new TreeModel(root);
		tree = new JTree(treeModel);

		final JScrollPane scroll = new JScrollPane(tree);
		panel.add(scroll);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	private void createButton(final JPanel buttonPane, final String text) {

		final JButton button = new JButton(text);
		buttonPane.add(button);
		buttons.put(text, button);
	}

	public void createTree(final List<TokenCell> rpn, final RPN RPN) {

		this.RPN = RPN;
		rpn_c = new ArrayList<TokenCell>();
		rpn_c.addAll(rpn);
		root = new TreeNode(getLastFromList(rpn_c));
		treeModel.setRoot(root);
		tree.revalidate();
		tree.updateUI();
		deleteLast(rpn_c);
		TreeNode node;
		switch (root.getCell().getToken()) {
			case Op1:
				node = findChild(root);
				node = findChild(root);
			break;
			case Op2:
				node = findChild(root);
				node = findChild(root);
			break;
			case Op3:
				node = findChild(root);
			break;
		}
	}

	private void deleteLast(final List<TokenCell> list) {

		if (list.size() != 0) {
			list.remove(list.size() - 1);
		}
	}

	private TreeNode findChild(final TreeNode node) {

		final TokenCell cell = getLastFromList(rpn_c);
		if (cell == null) {
			return null;
		}
		final TreeNode currNode = new TreeNode(cell);
		node.add(currNode);
		deleteLast(rpn_c);
		TreeNode newNode;
		switch (cell.getToken()) {
			case Op1:
				newNode = findChild(currNode);
				if (newNode != null) {
					currNode.add(newNode);
				}
				newNode = findChild(currNode);
				if (newNode != null) {
					currNode.add(newNode);
				}
			break;
			case Op2:
				newNode = findChild(currNode);
				if (newNode != null) {
					currNode.add(newNode);
				}
				newNode = findChild(currNode);
				if (newNode != null) {
					currNode.add(newNode);
				}
			break;
			case Op3:
				newNode = findChild(currNode);
				if (newNode != null) {
					currNode.add(newNode);
				}
			break;

		}
		return currNode;
	}

	public void fullForward() {

	}

	public final JButton getButton(final String text) {

		if (buttons.containsKey(text)) {
			return buttons.get(text);
		} else {
			return null;
		}
	}

	private TokenCell getLastFromList(final List<TokenCell> list) {

		if (list.size() != 0) {
			return list.get(list.size() - 1);
		} else {
			return null;
		}
	}

	public void stepBack() {

		if (RPN == null) {
			return;
		}
		if (stepNum - 1 >= 0) {
			stepNum--;
		}
		updateTree();
	}

	public void stepForward() {

		if (RPN == null) {
			return;
		}
		if (stepNum + 1 < RPN.getStepsCount()) {
			stepNum++;
		}
		updateTree();
	}

	private void updateTree() {

		// final Map<Integer, Couple> steps = RPN.getStepTo(stepNum);

	}

}
