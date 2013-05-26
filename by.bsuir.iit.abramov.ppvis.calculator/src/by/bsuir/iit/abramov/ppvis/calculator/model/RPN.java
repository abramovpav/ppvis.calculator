package by.bsuir.iit.abramov.ppvis.calculator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.calculator.util.Couple;
import by.bsuir.iit.abramov.ppvis.calculator.util.Token;
import by.bsuir.iit.abramov.ppvis.calculator.util.TokenCell;
import by.bsuir.iit.abramov.ppvis.calculator.util.TokenType;
import by.bsuir.iit.abramov.ppvis.calculator.util.Util;

public class RPN {
	private final List<TokenCell>					expression;
	private final List<TokenCell>					rpn;
	private final Stack<TokenCell>					stack;
	private final Map<Integer, Couple>				steps;
	private boolean									isCalculated;
	private double									result;

	private static final Map<TokenType, Integer>	operations	= new HashMap<TokenType, Integer>();
	static {
		RPN.operations.put(TokenType.L_BRACKET, 0);
		RPN.operations.put(TokenType.R_BRACKET, 0);
		RPN.operations.put(TokenType.NUM, 0);
		RPN.operations.put(TokenType.Op1, 1);
		RPN.operations.put(TokenType.Op2, 2);
		RPN.operations.put(TokenType.Op3, 3);
	}

	public RPN() {

		expression = new ArrayList<TokenCell>();
		steps = new HashMap<Integer, Couple>();
		stack = new Stack<TokenCell>();
		rpn = new ArrayList<TokenCell>();
		isCalculated = false;
	}

	private void addToStepsAndRPN(final List<TokenCell> rpn, final int i,
			final TokenCell cell, final double num1, final TokenCell cell1) {

		final TokenCell cell2 = new TokenCell(TokenType.NUM, Double.toString(num1));
		rpn.add(i >= 0 ? i : 0, cell2);
		steps.put(steps.size(), new Couple(rpn, cell.getValue() + " " + cell1.getValue()));
	}

	private void addToStepsAndRPN(final List<TokenCell> rpn, final int i,
			final TokenCell cell, final double num1, final TokenCell cell1,
			final TokenCell cell2) {

		final TokenCell cell3 = new TokenCell(TokenType.NUM, Double.toString(num1));
		rpn.add(i >= 0 ? i : 0, cell3);
		steps.put(steps.size(), new Couple(rpn, cell2.getValue() + " " + cell.getValue()
				+ " " + cell1.getValue()));
	}

	private void addToStepsAndRPN(final List<TokenCell> rpn, final int i,
			final TokenCell cell, final TokenCell cell1, final long num2) {

		final TokenCell cell2 = new TokenCell(TokenType.NUM, Long.toString(num2));
		rpn.add(i >= 0 ? i : 0, cell2);
		steps.put(steps.size(), new Couple(rpn, cell.getValue() + " " + cell1.getValue()));
	}

	public void calculateSteps() {

		steps.clear();
		calculateSteps(rpn);
	}

