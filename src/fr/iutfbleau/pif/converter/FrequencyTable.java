package fr.iutfbleau.pif.converter;

/** 
 * La classe <code>FrequencyTable</code> est une classe qui crée une table de fréquence.
 * Comme la table de fréquence stocke la fréquence de valeurs de couleurs allant de 0 à 255, 
 * nous utilisons un simple tableau de 256 valeurs pour tout manipuler.
 * 
 * @author Rayan Bisson, Michel Tran, Emmanuel Strivastava-Tiamzon
 */

public class FrequencyTable{
  private int[][] table = new int[256][256]; // On déclare la table de fréquence qui stocke donc des tableaux de RGB
  private File
  public FrequencyTable(){
    for(int i = 0; i < 256; i++){
      for(int y = 0; y < 256; i++){
        this.table[i][y] = new int[3];
      }
    }
  }
  // A faire en 1 ligne d'après Lukas
  public 
}
