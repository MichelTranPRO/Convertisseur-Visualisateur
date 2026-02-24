package fr.iutfbleau.pif.converter;

public class Node{
  private int frequency;
  private int value;
  private Node leftNode;
  private Node rightNode;
  public Node(int label){
    this.label = label;
    this.leftNode = null;
    this.rightNode = null;
  }
}

