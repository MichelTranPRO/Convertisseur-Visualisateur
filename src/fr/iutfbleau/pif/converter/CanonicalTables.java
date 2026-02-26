package fr.iutfbleau.pif.converter;

import java.util.HashMap;

/**
 * La classe <code>CanonicalTables</code> contient la creation et la gestion complète des trois tables de codes canoniques
 *
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class CanonicalTables{

  /**
   * Ces attributs sont les Hash Maps pour les données de couleur rouge, vert, et bleu, 
   * elles sont utilisées pour stockées le code canonique en tant que clé et la valeur de l'intensité en tant que leur valeur respectives
   */
  private HashMap<String, Integer> hashRed;
  private HashMap<String, Integer> hashGreen;
  private HashMap<String, Integer> hashBlue;  
}
