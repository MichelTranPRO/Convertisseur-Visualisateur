package fr.iutfbleau.pif.converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterController implements ActionListener {
	
	private ConverterFrame frame;

	public ConverterController(ConverterFrame frame) {
		this.frame = frame;

		this.frame.getBtnRed().addActionListener(this);
		this.frame.getBtnGreen().addActionListener(this);
		this.frame.getBtnBlue().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.frame.getBtnRed()) {
			this.frame.getCardLayout().show(this.frame.getCardsContainer(), "ROUGE");
		} else if(e.getSource() == this.frame.getBtnGreen()) {
			this.frame.getCardLayout().show(this.frame.getCardsContainer(), "VERT");
		} else if(e.getSource() == this.frame.getBtnBlue()) {
			this.frame.getCardLayout().show(this.frame.getCardsContainer(), "BLEU");
		} 
	}
}