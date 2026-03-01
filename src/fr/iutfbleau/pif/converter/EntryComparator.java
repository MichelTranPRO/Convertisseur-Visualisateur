package fr.iutfbleau.pif.converter;

import java.util.Comparator;
import java.util.Map;

/**
 * La classe <code>EntryComparator</code> compare deux entrées 
 * (symbole, code) pour trier les codes selon l'ordre canonique.
 * Les codes sont comparés d'abord par longueur, puis par valeur du symbole.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class EntryComparator implements Comparator<Map.Entry<Integer, Code>> {

  /**
 * Constructeur par défaut de la classe EntryComparator.
 * Initialise un comparateur pour trier les entrées (clé, code) selon l'ordre canonique.
 */
public EntryComparator() {
}

  /**
   * Comparaison de deux entrées (symbole, code) selon ordre canonique
   * @param e1 la première entrée à comparer
   * @param e2 la seconde entrée à comparer 
   * @return un entier négatif si e1 est inférieur à e2, zéro si e1 est égal à e2, 
   *         et un entier positif si e1 est supérieur à e2
   */
  @Override
  public int compare(Map.Entry<Integer, Code> e1, Map.Entry<Integer, Code> e2) {
    int cmp = Integer.compare(e1.getValue().getLength(), e2.getValue().getLength());
    if (cmp != 0) 
      return cmp;
    return Integer.compare(e1.getKey(), e2.getKey());
  }
}
