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
   * Le layout utilisé pour organiser les composants dans la fenêtre.
   */
  private BorderLayout borderLayout;

  /**
   * Le panneau d'affichage de l'image, qui contient le composant personnalisé <code>ImageDrawer</code> chargé de dessiner l'image. 
   */
  private ImagePanel imgPanel;

  /**
   * Le panneau de droite qui contient les boutons de navigation et les grilles de données, 
   * ainsi que le panneau de navigation lui-même qui contient les boutons pour basculer entre les différentes grilles de couleurs. 
   */
  private JPanel rightPanel;

  /**
   * Le panneau de navigation qui contient les boutons pour basculer entre les différentes grilles de couleurs. 
   */
  private JPanel navPanel;

  /**
   * Le bouton pour écrire le fichier PIF, qui déclenche l'action d'écriture du fichier lorsque l'utilisateur clique dessus. 
   */
  private JButton writeButton;

  /**
   * Le maximum de largeur pour la fenêtre, utilisé pour limiter la taille de la fenêtre lorsque l'image est très grande. 
   */
  private int maxWidth; 

  /**
   * Le maximum de hauteur pour la fenêtre, utilisé pour limiter la taille de la fenêtre lorsque l'image est très grande. 
   */
  private int maxHeight;

  /**
   * La largeur de l'image source . 
   */
  private int imgWidth;

  /**
   * La hauteur de l'image source .
   */
  private int imgHeight;

  /**
   * La largeur idéale pour la fenêtre. 
   */
  private int idealWidth;

  /**
   * La hauteur idéale pour la fenêtre.
   */
  private int idealHeight;

  /**
   * Constructeur de la classe <code>ConverterFrame</code>.
   * Initialise et assemble tous les composants graphiques de la fenêtre de conversion.
   * @param bufferedImage L'image source à afficher.
   * @param frequencyTable La table des fréquences calculées pour l'image.
   * @param red La table des codes (initiaux et canoniques) pour le canal rouge.
   * @param green La table des codes (initiaux et canoniques) pour le canal vert.
   * @param blue La table des codes (initiaux et canoniques) pour le canal bleu.
   * @param filename le nom du fichier qui va ressortir
   */
  public ConverterFrame(BufferedImage bufferedImage, 
      FrequencyTable frequencyTable, 
      CodeTable red, 
      CodeTable green, 
      CodeTable blue,
      String filename) {
    super("Convertisseur pif");

    borderLayout = new BorderLayout();
    this.setLayout(borderLayout);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.bufferedImage = bufferedImage;
    this.frequencyTable = frequencyTable;

    imgPanel = new ImagePanel(this.bufferedImage);
    this.add(imgPanel, BorderLayout.CENTER);

    rightPanel = new JPanel(new BorderLayout());
    rightPanel.setPreferredSize(new Dimension(450,0));

    navPanel = new JPanel();
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

    writeButton = new JButton("Écrire le fichier PIF");
    writeButton.addActionListener(new WriteFileListener(
          filename, red, green, blue, this.bufferedImage
          ));
    rightPanel.add(writeButton, BorderLayout.SOUTH);
    this.add(rightPanel, BorderLayout.EAST);

    maxWidth = 1200; 
    maxHeight = 800;
    imgWidth = this.bufferedImage.getWidth();
    imgHeight = this.bufferedImage.getHeight();

    idealWidth = imgWidth + 450 + 20; //On ajoute 450 Pour le tableau à droite
    idealHeight = imgHeight + 40;

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
