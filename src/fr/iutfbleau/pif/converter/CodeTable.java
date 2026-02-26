package fr.iutfbleau.pif.converter;

import java.util.HashMap;

/**
 * La classe <code>CanonicalTables</code> contient la creation et la gestion complète d'une table de codes canoniques
 *
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class CodeTable{

  /**
   * Cet attribut contient le dictionnaire qui contient chaque chemin avec sa valeur associée
   */
  private HashMap<String, Integer> hashcolor = new HashMap<>();

  public CodeTable(Node root){
  }

  /**
   * La méthode <code>fillTable</code> est une méthode récursive qui parcourt l'arbre et remplit la HashMap
   * 
   * @param actual le noeud où nous sommes actuellement
   * @param path le chemin pour accéder à la valeur actuelle
   */
  public void fillTable(Node actual, String path){

  }

  public HashMap<String, Integer> getHashMap(){
    return this.hashcolor;
  }
}
