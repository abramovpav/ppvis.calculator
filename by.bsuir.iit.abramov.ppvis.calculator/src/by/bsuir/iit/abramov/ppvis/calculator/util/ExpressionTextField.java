package by.bsuir.iit.abramov.ppvis.calculator.util;

import javax.swing.JTextField;

public class ExpressionTextField extends JTextField {
	private boolean	empty;
	private boolean	sign;

	public ExpressionTextField(final String text) {

		super(text);
		empty = true;
		sign = true;
	}

	public void changeSign() {

		sign = !sign;
	}

	public int getLeftBracketCount() {

		int counter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == '(') {
				counter++;
			}
		}
		return counter;
	}

	public int getRightBracketCount() {

		int counter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == ')') {
				counter++;
			}
		}
		return counter;
	}

	public boolean getSign() {

		return sign;
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

	public boolean isEmpty() {

		return empty;
	}

	public boolean isRightAndLeftBracketCountEqual() {

		int leftCounter = 0, rightCounter = 0;
		for (final char c : getText().toCharArray()) {
			if (c == '(') {
				leftCounter++;
			}
			if (c == ')') {
				rightCounter++;
			}
		}
		return leftCounter == rightCounter;
	}

	public void setEmpty(final boolean bool) {

		empty = bool;
	}

	public void setSign(final boolean sign) {

		this.sign = sign;
	}

}
