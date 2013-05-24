package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.view.Desktop;

public class Operation3ButtonListener implements ActionListener {

	private final String	operation;
	private final Desktop	desktop;

	public Operation3ButtonListener(final String operation, final Desktop desktop) {

		this.operation = operation;
		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		desktop.operation3(operation);
	}

}
