package by.bsuir.iit.abramov.ppvis.calculator.util;

import java.awt.Font;

import javax.swing.JTextArea;

public class InputTextField extends JTextArea {
	public static final char	POINT	= '.';
	public static final String	_0		= "0";
	protected boolean			empty;
	protected boolean			sign;

	public InputTextField(final String text) {

		super(text);
		setFont(new Font("MV Boli", Font.PLAIN, 20));
		empty = true;
		sign = true;
	}

	public void addNumToInput(final String str) {

		String input = getText2();
		if (isEmpty()) {
			input = "";
		}

		setText(input + str);
		setEmpty(false);
	}

	public void addPoint(final String point) {

		final String input = getText();
		setText(input + point);
		setEmpty(false);
	}

	public void changeSign() {

		sign = !sign;
		setText("-" + getText());
	}

	public void cleanInputField() {

		setText(InputTextField._0);
		setEmpty(true);
	}

	public boolean getSign() {

		return sign;
	}

	@Override
	public String getText() {

		String text = super.getText();
		final int index = text.indexOf(InputTextField.POINT);
		if (index != -1) {
			if (index == text.length() - 1) {
				text = text.substring(0, text.length() - 1);
			}
		}
		return text;
	}

	public String getText2() {

		return super.getText();
	}

	public boolean isEmpty() {

		return empty;
	}

	public void setEmpty(final boolean bool) {

		empty = bool;
	}

	public void setSign(final boolean sign) {

		this.sign = sign;
	}

}
