package by.bsuir.iit.abramov.ppvis.calculator.util;

public enum Buttons {
	ONE(" 1  "), TWO(" 2  "), THREE(" 3  "), DIVISION(" /  "), L_BRAKET(" (  "), FOUR(
			" 4  "), FOUTH(" 5  "), SIX(" 6  "), MULTIPLICATION(" *  "), R_BRAKET(" )  "), SEVEN(
			" 7  "), EIGHT(" 8  "), NINE(" 9  "), SUM(" +  "), LOG("log "), ZERO(" 0  "), POINT(
			" .  "), SIGN("+/- "), DIFFERENCE(" -  "), LN("ln  "), SQRT("sqrt"), BACKSPACE(
			"<-"), REVERSE("1/x "), FACTORIAL("n! "), EQUAL(" =  ");

	private final String	caption;

	Buttons(final String caption) {

		this.caption = caption;
	}

	public final String getCaption() {

		return caption;
	}
}
