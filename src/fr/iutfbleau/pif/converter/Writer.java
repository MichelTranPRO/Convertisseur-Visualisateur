package fr.iutfbleau.pif.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

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

    try(FileOutputStream fos = new FileOutputStream(name + ".pif")){

      fos.write((short) height);
      fos.write((short) width);



      for(int i = 0; i < 256 ; i++){
        if(redtable.containsKey(i)){
          fos.write(redtable.get(i).getLength());
        } else{
          fos.write(0);
        }
      }


      for(int i = 0; i < 256 ; i++){
        if(greentable.containsKey(i)){
          fos.write(greentable.get(i).getLength());
        } else{
          fos.write(0);
        }
      }


      for(int i = 0; i < 256 ; i++){
        if(bluetable.containsKey(i)){
          fos.write(bluetable.get(i).getLength());
        } else{
          fos.write(0);
        }
      }


      ArrayList<byte[]> segments = new ArrayList<>();

      int size = 1000;

      byte[] buffer = new byte[size];
      segments.add(buffer);

      int currentByte = 0;
      int bitCount = 0;
      int index = 0;

      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {

          int pixel = img.getRGB(x, y);

          int[] values = {
            (pixel >> 16) & 0xFF,
            (pixel >> 8) & 0xFF,
            pixel & 0xFF
          };

          Code[] tables = {
            redtable.get(values[0]),
            greentable.get(values[1]),
            bluetable.get(values[2])
          };

          for (Code code : tables) {

            int bits = code.getBits();
            int length = code.getLength();

            // On parcours les bits un par un
            for (int i = length - 1; i >= 0; i--) {

              currentByte <<= 1;                 // On décale à gauche
              currentByte |= (bits >> i) & 1;    // On ajoute le nouveau bit
              bitCount++;

              if (bitCount == 8) {

                if (index >= buffer.length) {
                  buffer = new byte[size];
                  segments.add(buffer);
                  index = 0;
                }

                buffer[index++] = (byte) currentByte;
                currentByte = 0;
                bitCount = 0;
              }
            }
          }
        }
      }

      // Pour ce qui reste à la fin
      if (bitCount > 0) {
        currentByte <<= (8 - bitCount);

        if (index >= buffer.length) {
          buffer = new byte[size];
          segments.add(buffer);
          index = 0;
        }

        buffer[index++] = (byte) currentByte;
      }

      // On écrit tout dans le fichier
      for (int i = 0; i < segments.size(); i++) {

        byte[] segment = segments.get(i);

        if (i == segments.size() - 1) {
          // Dernier segment → écrire seulement la partie remplie
          fos.write(segment, 0, index);
        } else {
          // Segments pleins
          fos.write(segment);
        }
      }
    } catch(IOException e){
      System.out.println("Problème avec l'ouverture du fichier sortie " + e);
    }
  }
}
