package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.view.Desktop;

public class ReverseButtonListener implements ActionListener {

	private final Desktop	desktop;

	public ReverseButtonListener(final Desktop desktop) {

		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		desktop.reverse();

	}

}
