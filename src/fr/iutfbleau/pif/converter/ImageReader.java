package fr.iutfbleau.pif.converter;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/* 
 * La classe <code>ImageReader</code> est utilisée comme modèle dans un encadrement MVC. 
 * Il fonctionne donc avec les données, reçu en ligne de commande en le lisant dans un format supporté par la méthode read de la classe ImageIO. 
 * 
 * @version 1.1
 * @author Emmanuel SRIVASTAVA-TIAMZON, Michel TRAN, Rayan BISSON
 */ 

public class ImageReader {

  private File fileInput;
  private BufferedImage image;

  public ImageReader(File fileInput) {
    this.fileInput = fileInput;
    readFileInput(this.fileInput);
  }

  public void readFileInput(File file) {
    try {
      this.image = ImageIO.read(file);
    } catch(IOException e) {
      System.err.println("Error while reading the input file : "+e);
    }
  }

  public void setFileInput(File fileInput) {
    this.fileInput = fileInput;
    readFileInput(this.fileInput);
  }

  public File getFileInput() {
    return this.fileInput;
  }

  public BufferedImage getImage() {
    return this.image;
  }

  public void verifyInputLength(String[] input) {
    if(input.length == 0) {
      System.out.println("The given file is not in the correct format");
    } else {
      //ici ça renverra vers l'ouverture JFileChooser en utilisant une autre classe.
    }
  }

}
