package fr.iutfbleau.pif.converter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class Writer{
  /**
   * La méthode writeFile permet de créer et d'insérer les headers et le format complet du fichier .pif
   *
   * @param name le nom du fichier pour qu'on le réutilise
   */
  static void writeFile(String name, 
      HashMap<Integer, Code> redtable, 
      HashMap<Integer, Code> greentable, 
      HashMap<Integer, Code> bluetable,
      int height,
      int width,
      BufferedImage img){

    short headheight = Integer.shortValue(height);
    short headwidth = Integer.shortValue(width);


    byte[] red = new byte[256];

    //On vérifie si chaque valeur est dans notre hashmap
    for(Integer i = 0; i < 256 ; i++){
      if(redtable.containsKey(i)){
        red[i] = redtable.getValue(i).getLength(); // Si elle est dedans on ajoute sa length
      } else{
        red[i] = 0; // Sinon on met 0
      }
    }


    byte[] green = new byte[256];

    //On vérifie si chaque valeur est dans notre hashmap
    for(Integer i = 0; i < 256 ; i++){
      if(greentable.containsKey(i)){
        green[i] = greentable.getValue(i).getLength(); // Si elle est dedans on ajoute sa length
      } else{
        green[i] = 0; // Sinon on met 0
      }
    }


    byte[] blue = new byte[256];

    //On vérifie si chaque valeur est dans notre hashmap
    for(Integer i = 0; i < 256 ; i++){
      if(bluetable.containsKey(i)){
        blue[i] = bluetable.getValue(i).getLength(); // Si elle est dedans on ajoute sa length
      } else{
        blue[i] = 0; // Sinon on met 0
      }
    }


    String pixels = "";
    for(int x = 0; x < height; x++){
      for(int y = 0; y < width; y++){
        int pixel = image.getRGB(x, y);

        int redp = (pixel >> 16) & 0xFF; // Décalage à droite puis suppression de la partie non intéressante puis on garde que le premier octet
        int greenp = (pixel >> 8) & 0xFF;
        int bluep = pixel & 0xFF;
      }
    }


    try {
      FileWriter myWriter = new FileWriter(name + ".pif");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
