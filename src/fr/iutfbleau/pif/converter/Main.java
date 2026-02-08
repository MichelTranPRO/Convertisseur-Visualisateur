package fr.iutfbleau.pif.converter;

import java.io.File;

public class Main{
  public static void main(String[] args){
    if(args.length == 1) {

      // Partie récupération du fichier
      File file = new File(args[0]);
      ImageReader myImage = new ImageReader(file);

      if(myImage.getImage() != null) {
        System.out.println("The image was sucessfully loaded !");

        // Partie table de fréquence
        FrequencyTable table = new FrequencyTable();
        table.readImageFillTable(myImage.getImage());
        System.out.println(table.toString());

      } else {
        System.out.println("Error: image could not be loaded.");
      }

    } else {
      System.out.println("No file found in argument.");
      System.exit(1);
    }



  }
}