	private void calculateSteps(final List<TokenCell> inputRPN) {

		steps.put(0, new Couple(rpn, "BEGIN"));
		try {
			final List<TokenCell> rpn = new ArrayList<TokenCell>();
			rpn.addAll(inputRPN);

			for (int i = 0; i < rpn.size(); ++i) {
				final TokenCell cell = rpn.get(i);
				switch (cell.getToken()) {
					case NUM:
						if (rpn.size() == 1) {
							steps.put(steps.size(), new Couple(rpn, "END"));
						}
					break;
					case Op1:
						i = processOperation(rpn, i, cell);
					break;
					case Op2:
						i = processOperation(rpn, i, cell);
					break;
					case Op3:
						i = processOperation3(rpn, i, cell);
					break;
				}
			}
			result = Double.parseDouble(rpn.get(0).getValue());
		} catch (final IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private int calculateStringRPN(final int num) {

		final TokenCell cell = expression.get(num);
		switch (cell.getToken()) {
			case Op3:
				stack.push(cell);
			break;
			case L_BRACKET:
				stack.push(cell);
			break;
			case NUM:
				rpn.add(new TokenCell(cell.getToken(), cell.getValue()));
			break;
			case Op1:
				processOperation(cell);
			break;
			case Op2:
				processOperation(cell);
			break;
			case R_BRACKET:
				while (!stack.isEmpty()) {
					final TokenCell el = stack.pop();
					if (el.getToken() == TokenType.L_BRACKET) {
						break;
					}
					rpn.add(new TokenCell(el.getToken(), el.getTokenValue()));
				}
				if (stack.isEmpty()) {
					// ERROR
				}
			break;
		}
		if (num + 1 < expression.size() - 1) {
			calculateStringRPN(num + 1);
		}
		return -1;
	}

	public void clear() {

		expression.clear();
		steps.clear();
		stack.clear();
		rpn.clear();
		isCalculated = false;
		result = 0;
	}

	public double getResult() {

		return result;
	}

	public List<TokenCell> getRPN(final List<TokenCell> expression) {

		prepare(expression);
		calculateStringRPN(0);
		while (stack.size() != 0 && stack.peek().getToken() != TokenType.L_BRACKET) {
			final TokenCell el = stack.pop();
			rpn.add(el);
		}
		isCalculated = true;
		return rpn;
	}

	public final Map<Integer, Couple> getSteps() {

		return steps;
	}

	public int getStepsCount() {

		return steps.size();
	}

	public Couple getStepTo(final Integer num) {

		if (steps.containsKey(num)) {
			return steps.get(num);
		} else {
			return null;
		}
	}

	public String getStringFromList(final List<TokenCell> list) {

		String text = "";
		for (final TokenCell cell : list) {
			text += cell.getValue();
		}

		return text;
	}

	public boolean isCalculated() {

		return isCalculated;
	}

	public void prepare(final List<TokenCell> expression) {

		clear();
		this.expression.add(new TokenCell(TokenType.L_BRACKET, Token.L_BRAKET));
		this.expression.addAll(expression);
		this.expression.add(new TokenCell(TokenType.R_BRACKET, Token.R_BRAKET));

	}

	private int processOperation(final List<TokenCell> rpn, int i, final TokenCell cell) {

		rpn.remove(i);
		i--;
		double num1, num2;
		TokenCell cell1, cell2;
		if (i < 1) {
			throw new IllegalArgumentException("Îøèáêà â ÎÏÇ1");
		}
		cell1 = rpn.get(i);
		rpn.remove(i);
		i--;
		cell2 = rpn.get(i);
		rpn.remove(i);
		// i--;
		if (cell1.getToken() != TokenType.NUM || cell2.getToken() != TokenType.NUM) {
			throw new IllegalArgumentException("Îøèáêà â ÎÏÇ2");
		}

		num1 = Double.parseDouble(cell1.getValue());
		num2 = Double.parseDouble(cell2.getValue());
		switch (cell.getTokenValue()) {
			case DIFFERENCE:
				num1 = num2 - num1;
				addToStepsAndRPN(rpn, i, cell, num1, cell1, cell2);
			break;
			case SUM:
				num1 = num2 + num1;
				addToStepsAndRPN(rpn, i, cell, num1, cell1, cell2);

			break;
			case MULTIPLICATION:
				num1 = num2 * num1;
				addToStepsAndRPN(rpn, i, cell, num1, cell1, cell2);
			break;
			case DIVISION:
				if (num1 == 0) {
					throw new IllegalArgumentException("Division by zero");
				}
				num1 = num2 / num1;
				addToStepsAndRPN(rpn, i, cell, num1, cell1, cell2);
			break;
		}
		return i;
	}

	private void processOperation(final TokenCell cell) {

		final int priority = RPN.operations.get(cell.getToken());
		TokenCell el;
		if (stack.size() != 0) {
			while (RPN.operations.get((el = stack.peek()).getToken()) > priority) {
				el = stack.pop();
				rpn.add(el);
			}
		}
		stack.push(cell);
	}

	private int processOperation3(final List<TokenCell> rpn, int i, final TokenCell cell) {

		rpn.remove(i);
		i--;
		double num1;
		TokenCell cell1;
		final TokenCell cell2;
		if (i < 0) {
			throw new IllegalArgumentException("Îøèáêà â ÎÏÇ3");
		}
		cell1 = rpn.get(i);
		rpn.remove(i);
		if (cell1.getToken() != TokenType.NUM) {
			throw new IllegalArgumentException("Îøèáêà â ÎÏÇ4");
		}

		num1 = Double.parseDouble(cell1.getValue());
		switch (cell.getTokenValue()) {
			case LOG:
				if (num1 <= 0) {
					throw new IllegalArgumentException("log: " + num1 + " <= 0");
				}
				num1 = Math.log10(num1);
				addToStepsAndRPN(rpn, i, cell, num1, cell1);
			break;
			case LN:
				if (num1 <= 0) {
					throw new IllegalArgumentException("ln: " + num1 + " <= 0");
				}
				num1 = Math.log(num1);
				addToStepsAndRPN(rpn, i, cell, num1, cell1);

			break;
			case SQRT:
				if (num1 <= 0) {
					throw new IllegalArgumentException("sqrt: " + num1 + " <= 0");
				}
				num1 = Math.sqrt(num1);
				addToStepsAndRPN(rpn, i, cell, num1, cell1);
			break;
			case FACTORIAL:
				if (num1 < 0) {
					throw new IllegalArgumentException("factorial: " + num1 + " < 0");
				}
				if (num1 > 20) {
					throw new IllegalArgumentException("factorial: " + num1 + " > 20");
				}
				final long num2 = Util.factorial(Math.round(num1));
				addToStepsAndRPN(rpn, i, cell, cell1, num2);
			break;
		}
		return i;
	}

	public String toStandartForm(final List<TokenCell> list) {

		TokenCell cell;
		final TokenCell cell1, cell2, newCell;
		int priority;
		final int cellPriority;
		stack.clear();
		for (int i = 0; i < list.size(); ++i) {
			cell = list.get(i);
			priority = RPN.operations.get(cell.getToken());
			switch (cell.getToken()) {
				case NUM:
					stack.push(cell);
				break;
				case Op1:
					toStandartOperationProcessing(cell, priority);
				break;
				case Op2:
					toStandartOperationProcessing(cell, priority);
				break;
				case Op3:
					toStandartOperation3Processing(cell, priority);
				break;
			}
		}
		return stack.pop().getValue();
	}

	private void toStandartOperation3Processing(final TokenCell cell, final int priority) {

		TokenCell cell1;
		TokenCell newCell;
		int cellPriority;
		cell1 = stack.pop();
		cellPriority = RPN.operations.get(cell1.getToken());
		if (cellPriority != 0 && cellPriority < priority) {
			newCell = new TokenCell(cell.getToken(), cell.getValue() + "("
					+ cell1.getValue() + ")");
		} else {
			newCell = new TokenCell(cell.getToken(), cell.getValue() + cell1.getValue());
		}
		stack.push(newCell);
	}

	private void toStandartOperationProcessing(final TokenCell cell, final int priority) {

		TokenCell cell1;
		TokenCell cell2;
		TokenCell newCell;
		int cellPriority;
		cell2 = stack.pop();
		cell1 = stack.pop();
		cellPriority = RPN.operations.get(cell1.getToken());
		if (cellPriority != 0 && cellPriority < priority) {
			newCell = new TokenCell(cell.getToken(), "(" + cell1.getValue() + ")"
					+ cell.getValue());
		} else {
			newCell = new TokenCell(cell.getToken(), cell1.getValue() + cell.getValue());
		}
		cellPriority = RPN.operations.get(cell2.getToken());
		if (cellPriority != 0 && cellPriority < priority) {
			newCell.setValue(newCell.getValue() + "(" + cell2.getValue() + ")");
		} else {
			newCell.setValue(newCell.getValue() + cell2.getValue());
		}
		stack.push(newCell);
	}
}
