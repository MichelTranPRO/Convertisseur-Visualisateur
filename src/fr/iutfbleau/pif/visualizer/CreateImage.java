package fr.iutfbleau.pif.visualizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * La classe <code>CreateImage</code> est responsable pour la création d'une image à partir d'un flux d'entrée qui part dans les paramètres de celui-ci.
 * 
 * @version 1.1
 * @author Michel TRAN, Rayan BISSON, Emmanuel SRIVASTAVA-TIAMZON
 */
public class CreateImage {

    /**
     * Cette attribut a pour type DataInputStream qui est utilisé pour lire les données de l'image.
     */
    private DataInputStream dataInput;
    
    /**
     * Cette attribut est un entier qui est utilisé pour stocké le bit courant qui est en train d'être lu depuis le flux d'entrée de données.
     */
    private int currentByte;

    /**
     * Ceci est un attribut entier qui est utilisé pour stocké l'index du bit
     */
    private int indexBit;

    /**
     * C'est le constructeur de la classe, il possède un paramètre de type DataInputStream qui est utilisé pour lire les données de l'image.
     * @param dataInput est le flux d'entrée de données utilisé pour lire les données de l'image.
     */
    public CreateImage(DataInputStream dataInput){
        this.dataInput=dataInput;
        this.currentByte=0;
        this.indexBit=-1;
    }

    /**
     * La méthode <code>getBit</code> est utilisée pour lire les bits du fulux d'entrée de données. 
     * @return un entier qui est la valeur du bit qui est en train d'être lu par le flux d'entrée de donnée.
     * @throws IOException si une erreur de lecture se produit lors de la lecture du flux d'entrée de données.
     */
    private int getBit() throws IOException{
            if (indexBit < 0){
                currentByte = dataInput.readUnsignedByte();
                indexBit=7;
            }
            int bit = currentByte >> indexBit;
            bit = bit &1 ;
            indexBit--;
            return bit;
    }

    /**
     * Cette méthode <code>decodeValue</code> est utilisée pour decoder ma vameir de la couleur du flux d'entrée de donnée.
     * Il utilise une HashMap qui est passé en paramètre pour decoder la valeur de la couleur.
     * @param hash est un HashMap qui est utilisé pour decoder la valeur de la couleur, il a une clé de type String et une valeur de type int.
     * @return un entier qui est la valeur de la couleur qui est en train d'être décoder.
     * @throws IOException si une erreur de lecture se produit lors de la lecture du flux d'entrée de données.
     */
    private int decodeValue(HashMap<String, Integer> hash) throws IOException{
        Integer intensity=null;
        String temp="";
        int bit;
        while(intensity == null){
            bit=getBit();
            if (bit== -1){
                throw new IOException("The byte cannot be read");
            }

            temp += bit;
            intensity = hash.get(temp);
        }
        return intensity;
    }

    /**
     * La méthode <code>buildImage</code> est utilisée pour construire l'image à partir du flux d'entrée de données.
     * Elle utilise la méthode <code>decodeValue</code> ainsi que les HashMaps passées en paramètres pour décoder la valeur des couleurs.
     * @param w l'entier représentant la largeur de l'image.
     * @param h l'entier représentant la hauteur de l'image.
     * @param hashRed la HashMap utilisée pour décoder la valeur de la couleur rouge (Clé : String, Valeur : Integer).
     * @param hashGreen la HashMap utilisée pour décoder la valeur de la couleur verte (Clé : String, Valeur : Integer).
     * @param hashBlue a HashMap utilisée pour décoder la valeur de la couleur bleue (Clé : String, Valeur : Integer).
     * @return une BufferedImage correspondant à l'image construite à partir du flux de données.
     * @throws IOException si une erreur de lecture se produit lors de la lecture du flux d'entrée de données.
     */
    public BufferedImage buildImage(int w, int h, HashMap<String, Integer> hashRed
                                                , HashMap<String, Integer> hashGreen
                                                , HashMap<String, Integer> hashBlue) throws IOException {
        int width = w;
        int heigth = h;
        BufferedImage img = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                int r = decodeValue(hashRed);
                int g = decodeValue(hashGreen);
                int b = decodeValue(hashBlue);
                int color= new Color(r,g,b).getRGB();
                img.setRGB(x, y, color);
                //System.out.println("couleur ajouté : "+ color);
            }
        }
        return img;
    }
}
