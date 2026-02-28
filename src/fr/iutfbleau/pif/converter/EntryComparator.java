package fr.iutfbleau.pif.converter;

import java.util.Comparator;
import java.util.Map;

public class EntryComparator implements Comparator<Map.Entry<Integer, Code>> {

  @Override
  public int compare(Map.Entry<Integer, Code> e1, Map.Entry<Integer, Code> e2) {
    int cmp = Integer.compare(e1.getValue().getLength(), e2.getValue().getLength());
    if (cmp != 0) 
      return cmp;
    return Integer.compare(e1.getKey(), e2.getKey());
  }
}
