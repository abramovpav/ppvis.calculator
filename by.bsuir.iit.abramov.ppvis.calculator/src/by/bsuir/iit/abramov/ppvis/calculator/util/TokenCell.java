package by.bsuir.iit.abramov.ppvis.calculator.util;

public class TokenCell {
	private final TokenType	tokenType;
	private String			value;
	private final Token		tokenValue;

	public TokenCell(final TokenType tokenType, final String value) {

		this.tokenType = tokenType;
		this.value = value;
		tokenValue = null;
	}

	public TokenCell(final TokenType tokenType, final Token value) {

		this.tokenType = tokenType;
		tokenValue = value;
		this.value = null;
	}

	public TokenType getToken() {

		return tokenType;
	}

	public Token getTokenValue() {

		return tokenValue;
	}

	public String getValue() {

		return tokenValue != null ? tokenValue.getString() : value;
	}

	public void setValue(final String value) {

		this.value = value;
	}
}
