package fr.iutfbleau.pif.converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;

/**
 * Listener pour écrire le fichier PIF lorsque l'utilisateur clique sur un bouton.
 * Cette classe encapsule toutes les informations nécessaires pour appeler Writer.writeFile
 * sans utiliser de lambda.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class WriteFileListener implements ActionListener {

  private final String filename;
  private final CodeTable redTable;
  private final CodeTable greenTable;
  private final CodeTable blueTable;
  private final BufferedImage image;

  /**
   * Crée un WriteFileListener avec les tables de couleurs et l'image.
   *
   * @param filename le nom du fichier PIF à créer
   * @param red la table des codes rouges
   * @param green la table des codes verts
   * @param blue la table des codes bleus
   * @param image l'image à convertir
   */
  public WriteFileListener(String filename, CodeTable red, CodeTable green, CodeTable blue, BufferedImage image) {
    this.filename = filename;
    this.redTable = red;
    this.greenTable = green;
    this.blueTable = blue;
    this.image = image;
  }

  /**
   * Appelé lorsqu'on clique sur le bouton. Écrit le fichier PIF et affiche
   * un message de confirmation.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Writer.writeFile(
        filename,
        redTable.getHashMap(),
        greenTable.getHashMap(),
        blueTable.getHashMap(),
        image.getHeight(),
        image.getWidth(),
        image
        );
    JOptionPane.showMessageDialog(null, "Fichier PIF écrit avec succès !");
  }
}
