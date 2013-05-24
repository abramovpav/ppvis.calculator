package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.view.Desktop;

public class NumberButtonListener implements ActionListener {

	private final int		num;
	private final Desktop	desktop;

	public NumberButtonListener(final int num, final Desktop desktop) {

		this.num = num;
		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		desktop.addNumToExpression(Integer.toString(num));
	}
}
