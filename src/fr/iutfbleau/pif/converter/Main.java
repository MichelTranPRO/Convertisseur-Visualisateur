package fr.iutfbleau.pif.converter;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * La classe <code>Main</code> contient le point d'entrée du programme.
 * Elle permet de sélectionner une image, de créer les tables de fréquence et
 * les arbres de Huffman pour chaque composante de couleur (R, G, B), puis
 * de générer les tables de codes et lancer l'interface de conversion.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class Main{

  /**
   * Constructeur par défaut de la classe Main.
   * Initialise le programme et prépare le traitement de l'image.
   */
  public Main() {
  }
  /**
   * Point d'entrée du programme.
   * 
   * @param args Si un argument est fourni, il correspond au chemin du fichier image.
   *             Sinon, une boîte de dialogue permet de sélectionner l'image.
   */
  public static void main(String[] args){

    File file = null;
    String filename = ""; // Pour la partie écriture du fichier

    if(args.length == 1) {

      // Partie récupération du fichier
      file = new File(args[0]);
      filename = file.getName();
      int dotIndex = filename.lastIndexOf('.'); 
      if (dotIndex > 0) { 
        filename = filename.substring(0, dotIndex); 
      }


    } else {
      // Partie choix du fichier
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showOpenDialog(null);

      // Si on a bien choisi quelque chose on récupère le fichier selectionné
      if (result == JFileChooser.APPROVE_OPTION) { 
        file = chooser.getSelectedFile();
        filename = file.getName();       
        int dotIndex = filename.lastIndexOf('.'); 
        if (dotIndex > 0) {
          filename = filename.substring(0, dotIndex); 
        }

      } else {
        System.out.println("No file selected.");
        System.exit(1);
      }
    }

    ImageReader myImage = new ImageReader(file);

    if (myImage.getImage() != null) {
      //System.out.println("The image was successfully loaded !");

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
      redtable.verifyLength();
      redtable.toCanonical();
      //System.out.println(redtable);

      CodeTable greentable = new CodeTable();
      greentable.fillTable(greentree.getRoot(), new Code(0,0));
      greentable.verifyLength();
      greentable.toCanonical();
      //System.out.println(greentable);

      CodeTable bluetable = new CodeTable();
      bluetable.fillTable(bluetree.getRoot(), new Code(0, 0));
      bluetable.verifyLength();
      bluetable.toCanonical();
      //System.out.println(bluetable);


      Writer.writeFile(filename,
          redtable.getHashMap(),
          greentable.getHashMap(),
          bluetable.getHashMap(),
          myImage.getImage().getHeight(),
          myImage.getImage().getWidth(),
          myImage.getImage());

      ConverterFrame frame = new ConverterFrame(myImage.getImage(), table, redtable, greentable, bluetable);
      ConverterController controller = new ConverterController(frame);

    } else {
      System.out.println("Error: image could not be loaded.");
    }


  }
}

