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
  private int[][] table = new int[256][256]; // On déclare la table de fréquence qui stocke donc des tableaux de RGB
  public FrequencyTable(){
    for(int i = 0; i < 256; i++){
      for(int y = 0; y < 256; i++){
        this.table[i][y] = new int[3];
      }
    }
  }

  /*
   * La méthode <code>fillTable</code> permet de lire l'image octet par octet et de 
   * stocker la fréquence de chaque couleur dans le tableau 
   *
   * @param image Image à lire bit à bit pour remplir la table;
   */
  public void readImageFillTable(BufferedImage image){
    int color;
    for(int i = 0; i < image.getHeight(); i++){
      for(int y = 0; y < image.getWidth(); y++){
        color = image.getRGB(x, y);
        this.table[i][y][];
      }
    }
  }

  /*
   * Methode toString qui print le beau tableau avec : valeur puis R puis G puis B
   */
  public void toString(){

  }
}
