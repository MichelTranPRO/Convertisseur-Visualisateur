package fr.iutfbleau.pif.visualizer;

import java.awt.event.*;

/**
 * La classe <code>ControllerMouseMotion</code> gère les événements de mouvement de la souris pour permettre le déplacement de l'image dans le composant <code>ImageVisualizer</code>.
 * @version 1.1
 * @author Emmanuel Srivastava-Tiamzon, Rayan Bisson et Michel Tran
 */
public class ControllerMouseMotion implements MouseMotionListener {
    /**
     * Les références au composant <code>ImageVisualizer</code> pour mettre à jour la position de l'image.
     */
    private ImageVisualizer visualizer;

    /**
     * La référence au contrôleur de clic de souris <code>ControllerMouseClick</code> pour accéder aux coordonnées de départ de la souris lors du glissement.
     */
    private ControllerMouseClick click;

    /**
     * Constructeur de la classe <code>ControllerMouseMotion</code>.
     * @param v La référence au composant <code>ImageVisualizer</code> pour mettre à jour la position de l'image.
     * @param c La référence au contrôleur de clic de souris <code>ControllerMouseClick</code> pour accéder aux coordonnées de départ de la souris lors du glissement.
     */
    public ControllerMouseMotion(ImageVisualizer v, ControllerMouseClick c) {
        this.visualizer = v;
        this.click = c;
    }

    /**
     * Méthode appelée lorsque la souris bouge pendant qu'un bouton reste appuyé.
     * Elle met à jour la position de l'image dans le composant <code>ImageVisualizer</code>.
     * @param e L'événement de souris qui contient les coordonnées actuelles de la souris
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - click.getStartX();
        int deltaY = e.getY() - click.getStartY();
        visualizer.moveImg(deltaX, deltaY);
        click.setStartX(e.getX());
        click.setStartY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e){
    }
}
