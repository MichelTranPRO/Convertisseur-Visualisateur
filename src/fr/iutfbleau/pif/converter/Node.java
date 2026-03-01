package fr.iutfbleau.pif.converter;

import java.lang.Comparable;

/**
 * La classe <code>Node</code> représente un noeud dans un arbre de Huffman.
 * Chaque noeud contient une fréquence et une valeur, ainsi que des références 
 * vers ses noeuds gauche et droit.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class Node implements Comparable<Node>{

  private int frequency;
  private int value;
  private Node leftNode;
  private Node rightNode;

  /**
   * Constructeur pour créer une feuille (valeur + fréquence).
   *
   * @param frequency la fréquence du symbole
   * @param value la valeur du symbole
   */
  public Node(int frequency, int value){
    this.frequency = frequency;
    this.value = value;
    this.leftNode = null;
    this.rightNode = null;
  }

  /**
   * Constructeur pour créer un noeud interne (fusion de deux noeuds).
   *
   * @param frequency la fréquence cumulée des deux noeuds
   * @param n1 le noeud gauche
   * @param n2 le noeud droit
   */
  public Node(int frequency, Node n1, Node n2){
    this.frequency = frequency;
    this.value = 0;
    this.leftNode = n1;
    this.rightNode = n2;
  }

  /**
   * Retourne la fréquence du noeud.
   *
   * @return la fréquence du noeud (int)
   */
  public int getFrequency(){
    return this.frequency;
  }

  /**
   * Retourne la valeur du noeud.
   *
   * @return la valeur du noeud (int)
   */
  public int getValue() { 
    return this.value; 
  }

  /**
   * Retourne le noeud gauche.
   *
   * @return le noeud gauche (Node), ou null si inexistant
   */
  public Node getLeftNode() { 
    return this.leftNode; 
  }

  /**
   * Retourne le noeud droit.
   *
   * @return le noeud droit (Node), ou null si inexistant
   */
  public Node getRightNode() { 
    return this.rightNode; 
  }


  /**
   * On override la méthode pour définir la logique de l'ordre pour la PriorityQueue.
   *
   * @param other l'autre noeud à comparer
   * @return la comparaison des deux fréquences
   */
  @Override
  public int compareTo(Node other){
    return Integer.compare(this.frequency, other.getFrequency());
  }
}

