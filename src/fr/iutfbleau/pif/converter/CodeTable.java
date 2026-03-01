package fr.iutfbleau.pif.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>CodeTable</code> contient la création et la gestion
 * complète d'une table de codes canoniques.
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

  /**
   * Constructeur vide.
   */
  public CodeTable(){
  }

  /**
   * La méthode <code>fillTable</code> est une méthode récursive qui parcourt l'arbre et remplit la HashMap
   * 
   * @param actual le noeud où nous sommes actuellement (valeur de départ = root)
   * @param path le code pour accéder à la valeur actuelle (valeur de départ = "")
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
   * Vérifie que la table contient au moins deux symboles.
   * Sinon, une exception est levée.
   */
  public void verifyLength(){
    if (hashcolor.size() <= 1) {
      throw new IllegalStateException(
          "Impossible de générer le fichier .pif : la table contient moins de 2 symboles."
          );
    }
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


  /**
   * Retourne la table originale des codes (non canoniques).
   *
   * @return la table originale des codes (HashMap Integer, Code)
   */
  public HashMap<Integer, Code> getHashMap(){
    return this.hashcolor;
  }


  /**
   * Retourne la table des codes canoniques.
   *
   * @return la table des codes canoniques (HashMap Integer, Code)
   */
  public HashMap<Integer, Code> getCanonical(){
    return this.hashcanonical;
  }


  /**
   * Retourne la table canonique sous forme de texte.
   *
   * @return une chaîne de caractères représentant la table canonique
   */
  @Override
  public String toString() {
    String result = "CodeTable {\n";

    // On copie les clés dans un tableau simple
    Integer[] keys = hashcanonical.keySet().toArray(new Integer[0]);
    boolean[] used = new boolean[keys.length];

    for (int i = 0; i < keys.length; i++) {

      int minIndex = -1;
      int minLength = Integer.MAX_VALUE;

      // chercher l'entrée non utilisée avec la plus petite longueur
      for (int j = 0; j < keys.length; j++) {
        if (!used[j]) {
          int length = hashcanonical.get(keys[j]).toString().length();
          if (length < minLength) {
            minLength = length;
            minIndex = j;
          }
        }
      }

      // marquer comme utilisée
      used[minIndex] = true;

      result += "  " + keys[minIndex]
        + " -> "
        + hashcanonical.get(keys[minIndex])
        + "\n";
    }

    result += "}";
    return result;
  }
}
