package fr.iutfbleau.pif.converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe <code>ConverterController</code> agit comme le contrôleur dans l'architecture MVC.
 * Elle écoute les interactions de l'utilisateur sur les boutons de la vue pour changer d'onglet.
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class ConverterController implements ActionListener {
	
	/** Attribut qui fait référence à la fenêtre principale (la vue) pour pouvoir interagir avec ses composants. */
	private ConverterFrame frame;

	/**
	 * Constructeur de la classe <code>ConverterController</code>.
	 * Initialise le contrôleur et met en place des boutons de la vue.
	 * @param frame La fenêtre principale de l'application.
	 */
	public ConverterController(ConverterFrame frame) {
		this.frame = frame;

		this.frame.getBtnRed().addActionListener(this);
		this.frame.getBtnGreen().addActionListener(this);
		this.frame.getBtnBlue().addActionListener(this);
	}

	/**
	 * Gère les actions déclenchées par les clics sur les boutons de navigation (Rouge, Vert, Bleu).
	 * Modifie la carte affichée dans le CardLayout en conséquence.
	 * @param e L'événement d'action capturé.
	 */
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