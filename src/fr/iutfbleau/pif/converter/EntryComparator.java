package fr.iutfbleau.pif.converter;

import java.util.Comparator;
import java.util.Map;

public class EntryComparator implements Comparator<Map.Entry<Integer, Code>> {
  /**
     * Comparaison de deux entrées (symbole, code) selon ordre canonique
     * @param e1 la première entrée à comparer
     * @param e2 la seconde entrée à comparer 
     * @return un entier négatif si e1 < e2
     *          zero si e1 = e2
     *          un entier positif si e1 > e2
     */

  @Override
  public int compare(Map.Entry<Integer, Code> e1, Map.Entry<Integer, Code> e2) {
    int cmp = Integer.compare(e1.getValue().getLength(), e2.getValue().getLength());
    if (cmp != 0) 
      return cmp;
    return Integer.compare(e1.getKey(), e2.getKey());
  }
}
