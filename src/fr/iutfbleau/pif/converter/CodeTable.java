package fr.iutfbleau.pif.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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
  private HashMap<Integer, Code> hashcolor = new HashMap<>();
  private HashMap<Integer, Code> hashcanonical = new HashMap<>();

  public CodeTable(){
  }

  /**
   * La méthode <code>fillTable</code> est une méthode récursive qui parcourt l'arbre et remplit la HashMap
   * 
   * @param actual le noeud où nous sommes actuellement (valeur de départ = root)
   * @param path le chemin pour accéder à la valeur actuelle (valeur de départ = "")
   */
  public void fillTable(Node actual, Code path){

    if (actual == null)
      return;

    int value = actual.getValue();

    if (actual.getLeftNode() == null && actual.getRightNode() == null) {
      int symbol = actual.getValue();
      hashcolor.put(symbol, path);
      return;
    }

    // On va à gauche = ajouter 0
    Code leftPath = new Code(path.getBits() << 1, path.getLength() + 1);
    fillTable(actual.getLeftNode(), leftPath);

    // On va à droite = ajouter 1
    Code rightPath = new Code((path.getBits() << 1) | 1, path.getLength() + 1);
    fillTable(actual.getRightNode(), rightPath);
  }

  /**
   * Cette méthode sert à transformer la table de codes initiaux en codes canoniques 
   */
  public void toCanonical(){
    List<Map.Entry<Integer, Code>> entries = new ArrayList<>(hashcolor.entrySet());

    // On trie par longueur du code puis par valeur avec le comparator
    entries.sort(new EntryComparator());

    int code = 0;
    int prevLength = entries.get(0).getValue().getLength();

    for (Map.Entry<Integer, Code> entry : entries) {
      int len = entry.getValue().getLength();

      // Si l'élément suivant a une longueur supérieure
      if (len > prevLength) {
        code <<= (len - prevLength); // On fait simplement un décalage à gauche de la 
                                     // longueur nécessaire pour arriver à la longueur voulue 
                                     // comme ça on a également des zéros supplémentaires à droite
        prevLength = len; // On update l'ancienne longueur
      }

      // On stocke le code canonique
      hashcanonical.put(entry.getKey(), new Code(code, len));

      code++; // sinon on ajoute 1 au code canonique du précédent
    }
  }

  public HashMap<Integer, Code> getHashMap(){
    return this.hashcolor;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CodeTable {\n");

    for (Map.Entry<Integer, Code> entry : hashcanonical.entrySet()){
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
