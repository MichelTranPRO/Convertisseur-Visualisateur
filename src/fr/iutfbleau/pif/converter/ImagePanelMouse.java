package fr.iutfbleau.pif.converter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe <code>ImagePanelMouse</code> gère les événements de la souris pour permettre
 * le glisser-déposer (drag & drop) de l'image affichée dans la vue.
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class ImagePanelMouse extends MouseAdapter{

    /** Le composant responsable du dessin de l'image. */
    private ImageDrawer imageDrawer;

    /** Le décalage horizontal et vertical de l'image au moment du clic. */
    private int initialOffsetX, initialOffsetY;

    /** La coordonnée X et Y de la souris sur l'écran au moment du clic. */
    private int startX, startY;

    public ImagePanelMouse(ImageDrawer imageDrawer) {
        this.imageDrawer = imageDrawer;
    }

    /**
     * Événement déclenché lors du clic de la souris. 
     * Mémorise la position de départ de la souris et les décalages initiaux de l'image.
     * @param e L'événement généré par la souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //On implémente la position de la souris sur l'écran lorsque l'on clique
        this.startX = e.getXOnScreen();
        this.startY = e.getYOnScreen();

        //On implémente le décalage de l'image lorsque l'on clique
        this.initialOffsetX = this.imageDrawer.getOffsetX();
        this.initialOffsetY = this.imageDrawer.getOffsetY();
    }

    /**
     * Événement déclenché lors du glissement de la souris. 
     * Calcule le déplacement et met à jour la position de l'image en temps réel.
     * @param e L'événement généré par la souris.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //On calcule la différence entre la position de clique à l'instant et celle de départ.
        int x = e.getXOnScreen() - this.startX;
        int y = e.getYOnScreen() - this.startY;

        this.imageDrawer.setOffset(this.initialOffsetX + x, this.initialOffsetY + y);
    }
}
