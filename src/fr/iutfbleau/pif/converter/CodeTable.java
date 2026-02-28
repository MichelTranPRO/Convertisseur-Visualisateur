package fr.iutfbleau.pif.converter;

import java.util.HashMap;
import java.util.Map;

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

    if (actual == null) {
      return;
    }

    int value = actual.getValue();

    if (value != 0) {
      this.hashcolor.put(path, value);
    }

    // Parcours récursif
    fillTable(actual.getLeftNode(), path + "0");
    fillTable(actual.getRightNode(), path + "1");
  }

  public HashMap<String, Integer> getHashMap(){
    return this.hashcolor;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CodeTable {\n");

    for (Map.Entry<String, Integer> entry : hashcolor.entrySet()) {
      sb.append("  ")
        .append(entry.getKey())
        .append(" -> ")
        .append(entry.getValue())
        .append("\n");
    }

    sb.append("}");
    return sb.toString();
  }
}
