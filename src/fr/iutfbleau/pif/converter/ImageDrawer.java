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
            //Test : secondPinceau.drawImage(this.image, this.offsetX, this.offsetY, this);
            int drawX = this.offsetX;
            int drawY = this.offsetY;

            if(this.getWidth() > this.image.getWidth) {
                drawX = (this.getWidth() - this.image.getWidth()) / 2;
            }

            if(this.getHeight() > this.image.getHeight) {
                drawX = (this.getHeight() - this.image.getHeight()) / 2;
            }

            secondPinceau.drawImage(this.image, drawX, drawY, this);
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

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }
}