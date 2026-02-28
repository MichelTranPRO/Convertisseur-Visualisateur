package fr.iutfbleau.pif.converter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ConverterFrame extends JFrame {
	
	private BufferedImage bufferedImage;
	private FrequencyTable frequencyTable;

	private CardLayout cardLayout;
	private JPanel cardsContainer;
	private JButton btnRed;
	private JButton btnGreen;
	private JButton btnBlue;

	public ConverterFrame(BufferedImage bufferedImage, FrequencyTable frequencyTable, CodeTable red, CodeTable green, CodeTable blue) {
		super("Convertisseur pif");
		
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.bufferedImage = bufferedImage;
		this.frequencyTable = frequencyTable;

		ImagePanel imgPanel = new ImagePanel(this.bufferedImage);
		this.add(imgPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(borderLayout);
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

			Code canonique = codeTable.getHashCanonical().get(i);
			if(canonique != null) {
				gridPanel.add(new JLabel(canonique.toString()));
			} else {
				gridPanel.add(new JLabel(""));
			}
		}

		return new JScrollPane(gridPanel);
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