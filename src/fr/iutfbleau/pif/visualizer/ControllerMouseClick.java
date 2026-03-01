package fr.iutfbleau.pif.visualizer;
import java.awt.event.*;

/**
 * La classe <code>ControllerMouseClick</code> permet d'enregistrer la position de la souris lorsqu'elle est pressée.
 * Cette position est utilisée dans la classe <code>ControllerMouseMotion</code> pour calculer le déplacement de l'image lors du glissement de la souris.
 * @version 1.1
 * @author Emmanuel Srivastava-Tiamzon, Rayan Bisson et Michel Tran
 */
public class ControllerMouseClick implements MouseListener {
    /**
     * Les coordonnées de départ de la souris lorsqu'elle est pressée.
     */
    private int startX;

    /**
     * Les coordonnées de départ de la souris lorsqu'elle est pressée.
     */
    private int startY;

    /**
     * Constructeur de la classe <code>ControllerMouseClick</code>.
     */
    public ControllerMouseClick() {
    }

    /**
     * Méthode appelée lorsque la souris est pressée.
     * Elle enregistre les coordonnées de la souris. 
     * Ces coordonnées sont utilisées pour calculer le déplacement de l'image lors du glissement de la souris.
     * @param e L'événement de souris qui contient les coordonnées de la souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.startX = e.getX();
        this.startY = e.getY();
    }


    @Override 
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Getter pour les coordonnées de la souris. 
     * @return Les coordonnées de la souris lorsqu'elle est pressée.
     */
    public int getStartX(){
        return startX;
    }

    /**
     * Getter pour les coordonnées de la souris.
     * @return Les coordonnées de la souris lorsqu'elle est pressée.
     */
    public int getStartY(){
        return startY;
    }
    
    /**
     * Setter pour les coordonnées de la souris.
     * @param x Les nouvelles coordonnées horizontales de la souris.
     */
    public void setStartX(int x){
        this.startX = x;
    }

    /**
     * Setter pour les coordonnées de la souris.
     * @param y Les nouvelles coordonnées verticales de la souris.
     */
    public void setStartY(int y){
        this.startY = y;
    }
}
