package fr.iutfbleau.pif.converter;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/**
 * La classe <code>ImageReader</code> sert à manipuler un fichier qu'on donne dans le constructeur.
 * Cette classe sert principalement à transformer le fichier donné en <code>BufferedImage</code> pour 
 * en faciliter la manipulation
 * 
 * 
 * @version 1.1
 * @author Emmanuel SRIVASTAVA-TIAMZON, Michel TRAN, Rayan BISSON
 */
public class ImageReader {

  /** L'image décodée et chargée en mémoire. */
  private BufferedImage image;

  /**
   * Constructeur qui lit le fichier et le charge en mémoire sous forme de <code>BufferedImage</code>.
   * 
   * @param file le fichier image à lire
   */
  public ImageReader(File file) {
    try {
      this.image = ImageIO.read(file);
    } catch(IOException e) {
      System.err.println("Error while reading the input file : "+ e );
    }
  }

  /**
   * Getter pour récupérer l'image chargée.
   * @return L'objet <code>BufferedImage</code> contenant les données de l'image lue.
   */
  public BufferedImage getImage() {
    return this.image;
  }
}
