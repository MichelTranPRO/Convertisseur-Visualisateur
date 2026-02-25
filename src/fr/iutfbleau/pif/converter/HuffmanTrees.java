package fr.iutfbleau.pif.converter;

import java.util.PriorityQueue;

public class HuffmanTrees{

  private Node rootRed;
  private Node rootGreen;
  private Node rootBlue;

  /** 
   * Le constructeur permet de créer trois arbres distincts avec les couleurs R G B selon le codage de Huffman
   *
   * @param table la table de fréquence
   */
  public HuffmanTrees(FrequencyTable table){

    PriorityQueue<Node> queue = new PriorityQueue<>();

    // Construction de l'arbre ROUGE
    // On ajoute tout les noeuds à la file de priorité
    for(int i = 0; i <= 255; i++){
      if(table.getRed(i) != 0){
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

    this.rootRed = queue.poll(); // Le dernier élément est forcément la racine



    // Construction de l'arbre VERT
    // On ajoute tout les noeuds à la file de priorité
    for(int i = 0; i <= 255; i++){
      if(table.getGreen(i) != 0){
        queue.add(new Node(table.getGreen(i), i));
      }
    }

    // On itère à travers la file en construisant l'arbre
    while(queue.size() > 1){
      Node n1 = queue.poll();
      Node n2 = queue.poll();

      Node parent = new Node(n1.getFrequency() + n2.getFrequency(), n1, n2);
      queue.add(parent);
    }

    this.rootGreen = queue.poll(); // Le dernier élément est forcément la racine



    // Construction de l'arbre BLEU
    // On ajoute tout les noeuds à la file de priorité
    for(int i = 0; i <= 255; i++){

      if(table.getBlue(i) != 0){
        queue.add(new Node(table.getBlue(i), i));
      }
    }

    // On itère à travers la file en construisant l'arbre
    while(queue.size() > 1){
      Node n1 = queue.poll();
      Node n2 = queue.poll();

      Node parent = new Node(n1.getFrequency() + n2.getFrequency(), n1, n2);
      queue.add(parent);
    }

    this.rootBlue = queue.poll(); // Le dernier élément est forcément la racine

  }

  public Node getRedRoot(){
    return this.rootRed;
  }

  public Node getBlueRoot(){
    return this.rootRed;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (rootRed == null && rootGreen == null && rootBlue == null) {
      return "Arbres vides";
    }

    if (rootRed != null) {
      sb.append("=== Arbre Rouge ===\n");
      sb.append(toString(rootRed, 0));
    }

    if (rootGreen != null) {
      sb.append("=== Arbre Vert ===\n");
      sb.append(toString(rootGreen, 0));
    }

    if (rootBlue != null) {
      sb.append("=== Arbre Bleu ===\n");
      sb.append(toString(rootBlue, 0));
    }

    return sb.toString();
  }

  private String toString(Node node, int depth) {
    if (node == null) return "";

    String indent = "  ".repeat(depth); // 2 espaces par niveau
    StringBuilder sb = new StringBuilder();

    sb.append(indent)
      .append("val=").append(node.getValue())
      .append(" freq=").append(node.getFrequency())
      .append("\n");

    sb.append(toString(node.getLeftNode(), depth + 1));
    sb.append(toString(node.getRightNode(), depth + 1));

    return sb.toString();
  }
}
