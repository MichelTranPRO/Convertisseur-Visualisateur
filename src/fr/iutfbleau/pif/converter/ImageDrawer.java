package fr.iutfbleau.pif.converter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.JComponent;

public class ImageDrawer extends JComponent {
    
    private BufferedImage image;
    private int offsetX, offsetY;

    public ImageDrawer(BufferedImage image, int offsetX, int offsetY) {
        this.image = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();

        if (this.image != null) {
            secondPinceau.drawImage(this.image, this.offsetX, this.offsetY, this);
        }

        secondPinceau.dispose();
    }

    public void setOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (this.image != null) {
            return new Dimension(this.image.getWidth() + this.offsetX, this.image.getHeight() + this.offsetY);
        } else {
            return super.getPreferredSize();
        }
    }
}