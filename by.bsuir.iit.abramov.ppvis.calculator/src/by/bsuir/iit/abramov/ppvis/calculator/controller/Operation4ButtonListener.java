package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.util.Token;
import by.bsuir.iit.abramov.ppvis.calculator.view.Desktop;

public class Operation4ButtonListener implements ActionListener {

	private final Token		operation;
	private final Desktop	desktop;

	public Operation4ButtonListener(final Token operation, final Desktop desktop) {

		this.operation = operation;
		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		desktop.operation4(operation);
	}

}
