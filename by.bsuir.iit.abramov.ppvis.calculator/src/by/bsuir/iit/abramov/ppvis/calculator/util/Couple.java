package by.bsuir.iit.abramov.ppvis.calculator.util;

import java.util.ArrayList;
import java.util.List;

public class Couple {
	public List<TokenCell>	rpn;
	public String			showString;

	public Couple(final List<TokenCell> rpn, final String showString) {

		this.rpn = new ArrayList<TokenCell>();
		this.rpn.addAll(rpn);
		this.showString = showString;
	}
}
