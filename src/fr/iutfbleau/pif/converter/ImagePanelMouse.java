package fr.iutfbleau.pif.converter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImagePanelMouse extends MouseAdapter{

    private ImageDrawer imageDrawer;
    private int initialOffsetX, initialOffsetY;

    public ImagePanelMouse(ImageDrawer imageDrawer) {
        this.imageDrawer = imageDrawer;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
