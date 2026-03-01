package fr.iutfbleau.pif.visualizer;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageVisualizer extends JComponent {
    private BufferedImage image;
    private int offsetX = 0;
    private int offsetY = 0;

    public ImageVisualizer(BufferedImage img) {
        this.image = img;
        ControllerMouseClick ctrlClick = new ControllerMouseClick();
        ControllerMouseMotion ctrlMouv = new ControllerMouseMotion(this, ctrlClick);

        this.addMouseListener(ctrlClick);
        this.addMouseMotionListener(ctrlMouv);
    }

    public void move(int dx, int dy) {
        this.offsetX += dx;
        this.offsetY += dy;
        this.repaint();
    }

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