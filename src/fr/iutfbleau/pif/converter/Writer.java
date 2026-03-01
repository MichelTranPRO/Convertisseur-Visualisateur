package fr.iutfbleau.pif.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;

/**
 * La classe <code>Writer</code> permet d'écrire un fichier .pif à partir
 * des tables de codes et de l'image originale.
 * Elle gère l'insertion des headers et le codage complet de l'image.
 * 
 * @version 1.1
 * @author Rayan Bisson, Michel Tran, Emmanuel Srivastava-Tiamzon
 */
public class Writer{

  /**
   * Constructeur par défaut de la classe Writer.
   * Cette classe ne nécessite pas d'initialisation particulière.
   */
  public Writer() {
  }

  /**
   * La méthode <code>writeFile</code> permet de créer et d'insérer les headers 
   * et le format complet du fichier .pif.
   *
   * @param name le nom du fichier pour qu'on le réutilise
   * @param redtable table de codes pour le canal rouge
   * @param greentable table de codes pour le canal vert
   * @param bluetable table de codes pour le canal bleu
   * @param height hauteur de l'image
   * @param width largeur de l'image
   * @param img l'image originale
   */
  static void writeFile(String name, 
      HashMap<Integer, Code> redtable, 
      HashMap<Integer, Code> greentable, 
      HashMap<Integer, Code> bluetable,
      int height,
      int width,
      BufferedImage img){

    File out = new File(name + ".pif");
    try(FileOutputStream fos = new FileOutputStream(out);
        BufferedOutputStream bos = new BufferedOutputStream(fos)){

      bos.write(width >> 8);
      bos.write(width);

      bos.write(height >> 8);  // OCtet de poids fort
      bos.write(height);         // Octet de poids faible


      for(int i = 0; i < 256 ; i++){
        if(redtable.containsKey(i)){
          bos.write(redtable.get(i).getLength());
        } else{
          bos.write(0);
        }
      }


      for(int i = 0; i < 256 ; i++){
        if(greentable.containsKey(i)){
          bos.write(greentable.get(i).getLength());
        } else{
          bos.write(0);
        }
      }


      for(int i = 0; i < 256 ; i++){
        if(bluetable.containsKey(i)){
          bos.write(bluetable.get(i).getLength());
        } else{
          bos.write(0);
        }
      }



      int currentByte = 0;
      int bitCount = 0;

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {

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

            int length = code.getLength();

            String bits = Integer.toBinaryString(code.getBits());

            // Pour retrouver le code canonique avec la bonne longueur on rajoute les 0 manquants à gauche
            while (bits.length() < length) {
              bits = "0" + bits;
            }

            //System.out.println("Code original: " + code.getBits() + " | Longueur attendue: " + length + " | String binaire: " + bits);

                                                          // Parcours des bits
            for (int i = 0; i < bits.length(); i++){

              currentByte <<= 1; // on libère 1 bit de place
              bitCount++; 

              if(bits.charAt(i) == '1'){
                currentByte |= 1; // On met le bit à 1 si nécessaire
              }

              // Si on a un octet complet qu'on a formé
              if (bitCount == 8) {
                bos.write(currentByte); // On écrit l'octet actuel
                currentByte = 0;
                bitCount = 0;
              }
            }
          }
        }
      }

      // Bits restants dans l'octet qu'on doit finir d'écrire
      if (bitCount > 0) {
        currentByte <<= (8 - bitCount); // On récupère les bits qu'il nous reste
        bos.write(currentByte);
      }


      bos.flush();
      bos.close();

      fos.close();
      System.out.println("Ecriture du fichier finie");
    } catch(IOException e){
      System.out.println("Problème d'écriture : " + e);
    }
  }
}
