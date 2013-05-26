package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.util.Token;
import by.bsuir.iit.abramov.ppvis.calculator.view.Desktop;

public class ButtonListener implements ActionListener {

	private ActionListener	listener;

	public ButtonListener(final Token eButton, final Desktop desktop) {

		listener = null;
		switch (eButton) {
			case ONE:
				listener = new NumberButtonListener(1, desktop);
			break;
			case TWO:
				listener = new NumberButtonListener(2, desktop);
			break;
			case THREE:
				listener = new NumberButtonListener(3, desktop);
			break;
			case DIVISION:
				listener = new Operation4ButtonListener(Token.DIVISION, desktop);
			break;
			case L_BRAKET:
				listener = new LeftBracketButtonListener(desktop);
			break;
			case FOUR:
				listener = new NumberButtonListener(4, desktop);
			break;
			case FOUTH:
				listener = new NumberButtonListener(5, desktop);
			break;
			case SIX:
				listener = new NumberButtonListener(6, desktop);
			break;
			case MULTIPLICATION:
				listener = new Operation4ButtonListener(Token.MULTIPLICATION, desktop);
			break;
			case R_BRAKET:
				listener = new RightBracketButtonListener(desktop);
			break;
			case SEVEN:
				listener = new NumberButtonListener(7, desktop);
			break;
			case EIGHT:
				listener = new NumberButtonListener(8, desktop);
			break;
			case NINE:
				listener = new NumberButtonListener(9, desktop);
			break;
			case SUM:
				listener = new Operation4ButtonListener(Token.SUM, desktop);
			break;
			case LOG:
				listener = new Operation3ButtonListener(Token.LOG, desktop);
			break;
			case ZERO:
				listener = new NumberButtonListener(0, desktop);
			break;
			case POINT:
				listener = new PointButtonListener(".", desktop);
			break;
			case SIGN:
				listener = new SignButtonListener("sign", desktop);
			break;
			case DIFFERENCE:
				listener = new Operation4ButtonListener(Token.DIFFERENCE, desktop);
			break;
			case LN:
				listener = new Operation3ButtonListener(Token.LN, desktop);
			break;
			case SQRT:
				listener = new Operation3ButtonListener(Token.SQRT, desktop);
			break;
			case BACKSPACE:
				listener = new BackspaceButtonListener(desktop);
			break;
			case REVERSE:
				listener = new ReverseButtonListener(desktop);
			break;
			case FACTORIAL:
				listener = new Operation3ButtonListener(Token.FACTORIAL, desktop);
			break;
			case EQUAL:
				listener = new EqualButtonListener(desktop);
			break;
			case CE:
				listener = new CEButtonListener(desktop);
			break;
			default:

		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		if (listener != null) {
			listener.actionPerformed(e);
		}

	}

}
