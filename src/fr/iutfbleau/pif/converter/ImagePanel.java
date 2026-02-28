package fr.iutfbleau.pif.converter;

import javax.swing.JPanel;

/**
 * La classe <code>ImagePanel</code> représente un panneau d'affichage d'image dans l'interface graphique de l'application.
 * 
 */
public class ImagePanel extends JPanel {
	
	public ImagePanel(BufferedImage image) {
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		ImageDrawer imageDrawer = new ImageDrawer(image, 0, 0);
		ImagePanelMouse mouseListener = new ImagePanelMouse(imageDrawer);

		imageDrawer.addMouseListener(mouseListener);
		imageDrawer.addMouseMotionListener(mouseListener);

		this.add(drawer, BorderLayout.CENTER);

	}

    
}
