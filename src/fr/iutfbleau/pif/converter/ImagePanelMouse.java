package fr.iutfbleau.pif.converter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImagePanelMouse extends MouseAdapter{

    private ImageDrawer imageDrawer;
    private int initialOffsetX, initialOffsetY;
    private int startX, startY;

    public ImagePanelMouse(ImageDrawer imageDrawer) {
        this.imageDrawer = imageDrawer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //On implémente la position de la souris sur l'écran lorsque l'on clique
        this.startX = e.getXOnScreen();
        this.startY = e.getYOnScreen();

        //On implémente le décalage de l'image lorsque l'on clique
        this.initialOffsetX = this.imageDrawer.getOffsetX();
        this.initialOffsetY = this.imageDrawer.getOffsetY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //On calcule la différence entre la position de clique à l'instant et celle de départ.
        int x = e.getXOnScreen() - this.startX;
        int y = e.getYOnScreen() - this.startY;

        this.imageDrawer.setOffset(this.initialOffsetX + x, this.initialOffsetY + y);
    }
}
