package fr.iutfbleau.pif.visualizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

public class CreateImage {
    private DataInputStream dataInput;
    private int currentByte;
    private int indexBit;
    public CreateImage(DataInputStream dataInput){
        this.dataInput=dataInput;
        this.currentByte=0;
        this.indexBit=-1;
    }

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
