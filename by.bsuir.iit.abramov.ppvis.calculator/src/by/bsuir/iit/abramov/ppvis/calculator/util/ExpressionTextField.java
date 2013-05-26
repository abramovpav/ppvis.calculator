package by.bsuir.iit.abramov.ppvis.calculator.util;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTextField extends InputTextField {

	private static final char		R_BRACKET_CHAR	= ')';
	private static final char		L_BRACKET_CHAR	= '(';
	private static final String		R_BRACKET		= ")";
	private static final String		L_BRACKET		= "(";
	private final List<TokenCell>	tokens			= new ArrayList<TokenCell>();
	private static final char		DIFFERENCE		= '-';
	private static final char		SUM				= '+';
	private static final char		MULTIPLICATION	= '*';
	private static final char		DIVISION		= '/';
	private static final char		NINE			= '9';
	private static final char		ZERO			= '0';
	private static final char		SQRT			= 't';
	private static final char		LN				= 'n';
	private static final char		LOG				= 'g';
	private static final char		FACTORIAL		= '!';

	public ExpressionTextField(final String text) {

		super(text);
		setEditable(false);
	}

	public void addLeftBracket() {

		String expression = getText();
		if (isLastNumber(expression) || isLastRightBraket(expression)) {
			return;
		}
		expression += ExpressionTextField.L_BRACKET;
		put(TokenType.L_BRACKET, Token.L_BRAKET);// ExpressionTextField.L_BRACKET);
		updateText();
	}

	public void addRightBracket(final InputTextField inputField) {

		String expression = getText();
		if (isRightAndLeftBracketCountEqual()) {
			return;
		}
		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
			if (isLastOperation4(expression) || isLastOperation3(expression)
					|| isLastLeftBraket(expression)) {
				expression += InputTextField._0;
				put(TokenType.NUM, InputTextField._0);
			}
		} else {
			if (isLastRightBraket(expression) || isLastNumber(expression)) {
				input = "";
			} else if (isLastOperation3(expression)) {
				expression += ExpressionTextField.L_BRACKET;
				put(TokenType.L_BRACKET, Token.L_BRAKET);// ExpressionTextField.L_BRACKET);
			}
		}

		put(TokenType.NUM, input);
		put(TokenType.R_BRACKET, Token.R_BRAKET);// ExpressionTextField.R_BRACKET);
		updateText();
		inputField.cleanInputField();
	}

	public void CE(final InputTextField inputField) {

		tokens.clear();
		updateText();
		inputField.cleanInputField();
	}

	public void deleteLastToken() {

		final TokenCell token = tokens.get(tokens.size() - 1);
		System.out.println("delete " + token.getToken() + " " + token.getValue());
		tokens.remove(token);
	}

	public void deleteToken() {

		String expression = getText();
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
		deleteLastToken();
		updateText();
	}

	public void equal(final InputTextField inputField) {

		final String expression = getText();
		final String input = inputField.getText();
		if (!isLastNumber(expression) && !isLastLeftBraket(expression)
				&& !isLastRightBraket(expression)) {
			if (!inputField.isEmpty()) {
				put(TokenType.NUM, input);
				inputField.cleanInputField();
			}
		}
		updateText();
	}

	public int getLeftBracketCount() {

		int counter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == ExpressionTextField.L_BRACKET_CHAR) {
				counter++;
			}
		}
		return counter;
	}

	public int getRightBracketCount() {

		int counter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == ExpressionTextField.R_BRACKET_CHAR) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public String getText() {

		String text = super.getText();
		final int index = text.indexOf('.');
		if (index != -1) {
			if (index == text.length() - 1) {
				text = text.substring(0, text.length() - 1);
			}
		}
		return text;
	}

	public final List<TokenCell> getTokens() {

		return tokens;
	}

	private boolean isAllFieldsEmpty(final InputTextField inputField,
			final String expression) {

		return inputField.isEmpty() && expression.length() == 0;
	}

	public boolean isLastLeftBraket(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == ExpressionTextField.L_BRACKET_CHAR;
	}

	public boolean isLastNumber(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c >= ExpressionTextField.ZERO && c <= ExpressionTextField.NINE;
	}

	public boolean isLastOperation3(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		switch (c) {
			case SQRT:
				return true;
			case LOG:
				return true;
			case LN:
				return true;
			case FACTORIAL:
				return true;
		}
		return false;
	}

	public boolean isLastOperation4(final String str) {

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

	public boolean isLastPoint(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == InputTextField.POINT;
	}

	public boolean isLastRightBraket(final String str) {

		if (str.length() == 0) {
			return false;
		}
		final char c = str.toCharArray()[str.length() - 1];
		return c == ExpressionTextField.R_BRACKET_CHAR;
	}

	private boolean isNecessityToDeleteLastToken(final InputTextField inputField,
			final String expression) {

		return (isLastOperation4(expression) || isLastPoint(expression))
				&& inputField.isEmpty();
	}

	private boolean isNecessityToWrite0(final InputTextField inputField,
			final String expression) {

		return inputField.isEmpty()
				&& (isLastLeftBraket(expression) || isLastOperation3(expression));
	}

	public boolean isOK() {

		if (isRightAndLeftBracketCountEqual()) {
			return !(getRightBracketCount() == 0 && !isLastNumber(getText()));
		}
		return false;
	}

	public boolean isRightAndLeftBracketCountEqual() {

		int leftCounter = 0, rightCounter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == ExpressionTextField.L_BRACKET_CHAR) {
				leftCounter++;
			}
			if (c == ExpressionTextField.R_BRACKET_CHAR) {
				rightCounter++;
			}
		}
		return leftCounter == rightCounter;
	}

	public void operation3(final Token operation, final InputTextField inputField) {

		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
		}
		final String expression = getText();

		if (isLastNumber(expression) || isLastRightBraket(expression)) {
			return;
		}

		put(TokenType.Op3, operation);
		put(TokenType.NUM, input);
		updateText();
		inputField.cleanInputField();
	}

	public void operation4(final Token operation, final InputTextField inputField) {

		String input = inputField.getText();
		if (inputField.isEmpty()) {
			input = "";
		}
		String expression = getText();

		if (isAllFieldsEmpty(inputField, expression)) {
			expression = InputTextField._0;
			put(TokenType.NUM, InputTextField._0);
		} else if (isLastNumber(expression) || isLastRightBraket(expression)) {
			input = "";
		} else if (isNecessityToDeleteLastToken(inputField, expression)) {
			expression = expression.substring(0, expression.length() - 1);
			deleteLastToken();
		} else if (isNecessityToWrite0(inputField, expression)) {
			expression += InputTextField._0;
			put(TokenType.NUM, InputTextField._0);
		}

		put(TokenType.NUM, input);
		switch (operation) {
			case DIVISION:
				put(TokenType.Op2, operation);
			break;
			case MULTIPLICATION:
				put(TokenType.Op2, operation);
			break;
			case SUM:
				put(TokenType.Op1, operation);
			break;
			case DIFFERENCE:
				put(TokenType.Op1, operation);
			break;
		}
		updateText();
		inputField.cleanInputField();

	}

	public void put(final TokenType tokenType, final String value) {

		if (value != "") {
			System.out.println("put " + tokenType + " " + value);
			final TokenCell cell = new TokenCell(tokenType, value);
			tokens.add(cell);
		}
	}

	public void put(final TokenType tokenType, final Token value) {

		System.out.println("put " + tokenType + " " + value);
		final TokenCell cell = new TokenCell(tokenType, value);
		tokens.add(cell);
	}

	public void reverse(final InputTextField inputField) {

		if (!inputField.isEmpty()) {
			final String input = inputField.getText();
			final String expression = getText();
			if (isLastLeftBraket(expression) || isLastOperation3(expression)
					|| isLastOperation4(expression) || expression.length() == 0) {

				put(TokenType.L_BRACKET, Token.L_BRAKET);// ExpressionTextField.L_BRACKET);
				put(TokenType.NUM, "1");
				put(TokenType.Op2, Token.DIVISION);
				put(TokenType.NUM, input);
				put(TokenType.R_BRACKET, Token.R_BRAKET);// ExpressionTextField.R_BRACKET);

				updateText();
			}
			inputField.cleanInputField();
		}
	}

	@Override
	public void setText(final String inputText) {

		updateText();
	}

	public void updateText() {

		String text = "";
		if (tokens != null) {
			for (final TokenCell cell : tokens) {
				text += cell.getValue();
			}
		}
		super.setText(text);
	}

}
