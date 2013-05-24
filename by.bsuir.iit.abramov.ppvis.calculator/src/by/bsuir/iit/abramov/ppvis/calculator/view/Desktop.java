package by.bsuir.iit.abramov.ppvis.calculator.view;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.ppvis.calculator.controller.ButtonListener;
import by.bsuir.iit.abramov.ppvis.calculator.util.Buttons;
import by.bsuir.iit.abramov.ppvis.calculator.util.ExpressionTextField;

public class Desktop extends JPanel {
	private static final String	_0	= "0";
	private static final char	DIFFERENCE	= '-';
	private static final char	SUM	= '+';
	private static final char	MULTIPLICATION	= '*';
	private static final char	DIVISION	= '/';
	private static final char	NINE	= '9';
	private static final char	ZERO	= '0';
	private static final char	SQRT	= 't';
	private static final char	LN	= 'n';
	private static final char	LOG	= 'g';
	private static final char	FACTORIAL	= '!';
	private final ExpressionTextField	inputField;
	private final ExpressionTextField	expressionField;
	private static final int			BUTTON_HEIGHT			= 40;
	private static final int			BUTTON_WIDTH			= 100;
	private static final int			BUTTONS_COLUMNS_COUNT	= 5;
	private final Map<Buttons, JButton>	buttons;

	/**
	 * Create the panel.
	 */
	public Desktop() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		buttons = new HashMap<Buttons, JButton>();
		expressionField = new ExpressionTextField("");
		expressionField.setFont(new Font("MV Boli", Font.PLAIN, 20));
		add(expressionField);
		expressionField.setEditable(false);
		expressionField.setColumns(10);
		inputField = new ExpressionTextField(_0);
		inputField.setFont(new Font("MV Boli", Font.PLAIN, 20));
		add(inputField);
		inputField.setColumns(10);

		final JPanel buttonsPanel = new JPanel();
		add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

		final int i = 0;
		int j = 0;
		JPanel rowButtonsPanel = new JPanel();
		buttonsPanel.add(rowButtonsPanel);
		for (final Buttons eButton : Buttons.values()) {
			createButton(rowButtonsPanel, eButton);
			j++;
			if (j == Desktop.BUTTONS_COLUMNS_COUNT) {
				rowButtonsPanel = new JPanel();
				buttonsPanel.add(rowButtonsPanel);
				j = 0;
			}
		}
	}

	public void addLeftBracket() {

		String expression = expressionField.getText();
		if (isLastNumber(expression) || isLastRightBraket(expression)) {
			return;
		}
		expression += "(";
		expressionField.setText(expression);
	}

	public void addNumToExpression(final String str) {

		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
		}

		inputField.setText(input + str);
		inputField.setEmpty(false);

	}

	public void addPoint(final String point) {

		final String input = inputField.getText();
		inputField.setText(input + point);
		inputField.setEmpty(false);
	}

	public void addRightBracket() {

		String expression = expressionField.getText();
		if (expressionField.isRightAndLeftBracketCountEqual()) {
			return;
		}
		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
			if (isLastOperation4(expression) || isLastOperation3(expression)
					|| isLastLeftBraket(expression)) {
				expression += _0;
			}
		} else {
			if (isLastRightBraket(expression) || isLastNumber(expression)) {
				input = "";
			} else if (isLastOperation3(expression)) {
				expression += "(";
			}
		}

		expressionField.setText(expression + input + ")");
		cleanInputField();
	}

	public void backspace() {

		final String expression = expressionField.getText();
		if (expression.length() == 0) {
			return;
		}
		deleteToken();
	}

	private void cleanInputField() {

		inputField.setText(_0);
		inputField.setEmpty(true);
	}

	private void createButton(final JPanel panel, final Buttons eButton) {

		final JButton button = new JButton(eButton.getCaption());
		buttons.put(eButton, button);
		button.setSize(Desktop.BUTTON_WIDTH, Desktop.BUTTON_HEIGHT);
		button.addActionListener(new ButtonListener(eButton, this));
		panel.add(button);
	}

	private void deleteToken() {

		String expression = expressionField.getText();
		if (isLastPoint(expression) || isLastLeftBraket(expression)
				|| isLastRightBraket(expression) || isLastNumber(expression)
				|| isLastOperation4(expression)) {
			expression = expression.substring(0, expression.length() - 1);
		} else if (isLastOperation3(expression)) {
			final char c = expression.toCharArray()[expression.length() - 1];
			switch (c) {
				case FACTORIAL:
					expression = expression.substring(0, expression.length() - 1);
				break;
				case LOG:
					expression = expression.substring(0, expression.length() - 3);
				break;
				case LN:
					expression = expression.substring(0, expression.length() - 2);
				break;
				case SQRT:
					expression = expression.substring(0, expression.length() - 4);
			}
		}
		expressionField.setText(expression);
	}

	private boolean isLastLeftBraket(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == '(';
	}

	private boolean isLastNumber(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c >= ZERO && c <= NINE;
	}

	private boolean isLastOperation3(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		switch (c) {
			case SQRT:// sqrt
				return true;
			case LOG:// log
				return true;
			case LN:// ln
				return true;
			case FACTORIAL:
				return true;
		}
		return false;
	}

	private boolean isLastOperation4(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		switch (c) {
			case DIVISION:
				return true;
			case MULTIPLICATION:
				return true;
			case SUM:
				return true;
			case DIFFERENCE:
				return true;
		}
		return false;
	}

	private boolean isLastPoint(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == '.';
	}

	private boolean isLastRightBraket(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == ')';
	}

	public void operation3(final String operation) {

		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
		}
		String expression = expressionField.getText();

		if (inputField.isEmpty() && expression.length() == 0) {
			expression = operation;
		} else if (isLastNumber(expression) || isLastRightBraket(expression)) {
			return;
		}

		expressionField.setText(expression + operation + input);
		cleanInputField();
	}

	public void operation4(final String operation) {

		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
		}
		String expression = expressionField.getText();

		if (inputField.isEmpty() && expression.length() == 0) {
			expression = _0 + operation;
		} else if (isLastNumber(expression) || isLastRightBraket(expression)) {

			input = "";
		} else if (isLastOperation4(expression) && inputField.isEmpty()) {
			expression = expression.substring(0, expression.length() - 1);
		} else if (inputField.isEmpty()) {
			if (isLastLeftBraket(expression) || isLastOperation3(expression)) {
				expression += _0;
			}
			if (isLastPoint(expression)) {
				expression = expression.substring(0, expression.length() - 1);
			}

		}

		expressionField.setText(expression + input + operation);
		cleanInputField();

	}

	public void reverse() {

		if (!inputField.isEmpty()) {
			final String input = inputField.getText();
			final String expression = expressionField.getText();
			if (isLastLeftBraket(expression) || isLastOperation3(expression)
					|| isLastOperation4(expression)) {
				expressionField.setText(expression + "(1/" + inputField.getText() + ")");
			}
			cleanInputField();
		}
	}

	public void sign() {

		expressionField.changeSign();
	}

}
