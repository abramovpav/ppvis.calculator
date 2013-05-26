package by.bsuir.iit.abramov.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.calculator.model.Model;

public class PrevButtonListener implements ActionListener {

	private final Model	model;

	public PrevButtonListener(final Model model) {

		this.model = model;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		model.stepBack();
	}

}
