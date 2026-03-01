package fr.iutfbleau.pif.visualizer;

import java.awt.event.*;

public class ControllerMouseMotion implements MouseMotionListener {
    private ImageVisualizer visualizer;
    private ControllerMouseClick click;

    public ControllerMouseMotion(ImageVisualizer v, ControllerMouseClick c) {
        this.visualizer = v;
        this.click = c;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - click.getStartX();
        int deltaY = e.getY() - click.getStartY();
        visualizer.move(deltaX, deltaY);
    }

    @Override
    public void mouseMoved(MouseEvent e){
    }
}
