package fr.iutfbleau.pif.visualizer;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * La classe <code>ImageVisualizer</code> est un composant personnalisé qui affiche une image et permet de la déplacer à l'aide de la souris.
 * @version 1.1
 * @author Emmanuel Srivastava-Tiamzon, Rayan Bisson et Michel Tran
 */
public class ImageVisualizer extends JComponent {

    /**
     * L'image à afficher, ainsi que les décalages horizontaux et verticaux pour le déplacement de l'image.
     */
    private BufferedImage image;

    /**
     * Les décalages horizontaux et verticaux pour le déplacement de l'image. Ils sont mis à jour lors du glissement de la souris et utilisés pour dessiner l'image à la bonne position.
     */
    private int offsetX = 0;

    /**
     * Le décalage vertical pour le déplacement de l'image. Il est mis à jour lors du glissement de la souris et utilisé pour dessiner l'image à la bonne position.
     */
    private int offsetY = 0;

    /**
     * Constructeur de la classe <code>ImageVisualizer</code> qui initialise l'image à afficher et configure les écouteurs de souris pour permettre le déplacement de l'image.
     * @param img L'image à afficher dans le composant.
     */
    public ImageVisualizer(BufferedImage img) {
        this.image = img;
        ControllerMouseClick ctrlClick = new ControllerMouseClick();
        ControllerMouseMotion ctrlMouv = new ControllerMouseMotion(this, ctrlClick);

        this.addMouseListener(ctrlClick);
        this.addMouseMotionListener(ctrlMouv);
    }

    /**
     * Méthode qui met à jour les décalages horizontaux et verticaux en fonction du déplacement de la souris.
     * @param dx Le déplacement horizontal de la souris depuis la dernière position enregistrée.
     * @param dy Le déplacement vertical de la souris depuis la dernière position enregistrée.
     */
    public void moveImg(int dx, int dy) {
        this.offsetX += dx;
        this.offsetY += dy;


        // horizontal
        if (image.getWidth() > getWidth()) {
            int minX = getWidth() - image.getWidth();
            if (this.offsetX > 0) {
                this.offsetX = 0;
            } else if (this.offsetX < minX) {
                this.offsetX = minX;
            }
        } else {
            this.offsetX = 0; 
        }

        // vertical
        if (image.getHeight() > getHeight()) {
            int minY = getHeight() - image.getHeight();
            if (this.offsetY > 0) {
                this.offsetY = 0;
            } else if (this.offsetY < minY) {
                this.offsetY = minY;
            }
        } else {
            this.offsetY = 0;
        }

        this.repaint();
    }

    /**
     * Méthode qui dessine l'image à la position calculée en fonction des décalages horizontaux et verticaux. Si l'image est plus petite que le composant, elle est centrée.
     */
    @Override
    protected void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();
        if (image != null) {
            int drawX = offsetX;
            int drawY = offsetY;

            if (image.getWidth() < getWidth()) {
                drawX = (getWidth() - image.getWidth()) / 2;
            }
            if (image.getHeight() < getHeight()) {
                drawY = (getHeight() - image.getHeight()) / 2;
            }
            secondPinceau.drawImage(image, drawX, drawY, this);
        }
    }
}