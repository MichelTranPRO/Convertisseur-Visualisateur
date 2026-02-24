package fr.iutfbleau.pif.visualizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * The class <code>CreateImage</code> is responsible for creating an image from a data input stream that goes into it's parameters.
 * 
 * @version 1.1
 * @author Michel TRAN, Rayan BISSON, Emmanuel SRIVASTAVA-TIAMZON
 */
public class CreateImage {

    /**
     * This is one of the attributes of the class, it has a DataInputStream type that is used to read the data of the image.
     */
    private DataInputStream dataInput;
    
    /**
     * This is an integer that is used to store the current byte that is being read from the data input stream.
     */
    private int currentByte;

    /**
     * This is an integer that is used to store the index of the bit.
     */
    private int indexBit;

    /**
     * This is the constructor of the class, it has a DataInputStream type parameter that is used to read the data of the image.
     * @param dataInput is the data input stream that is used to read the data of the image.
     */
    public CreateImage(DataInputStream dataInput){
        this.dataInput=dataInput;
        this.currentByte=0;
        this.indexBit=-1;
    }

    /**
     * The method <code>getBit</code> is used to read the bits from the data input stream. 
     * @return an Integer that is the value of the bit that is being read from the data input stream.
     * @throws IOException
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
     * The method <code>decodeValue</code> is used to decode the value of the color from the data input stream. 
     * It uses a HashMap that is passed as a parameter to decode the value of the color.
     * @param hash is a HashMap that is used to decode the value of the color, it has a String type as key and an Integer type as value.
     * @return an Integer that is the value of the color that is being decoded.
     * @throws IOException
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
     * The method <code>buildImage</code> is used to build the image from the data input stream. It uses the method <code>decodeValue</code> to decode the value of the color and it uses the HashMaps that are passed as parameters to decode the value of the color.
     * @param w
     * @param h
     * @param hashRed
     * @param hashGreen
     * @param hashBlue
     * @return
     * @throws IOException
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
