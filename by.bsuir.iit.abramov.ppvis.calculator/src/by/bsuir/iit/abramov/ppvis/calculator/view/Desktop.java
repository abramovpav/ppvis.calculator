package by.bsuir.iit.abramov.ppvis.calculator.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.bsuir.iit.abramov.ppvis.calculator.controller.ButtonListener;
import by.bsuir.iit.abramov.ppvis.calculator.model.Model;
import by.bsuir.iit.abramov.ppvis.calculator.model.RPN;
import by.bsuir.iit.abramov.ppvis.calculator.util.ExpressionTextField;
import by.bsuir.iit.abramov.ppvis.calculator.util.InputTextField;
import by.bsuir.iit.abramov.ppvis.calculator.util.MainButton;
import by.bsuir.iit.abramov.ppvis.calculator.util.Token;

public class Desktop extends JPanel {
	private final InputTextField		inputField;
	private final ExpressionTextField	expressionField;
	private static final int			BUTTON_HEIGHT			= 40;
	private static final int			BUTTON_WIDTH			= 100;
	private static final int			BUTTONS_COLUMNS_COUNT	= 5;
	private final Map<Token, JButton>	buttons;
	private final RPN					rpn;
	private final Model					model;
	private JPanel						buttonsPanel;
	private boolean						mode					= true;

	public Desktop(final Model model) {

		this.model = model;
		rpn = model.getRPN();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		buttons = new HashMap<Token, JButton>();
		expressionField = new ExpressionTextField("");
		final JScrollPane scroll = new JScrollPane(expressionField);
		add(scroll);
		inputField = new InputTextField(InputTextField._0);
		add(inputField);
		inputField.setColumns(10);

		buttonsPanel = new JPanel();
		add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		createButtons(buttonsPanel, mode);
	}

	public void addLeftBracket() {

		expressionField.addLeftBracket();
	}

	public void addNumToInput(final String str) {

		inputField.addNumToInput(str);
	}

	public void addPoint(final String point) {

		inputField.addPoint(point);
	}

	public void addRightBracket() {

		expressionField.addRightBracket(inputField);
	}

	public void backspace() {

		final String expression = expressionField.getText();
		if (expression.length() == 0) {
			return;
		}
		expressionField.deleteToken();
	}

	public void CE() {

		expressionField.CE(inputField);
	}

	private void createButton(final JPanel panel, final MainButton eButton) {

		final JButton button = new JButton(eButton.getString());
		buttons.put(eButton.getToken(), button);
		button.setSize(Desktop.BUTTON_WIDTH, Desktop.BUTTON_HEIGHT);
		button.addActionListener(new ButtonListener(eButton.getToken(), this));
		panel.add(button);
	}

	public void createButtons(final JPanel buttonsPanel, final boolean mode) {

		JPanel rowButtonsPanel = new JPanel();
		int j = 0;
		buttonsPanel.add(rowButtonsPanel);
		for (final MainButton eButton : MainButton.values()) {
			if ((mode && eButton.isMain()) || !mode) {
				createButton(rowButtonsPanel, eButton);
				j++;
				if (j == Desktop.BUTTONS_COLUMNS_COUNT) {
					rowButtonsPanel = new JPanel();
					buttonsPanel.add(rowButtonsPanel);
					j = 0;
				}
			}
		}
	}

	public void equal() {

		expressionField.equal(inputField);
		model.createRPNStringAndTree(expressionField, inputField);

	}

	public void expand() {

		mode = !mode;
		System.out.println("pol");
		remove(buttonsPanel);
		buttonsPanel = new JPanel();
		add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		createButtons(buttonsPanel, mode);
		revalidate();

		updateUI();
	}

	public void operation3(final Token operation) {

		expressionField.operation3(operation, inputField);
	}

	public void operation4(final Token operation) {

		expressionField.operation4(operation, inputField);

	}

	public void reverse() {

		expressionField.reverse(inputField);
	}

	public void sign() {

		inputField.changeSign();
	}

}
