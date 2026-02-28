package fr.iutfbleau.pif.converter;

import java.io.File;
import javax.swing.JFileChooser;

public class Main{
  public static void main(String[] args){
    
    File file = null;
    String filename; // Pour la partie écriture du fichier

    if(args.length == 1) {

      // Partie récupération du fichier
      file = new File(args[0]);
      filename = args[0];
      

    } else {
      // Partie choix du fichier
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showOpenDialog(null);

      // Si on a bien choisi quelque chose on récupère le fichier selectionné
      if (result == JFileChooser.APPROVE_OPTION) { 
        file = chooser.getSelectedFile();
        filename = file.getName();
      } else {
        System.out.println("No file selected.");
        System.exit(1);
      }
    }

    ImageReader myImage = new ImageReader(file);

    if (myImage.getImage() != null) {
      System.out.println("The image was successfully loaded !");

      // Partie table de fréquence
      FrequencyTable table = new FrequencyTable();
      table.readImageFillTable(myImage.getImage());
      // System.out.println(table);

      // Partie codage de Huffman
      HuffmanTree redtree = new HuffmanTree(table, 0);
      HuffmanTree greentree = new HuffmanTree(table, 1);
      HuffmanTree bluetree = new HuffmanTree(table, 2);

      //System.out.println(redtree);
      //System.out.println(greentree);
      //System.out.println(bluetree);

      CodeTable redtable = new CodeTable();
      redtable.fillTable(redtree.getRoot(), new Code(0,0));
      redtable.toCanonical();
      System.out.println(redtable);

      CodeTable greentable = new CodeTable();
      greentable.fillTable(greentree.getRoot(), new Code(0,0));
      greentable.toCanonical();
      System.out.println(greentable);

      CodeTable bluetable = new CodeTable();
      bluetable.fillTable(bluetree.getRoot(), new Code(0, 0));
      bluetable.toCanonical();
      System.out.println(bluetable);


      // Pas oublier d'inclure le filename dans ma méthode

    } else {
      System.out.println("Error: image could not be loaded.");
    }
  }
}

