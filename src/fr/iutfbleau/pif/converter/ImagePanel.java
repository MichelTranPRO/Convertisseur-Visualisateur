package fr.iutfbleau.pif.converter;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

/**
 * La classe <code>ImagePanel</code> représente un panneau d'affichage d'image dans l'interface graphique de l'application.
 *
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon

 */
public class ImagePanel extends JPanel {

  /**
   * Le layout utilisé pour organiser les composants dans le panneau, ainsi que le composant personnalisé <code>ImageDrawer</code> qui est responsable de l'affichage de l'image.
   */
  BorderLayout borderLayout;

  /**
   * Le composant personnalisé <code>ImageDrawer</code> qui est responsable de l'affichage de l'image. Il est ajouté au centre du panneau pour occuper tout l'espace disponible.
   */
  ImageDrawer imageDrawer;

  /**
   * Constructeur de la classe <code>ImagePanel</code>.
   * @param image L'image à afficher et manipuler.
   */
  public ImagePanel(BufferedImage image) {
    borderLayout = new BorderLayout();
    this.setLayout(borderLayout);
    imageDrawer = new ImageDrawer(image);
    this.add(imageDrawer, BorderLayout.CENTER);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(400, 400); // Ou une taille par défaut
  }
}
