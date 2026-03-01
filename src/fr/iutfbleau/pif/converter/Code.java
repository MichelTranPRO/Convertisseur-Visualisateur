package fr.iutfbleau.pif.converter;

/**
 * La classe <code>Code</code> représente un code binaire associé à un symbole.
 * Chaque code a une valeur en bits et une longueur.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class Code{

  /** La valeur binaire du code */
  private int bits;

  /** La longueur du code en nombre de bits */
  private int length;

  /**
   * Constructeur de la classe <code>Code</code>.
   * 
   * @param bits La valeur binaire du code
   * @param length La longueur du code en nombre de bits
   */
  public Code(int bits, int length){
    this.bits = bits;
    this.length = length;
  }

  /**
   * Retourne la valeur binaire du code.
   * 
   * @return La valeur en bits
   */
  public int getBits(){
    return this.bits;
  }

  /**
   * Retourne la longueur du code.
   * 
   * @return La longueur en bits
   */
  public int getLength(){
    return this.length;
  }

  /**
   * Retourne une représentation textuelle du code en binaire.
   * 
   * @return Le code sous forme de chaîne binaire
   */
  @Override
  public String toString() {
    return Integer.toBinaryString(this.bits);
  }
}
