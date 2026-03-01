package fr.iutfbleau.pif.converter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La classe <code>ConverterFrame</code> représente la vue principale de l'application de conversion.
 * Elle gère l'affichage de l'image, des boutons de navigation et des grilles de données.
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class ConverterFrame extends JFrame {
	
	/** L'image source à afficher dans le panneau central. */
	private BufferedImage bufferedImage;
	/** La table des fréquences associée à l'image. */
	private FrequencyTable frequencyTable;

	/** Le gestionnaire de mise en page pour l'affichage alterné des grilles de couleurs. */
	private CardLayout cardLayout;

	/** Le conteneur principal des différentes grilles de couleurs. */
	private JPanel cardsContainer;

	/** Le bouton permettant d'afficher la table du canal rouge. */
	private JButton btnRed;

	/** Le bouton permettant d'afficher la table du canal vert. */
	private JButton btnGreen;

	/** Le bouton permettant d'afficher la table du canal bleu. */
	private JButton btnBlue;

	/**
	 * Constructeur de la classe <code>ConverterFrame</code>.
	 * Initialise et assemble tous les composants graphiques de la fenêtre de conversion.
	 * @param bufferedImage L'image source à afficher.
	 * @param frequencyTable La table des fréquences calculées pour l'image.
	 * @param red La table des codes (initiaux et canoniques) pour le canal rouge.
	 * @param green La table des codes (initiaux et canoniques) pour le canal vert.
	 * @param blue La table des codes (initiaux et canoniques) pour le canal bleu.
	 */
	public ConverterFrame(BufferedImage bufferedImage, FrequencyTable frequencyTable, CodeTable red, CodeTable green, CodeTable blue) {
		super("Convertisseur pif");
		
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.bufferedImage = bufferedImage;
		this.frequencyTable = frequencyTable;

		ImagePanel imgPanel = new ImagePanel(this.bufferedImage);
		this.add(imgPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(450,0));

		JPanel navPanel = new JPanel();
		this.btnRed = new JButton("Rouge");
		this.btnGreen = new JButton("Vert");
		this.btnBlue = new JButton("Bleu");
		navPanel.add(this.btnRed);
		navPanel.add(this.btnGreen);
		navPanel.add(this.btnBlue);

		this.cardLayout = new CardLayout();
		this.cardsContainer = new JPanel(this.cardLayout);

		this.cardsContainer.add(createColorGrid(0, this.frequencyTable, red), "ROUGE");
		this.cardsContainer.add(createColorGrid(1, this.frequencyTable, green), "VERT");
		this.cardsContainer.add(createColorGrid(2, this.frequencyTable, blue), "BLEU");

		rightPanel.add(navPanel, BorderLayout.NORTH);
		rightPanel.add(this.cardsContainer, BorderLayout.CENTER);

		this.add(rightPanel, BorderLayout.EAST);

		int maxWidth = 1200; 
		int maxHeight = 800;
		int imgWidth = this.bufferedImage.getWidth();
		int imgHeight = this.bufferedImage.getHeight();

		int idealWidth = imgWidth + 450 + 20; //On ajoute 450 Pour le tableau à droite
		int idealHeight = imgHeight + 40;

		this.setSize(Math.min(idealWidth, maxWidth), Math.min(idealHeight, maxHeight));
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	/**
	 * Méthode utilitaire privée pour créer une grille affichant les données d'un canal de couleur.
	 * @param colorIndex L'index de la couleur (0 = Rouge, 1 = Vert, 2 = Bleu).
	 * @param frequencyTable La table contenant les fréquences.
	 * @param codeTable La table contenant les codes initiaux et canoniques.
	 * @return Un panneau défilant contenant la grille de données formatée.
	 */
	private JScrollPane createColorGrid(int colorIndex, FrequencyTable frequencyTable, CodeTable codeTable) {
		JPanel gridPanel = new JPanel(new GridLayout(0,4,5,2));

		gridPanel.add(new JLabel("Valeur"));
		gridPanel.add(new JLabel("Fréquence"));
		gridPanel.add(new JLabel("Code Initial"));
		gridPanel.add(new JLabel("Canonique"));

		for(int i = 0; i < 256; i++) {
			gridPanel.add(new JLabel(String.valueOf(i)));

			int freq = 0;
			if(colorIndex == 0) {
				freq = frequencyTable.getRed(i);
			} else if(colorIndex == 1) {
				freq = frequencyTable.getGreen(i);
			} else if(colorIndex == 2) {
				freq = frequencyTable.getBlue(i);
			}

			gridPanel.add(new JLabel(String.valueOf(freq)));

			Code initial = codeTable.getHashMap().get(i);
			if(initial != null) {
				gridPanel.add(new JLabel(initial.toString()));
			} else {
				gridPanel.add(new JLabel(""));
			}

      

			Code canonique = codeTable.getCanonical().get(i);
			if(canonique != null) {
				gridPanel.add(new JLabel(canonique.toString()));
			} else {
				gridPanel.add(new JLabel(""));
			}
		}
		JScrollPane temp = new JScrollPane(gridPanel);
		temp.getVerticalScrollBar().setUnitIncrement(500);
		return temp;
	}

	/**
	 * Cette méthode est un getter pour le bouton rouge.
	 * @return Le JButton rouge.
	 */
	public JButton getBtnRed() {
		return this.btnRed;
	}

	/**
	 * Cette méthode est un getter pour le bouton vert.
	 * @return Le JButton vert.
	 */
	public JButton getBtnGreen() {
		return this.btnGreen;
	}

	/**
	 * Cette méthode est un getter pour le bouton bleu.
	 * @return Le JButton bleu.
	 */
	public JButton getBtnBlue() {
		return this.btnBlue;
	}

	/**
	 * Cette méthode est un getter pour le cardLayout mise en place dans cette classe.
	 * @return le cardLayout mise en place dans cette classe.
	 */
	public CardLayout getCardLayout() {
		return this.cardLayout;
	}

	/**
	 * Cette méthode est un getter pour le JPanel de cardsContainer.
	 * @return Le JPanel qui représente l'attribut cardsContainer.
	 */
	public JPanel getCardsContainer() {
		return this.cardsContainer;
	}

}
