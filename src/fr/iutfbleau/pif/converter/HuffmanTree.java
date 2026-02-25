package fr.iutfbleau.pif.converter;

import java.util.PriorityQueue;

public class HuffmanTree{

  private Node root;

  /** 
   * Le constructeur permet de créer trois arbres distincts avec les couleurs R G B selon le codage de Huffman
   *
   * @param table la table de fréquence
   * @param color la composante RGB qu'on veut selectionner dans la table de fréquence
   */
  public HuffmanTree(FrequencyTable table, int color){

    PriorityQueue<Node> queue = new PriorityQueue<>();

    // On ajoute tout les noeuds à la file de priorité
    for(int i = 0; i <= 255; i++){
      if(color == 0 && table.getRed(i) != 0){
          queue.add(new Node(table.getRed(i), i));
      }
      
      if(color == 1 && table.getRed(i) != 0){
          queue.add(new Node(table.getRed(i), i));
      }

      if(color == 2 && table.getRed(i) != 0){
          queue.add(new Node(table.getRed(i), i));
        
      }

    }

    // On itère à travers la file en construisant l'arbre
    while(queue.size() > 1){
      Node n1 = queue.poll();
      Node n2 = queue.poll();

      Node parent = new Node(n1.getFrequency() + n2.getFrequency(), n1, n2);
      queue.add(parent);
    }

    this.root = queue.poll(); // Le dernier élément est forcément la racine

  }

  public Node getRoot(){
    return this.root;
  }

  @Override 
  public String toString() 
  { 
    if (root == null) 
      return "Arbre vide"; 
    return toString(this.root, 0); 
  } 

  private String toString(Node node, int depth) 
  { 
    if (node == null) return ""; 
    String indent = " ".repeat(depth); // 2 espaces par niveau 
    String s = indent + "val=" + node.getValue() + " freq=" + node.getFrequency() + "\n"; 
    s += toString(node.getLeftNode(), depth + 1); 
    s += toString(node.getRightNode(), depth + 1); 

    return s; 
  }
}
