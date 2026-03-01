package fr.iutfbleau.pif.converter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.JComponent;

/**
 * La classe <code>ImageDrawer</code> est un composant graphique chargé de dessiner l'image.
 * Elle affiche l'image centrée et la réduit proportionnellement si elle dépasse du conteneur.
 *
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class ImageDrawer extends JComponent {
  /**
   * Les dimensions du panneau et de l'image, ainsi que les dimensions de dessin calculées pour centrer et redimensionner l'image si nécessaire. Les ratios de redimensionnement sont également calculés pour assurer que l'image est affichée correctement sans distorsion.
   */
  int panelWidth;

  /**
   * La hauteur du panneau.
   */
  int panelHeight;

  /**
   * La largeur de l'image source.
   */
  int imgWidth;

  /** La hauteur de l'image source. */
  int imgHeight;

  /**
   * La largeur et la hauteur de dessin calculées pour centrer et redimensionner l'image si nécessaire. 
   */
  int drawWidth;

  /**
   * La hauteur de dessin calculée pour centrer et redimensionner l'image si nécessaire. 
   */
  int drawHeight;

  /**
   * Les ratios de redimensionnement pour la largeur et la hauteur. 
   */
  double widthRatio;

  /**
   * Le ratio de redimensionnement pour la hauteur. 
   */
  double heightRatio;

  /**
   * Le ratio de redimensionnement final utilisé pour dessiner l'image. 
  */ 
  double ratio;
  
  /**
   * Les coordonnées de dessin pour centrer l'image dans le panneau. 
   */
  int drawX;

  /**
   * La coordonnée de dessin verticale pour centrer l'image dans le panneau. 
   */
  int drawY;

  /** L'image source à dessiner. */
  private BufferedImage image;

  /**
   * Constructeur de la classe <code>ImageDrawer</code>.
   * @param image L'image à dessiner.
   */
  public ImageDrawer(BufferedImage image) {
    this.image = image;
  }

  @Override
  public void paintComponent(Graphics pinceau) {
    Graphics secondPinceau = pinceau.create();

    if (this.image != null) {
      panelWidth = this.getWidth();
      panelHeight = this.getHeight();
      imgWidth = this.image.getWidth();
      imgHeight = this.image.getHeight();

      drawWidth = imgWidth;
      drawHeight = imgHeight;

      if (imgWidth > panelWidth || imgHeight > panelHeight) {
        //ici on calcule le ratio pour pas se rater quand on rétrécit l'image
        widthRatio = (double) panelWidth / imgWidth;
        heightRatio = (double) panelHeight / imgHeight;

        // On prend le ratio le plus petit pour s'assurer que l'image rentre entièrement
        ratio = Math.min(widthRatio, heightRatio);

        // On applique le ratio aux dimensions de dessin
        drawWidth = (int) (imgWidth * ratio);
        drawHeight = (int) (imgHeight * ratio);
      }

      //on met l'image au milieu
      drawX = (panelWidth - drawWidth) / 2;
      drawY = (panelHeight - drawHeight) / 2;

      secondPinceau.drawImage(this.image, drawX, drawY, drawWidth, drawHeight, this);
    }

    secondPinceau.dispose();
  }

  @Override
  public Dimension getPreferredSize() {
    if (this.image != null) {
      return new Dimension(this.image.getWidth(), this.image.getHeight());
    } else {
      return super.getPreferredSize();
    }
  }
}
