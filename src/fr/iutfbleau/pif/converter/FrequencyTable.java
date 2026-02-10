package fr.iutfbleau.pif.converter;

import java.awt.image.BufferedImage;
/** 
 * La classe <code>FrequencyTable</code> est une classe qui crée une table de fréquence et nous 
 * permet de la manipuler.
 * Comme la table de fréquence stocke la fréquence de valeurs de couleurs allant de 0 à 255, 
 * nous utilisons un simple tableau de 256 valeurs pour tout manipuler.
 * 
 * @author Rayan Bisson, Michel Tran, Emmanuel Strivastava-Tiamzon
 */

public class FrequencyTable{

  private int[][] table = new int[256][3]; // On déclare la table de fréquence qui stocke donc des tableaux de RGB

  public FrequencyTable(){

    for(int i = 0; i < 256; i++){
      table[i][0] = 0; // R
      table[i][1] = 0; // G
      table[i][2] = 0; // B
    }

  }

  /*
   * La méthode <code>readImageFillTable</code> permet de lire l'image octet par octet et de 
   * stocker la fréquence de chaque couleur dans le tableau 
   *
   * @param image Image à lire bit à bit pour remplir la table;
   */
  public void readImageFillTable(BufferedImage image){

    int pixel = 0;
    int red = 0;
    int green = 0;
    int blue = 0;

    for(int i = 0; i < image.getWidth(); i++){
      for(int j = 0; j < image.getHeight(); j++){

        pixel = image.getRGB(i, j);

        red = (pixel >> 16) & 0xFF; // Décalage à droite puis suppression de la partie non intéressante puis on garde que le premier octet
        green = (pixel >> 8) & 0xFF;
        blue = pixel & 0xFF;

        this.table[red][0] ++;
        this.table[green][1] ++;
        this.table[blue][2] ++;

      }
    }
  }

  /*
   * Methode toString qui print le beau tableau avec : valeur puis R puis G puis B
   *
   * @returns un string qui contient tout les éléments;
   */
  @Override
  public String toString(){
    String s = "";

    for (int i = 0; i < table.length; i++) {
      s += "Valeur " + i + " : { "
        + table[i][0] + ", "
        + table[i][1] + ", "
        + table[i][2] + " }"
        + System.lineSeparator(); // Saut à la ligne qui s'adapte si on est sur windows ou linux pour les testeurs windows
    }

    return s;
  }
}
