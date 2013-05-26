package by.bsuir.iit.abramov.ppvis.calculator.util;

public enum MainButton {
	ONE(Token.ONE, true), TWO(Token.TWO, true), THREE(Token.THREE, true), DIVISION(
			Token.DIVISION, true), BACKSPACE(Token.BACKSPACE, true), FOUR(Token.FOUR,
			true), FOUTH(Token.FOUTH, true), SIX(Token.SIX, true), MULTIPLICATION(
			Token.MULTIPLICATION, true), CE(Token.CE, true), SEVEN(Token.SEVEN, true), EIGHT(
			Token.EIGHT, true), NINE(Token.NINE, true), SUM(Token.SUM, true), L_BRAKET(
			Token.L_BRAKET, true), ZERO(Token.ZERO, true), POINT(Token.POINT, true), SIGN(
			Token.SIGN, true), DIFFERENCE(Token.DIFFERENCE, true), R_BRAKET(
			Token.R_BRAKET, true), SQRT(Token.SQRT, true), /*
															 * PERCENT(Token.PERCENT
															 * , true),
															 */REVERSE(Token.REVERSE,
			true), LOG(Token.LOG, false), LN(Token.LN, false), EQUAL(Token.EQUAL, true), FACTORIAL(
			Token.FACTORIAL, false);

	private Token	token;
	private boolean	main	= true;

	MainButton(final Token token, final boolean main) {

		this.token = token;
		this.main = main;
	}

	public String getString() {

		return token.getString();
	}

	public final Token getToken() {

		return token;
	}

	public boolean isMain() {

		return main;
	}

}
