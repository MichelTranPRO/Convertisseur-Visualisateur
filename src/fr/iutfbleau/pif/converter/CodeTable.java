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

  public CodeTable(){
  }

  /**
   * La méthode <code>fillTable</code> est une méthode récursive qui parcourt l'arbre et remplit la HashMap
   * 
   * @param actual le noeud où nous sommes actuellement (valeur de départ = root)
   * @param path le chemin pour accéder à la valeur actuelle (valeur de départ = "")
   */
  public void fillTable(Node actual, String path){
    int value;

    if((value = actual.getLeftNode().getValue()) != 0)
      this.hashcolor.put(path, value);

    else if((value = actual.getRightNode().getValue()) != 0)
      this.hashcolor.put(path, value);

    else{
      fillTable()
    }
  }

  public HashMap<String, Integer> getHashMap(){
    return this.hashcolor;
  }
}
