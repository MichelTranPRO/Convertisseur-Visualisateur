package fr.iutfbleau.pif.converter;

import java.lang.Comparable;

public class Node implements Comparable<Node>{

  private int frequency;
  private int value;
  private Node leftNode;
  private Node rightNode;

  public Node(int frequency, int value){
    this.frequency = frequency;
    this.value = value;
    this.leftNode = null;
    this.rightNode = null;
  }

  public Node(int frequency, Node n1, Node n2){
    this.frequency = frequency;
    this.value = 0;
    this.leftNode = n1;
    this.rightNode = n2;
  }


  public int getFrequency(){
    return this.frequency;
  }

  public int getValue() { 
    return this.value; 
  }

  public Node getLeftNode() { 
    return this.leftNode; 
  }

  public Node getRightNode() { 
    return this.rightNode; 
  }


  /* 
   * On override la méthode pour définir la logique de l'ordre pour la PriorityQueue
   *
   * @return la comparaison des deux fréquences
   */
  @Override
  public int compareTo(Node other){
    return Integer.compare(this.frequency, other.getFrequency());
  }
}

