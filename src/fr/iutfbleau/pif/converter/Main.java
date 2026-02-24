package fr.iutfbleau.pif.converter;

import java.io.File;
import javax.swing.JFileChooser;

public class Main{
  public static void main(String[] args){
    
    File file = null;

    if(args.length == 1) {

      // Partie récupération du fichier
      file = new File(args[0]);

    } else {
      // Partie choix du fichier
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showOpenDialog(null);

      // Si on a bien choisi quelque chose on récupère le fichier selectionné
      if (result == JFileChooser.APPROVE_OPTION) { 
        file = chooser.getSelectedFile();
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
      System.out.println(table);

      new Tree(table);

    } else {
      System.out.println("Error: image could not be loaded.");
    }
  }
}

