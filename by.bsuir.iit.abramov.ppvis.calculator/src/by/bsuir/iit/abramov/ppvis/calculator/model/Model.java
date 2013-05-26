package by.bsuir.iit.abramov.ppvis.calculator.model;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.calculator.controller.CalculateButtonListener;
import by.bsuir.iit.abramov.ppvis.calculator.controller.NextButtonListener;
import by.bsuir.iit.abramov.ppvis.calculator.controller.PrevButtonListener;
import by.bsuir.iit.abramov.ppvis.calculator.util.Couple;
import by.bsuir.iit.abramov.ppvis.calculator.util.ExpressionTextField;
import by.bsuir.iit.abramov.ppvis.calculator.util.InputTextField;
import by.bsuir.iit.abramov.ppvis.calculator.util.TokenCell;
import by.bsuir.iit.abramov.ppvis.calculator.view.Tree;

public class Model {

	private final RPN	RPN;
	private final Tree	tree;
	private int			step;

	public Model(final RPN rpn, final Tree tree) {

		RPN = rpn;
		this.tree = tree;

		JButton button = tree.getButton(Tree.BUTTON_NEXT);

		button.addActionListener(new NextButtonListener(this));
		button = tree.getButton(Tree.BUTTON_PREV);
		button.addActionListener(new PrevButtonListener(this));
		button = tree.getButton(Tree.BUTTON_CALCULATE);
		button.addActionListener(new CalculateButtonListener(this));
	}

	public double calculate(final InputTextField inputField) {

		RPN.calculateSteps();
		step = 0;
		final double text = RPN.getResult();
		inputField.setText(Double.toString(text));
		return text;
	}

	public void clear() {

		step = 0;
		RPN.clear();
		tree.clear();
	}

	public void createRPNStringAndTree(final ExpressionTextField expressionField,
			final InputTextField inputField) {

		if (expressionField.isOK()) {
			final List<TokenCell> rpnList = RPN.getRPN(expressionField.getTokens());
			tree.createTree(rpnList, RPN);
			final String text = RPN.getStringFromList(rpnList);
			System.out.println(text);
			calculate(inputField);

		} else {
			JOptionPane.showMessageDialog(null, "Ошибка в формуле");
		}
	}

	public final RPN getRPN() {

		return RPN;
	}

	public void stepBack() {

		if (step - 1 >= 0) {
			step--;
			final Couple list = RPN.getStepTo(step);
			if (list != null) {
				JOptionPane.showMessageDialog(null, RPN.toStandartForm(list.rpn));
				tree.createTree(list.rpn, RPN);
			}
		}
	}

	public void stepForward() {

		if (step + 1 < RPN.getStepsCount()) {
			step++;
			final Couple list = RPN.getStepTo(step);
			if (list != null) {
				JOptionPane.showMessageDialog(null, RPN.toStandartForm(list.rpn));
				tree.createTree(list.rpn, RPN);
			}
		}

	}

	public void stepFullForward() {

		step = RPN.getStepsCount() - 1;
		final Couple list = RPN.getStepTo(step);
		if (list != null) {
			JOptionPane.showMessageDialog(null, RPN.toStandartForm(list.rpn));
			tree.createTree(list.rpn, RPN);
		}
	}

}
