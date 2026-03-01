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
      int panelWidth = this.getWidth();
      int panelHeight = this.getHeight();
      int imgWidth = this.image.getWidth();
      int imgHeight = this.image.getHeight();

      int drawWidth = imgWidth;
      int drawHeight = imgHeight;

      if (imgWidth > panelWidth || imgHeight > panelHeight) {
        //ici on calcule le ratio pour pas se rater quand on rétrécit l'image
        double widthRatio = (double) panelWidth / imgWidth;
        double heightRatio = (double) panelHeight / imgHeight;

        // On prend le ratio le plus petit pour s'assurer que l'image rentre entièrement
        double ratio = Math.min(widthRatio, heightRatio);

        // On applique le ratio aux dimensions de dessin
        drawWidth = (int) (imgWidth * ratio);
        drawHeight = (int) (imgHeight * ratio);
      }

      //on met l'image au milieu
      int drawX = (panelWidth - drawWidth) / 2;
      int drawY = (panelHeight - drawHeight) / 2;

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
