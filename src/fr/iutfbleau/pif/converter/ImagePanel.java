package fr.iutfbleau.pif.converter;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

/**
 * La classe <code>ImagePanel</code> représente un panneau d'affichage d'image dans l'interface graphique de l'application.
 * @version 1.2
 */
public class ImagePanel extends JPanel {
	
	/**
	 * Constructeur de la classe <code>ImagePanel</code>.
	 * @param image L'image à afficher et manipuler.
	 */
	public ImagePanel(BufferedImage image) {
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		ImageDrawer imageDrawer = new ImageDrawer(image);
		this.add(imageDrawer, BorderLayout.CENTER);
	}

    
}
