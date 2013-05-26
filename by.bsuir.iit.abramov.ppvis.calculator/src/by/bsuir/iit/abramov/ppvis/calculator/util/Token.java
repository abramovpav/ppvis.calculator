package by.bsuir.iit.abramov.ppvis.calculator.util;

public enum Token {
	ONE("1"), TWO("2"), THREE("3"), DIVISION("/"), L_BRAKET("("), FOUR("4"), FOUTH("5"), SIX(
			"6"), MULTIPLICATION("*"), R_BRAKET(")"), SEVEN("7"), EIGHT("8"), NINE("9"), SUM(
			"+"), LOG("log"), ZERO("0"), POINT("."), SIGN("+/-"), DIFFERENCE("-"), LN(
			"ln"), SQRT("sqrt"), BACKSPACE("<-"), REVERSE("1/x"), FACTORIAL("!"), EQUAL(
			"="), CE("CE"), PERCENT("%");

	private final String	string;

	Token(final String string) {

		this.string = string;
	}

	public final String getString() {

		return string;
	}
}
