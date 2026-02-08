package fr.iutfbleau.pif;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/* 
 * La classe <code>ImageReader</code> sert à manipuler un fichier qu'on donne dans le constructeur.
 * Cette classe sert principalement à transformer le fichier donné en <code>BufferedImage</code> pour 
 * en faciliter la manipulation
 * 
 * 
 * @version 1.1
 * @author Emmanuel SRIVASTAVA-TIAMZON, Michel TRAN, Rayan BISSON
 */ 

public class ImageReader {

  private BufferedImage image;

  public ImageReader(File file) {
    try {
      this.image = ImageIO.read(file);
    } catch(IOException e) {
      System.err.println("Error while reading the input file : "+ e );
    }
  }

  public BufferedImage getImage() {
    return this.image;
  }
}
