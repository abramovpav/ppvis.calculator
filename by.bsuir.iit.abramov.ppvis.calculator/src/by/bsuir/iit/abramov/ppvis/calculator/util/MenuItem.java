package by.bsuir.iit.abramov.ppvis.calculator.util;

public enum MenuItem {
	EXPAND("Expand");

	private String	text;

	MenuItem(final String text) {

		this.text = text;
	}

	public String getText() {

		return text;
	}
}
