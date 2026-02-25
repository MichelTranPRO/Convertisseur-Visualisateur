package fr.iutfbleau.pif.converter;

import java.util.PriorityQueue;

public class Tree{

  private Node root;

  public Tree(FrequencyTable table){

    PriorityQueue<Node> queue = new PriorityQueue<>();

    // On ajoute tout les noeuds à la file de priorité
    for(int i = 0; i <= 255; i++){
      if(table.getRed(i) != 0){
        queue.add(new Node(table.getRed(i), i));
      }

      if(table.getGreen(i) != 0){
        queue.add(new Node(table.getGreen(i), i));
      }

      if(table.getBlue(i) != 0){
        queue.add(new Node(table.getBlue(i), i));
      }



    }
    while(queue.size() > 1){
      Node n1 = queue.poll();
      Node n2 = queue.poll();

      Node parent = new Node(n1.getFrequency() + n2.getFrequency(), n1, n2);
      queue.add(parent);
    }

    this.root = queue.poll(); // Le dernier élément est forcément la racine

  }


  @Override
  public String toString() {
    if (root == null) return "Arbre vide";
    return toString(root, 0);
  }

  private String toString(Node node, int depth) {
    if (node == null) return "";
    String indent = "  ".repeat(depth); // 2 espaces par niveau
    String s = indent + "val=" + node.getValue() + " freq=" + node.getFrequency() + "\n";
    s += toString(node.getLeftNode(), depth + 1);
    s += toString(node.getRightNode(), depth + 1);
    return s;
  }
}