package fr.iutfbleau.pif.converter;

import java.io.File;

public class Main{
  public static void main(String[] args){

    if(args.length == 1) {

      File file = new File(args[0]);
      ImageReader myImage = new ImageReader(file);

      if(myImage.getImage() != null) {
        System.out.println("The image was sucessfully loaded !");
      }

    } else {
      System.out.println("No file found in argument.");
      System.exit(1);
    }


    FrequencyTable table = new FrequencyTable(myImage.getImage());
  }
}
