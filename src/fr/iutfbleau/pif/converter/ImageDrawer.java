package fr.iutfbleau.pif.converter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.JComponent;

/**
 * La classe <code>ImageDrawer</code> est un composant graphique chargé de dessiner l'image.
 * Elle permet d'afficher l'image centrée ou avec un décalage spécifique généré par la souris.
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class ImageDrawer extends JComponent {
    
    /** L'image source à dessiner. */
    private BufferedImage image;

    /** Le décalage horizontal et vertical pour l'affichage de l'image. */
    private int offsetX, offsetY;

    /**
     * Constructeur de la classe <code>ImageDrawer</code>.
     * @param image L'image à dessiner.
     * @param offsetX Le décalage horizontal initial.
     * @param offsetY Le décalage vertical initial.
     */
    public ImageDrawer(BufferedImage image, int offsetX, int offsetY) {
        this.image = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Redéfinition de la méthode de dessin pour afficher l'image.
     * Gère le centrage automatique si le conteneur est plus grand que l'image.
     * @param pinceau L'objet Graphics utilisé pour dessiner.
     */
    @Override
    public void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();

        if (this.image != null) {
            //Test : secondPinceau.drawImage(this.image, this.offsetX, this.offsetY, this);
            int drawX = this.offsetX;
            int drawY = this.offsetY;

            if(this.getWidth() > this.image.getWidth()) {
                drawX = (this.getWidth() - this.image.getWidth()) / 2;
            }

            if(this.getHeight() > this.image.getHeight()) {
                drawY = (this.getHeight() - this.image.getHeight()) / 2;
            }

            secondPinceau.drawImage(this.image, drawX, drawY, this);
        }

        secondPinceau.dispose();
    }

    /**
     * Setter pour les offset, il modifie les décalages de l'image et force le redessin du composant.
     * @param offsetX Le nouveau décalage horizontal.
     * @param offsetY Le nouveau décalage vertical.
     */
    public void setOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        repaint();
    }

    /**
     * Calcule et retourne la taille préférée du composant en fonction de l'image et des décalages.
     * @return La dimension idéale pour afficher l'image complète.
     */
    @Override
    public Dimension getPreferredSize() {
        if (this.image != null) {
            return new Dimension(this.image.getWidth() + this.offsetX, this.image.getHeight() + this.offsetY);
        } else {
            return super.getPreferredSize();
        }
    }

    /**
     * Getter pour récupérer le décalage horizontal actuel.
     * @return Le décalage sur l'axe X.
     */
    public int getOffsetX() {
        return this.offsetX;
    }

    /**
     * Getter pour récupérer le décalage vertical actuel.
     * @return Le décalage sur l'axe Y.
     */
    public int getOffsetY() {
        return this.offsetY;
    }
}