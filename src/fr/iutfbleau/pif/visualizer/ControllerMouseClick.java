package fr.iutfbleau.pif.visualizer;
import java.awt.event.*;


public class ControllerMouseClick implements MouseListener {
    private int startX;
    private int startY;

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

    public int getStartX(){
        return startX;
    }
    public int getStartY(){
        return startY;
    }
}
