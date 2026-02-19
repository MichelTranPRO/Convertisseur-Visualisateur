package fr.iutfbleau.pif.visualizer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTreatment {
    
    private File file;
    private DataInputStream dataInput;
    
    public short[] headerShort;
    private byte[] bodyByte;
    
    private int[] tabRed;
    private int[] tabGreen;
    private int[] tabBlue;

    private List<DataColor> listRed;
    private List<DataColor> listGreen;
    private List<DataColor> listBlue;
    
    public FileTreatment (File file){
        this.file=file;
        this.headerShort = new short[2];
        this.bodyByte = new byte[1024];
        
        this.tabRed = new int[256];
        this.tabGreen = new int[256];
        this.tabBlue = new int[256];
        
        this.listRed = new ArrayList<DataColor>();
        this.listGreen = new ArrayList<DataColor>();
        this.listBlue = new ArrayList<DataColor>();

        try{
            this.dataInput = new DataInputStream(new FileInputStream(this.file));
            headerTreatment();
            tablesRGB();
        }catch(FileNotFoundException e){
            System.out.println("No file selected.");
        }
        
    }

    private void headerTreatment(){
        try{
            headerShort[0] = dataInput.readShort();
            headerShort[1] = dataInput.readShort();
        }catch(IOException e){
            System.err.println("The stream has been closed");
        }
        
    }
    public Dimension getDimensionFile(){
        return new Dimension((int)headerShort[0],(int)headerShort[1]);
    }

    private void tablesRGB(){ 
        try{
            dataInput.read(bodyByte,0,768);
            for (int i=0 ; i<256 ; i++){
                int lenght = bodyByte[i];
                if (lenght != 0){
                    listRed.add(new DataColor(i, lenght));
                }
            }
            for (int i=0 ; i<256 ; i++){
                int lenght = bodyByte[256+i];
                if (lenght != 0){
                    listGreen.add(new DataColor(i, lenght));
                }
            }
            for (int i=0 ; i<256 ; i++){
                int lenght = bodyByte[512+i];
                if (lenght != 0){
                    listBlue.add(new DataColor(i, lenght));
                }
            }
        }catch(IOException e){
            System.err.println("The first byte cannot be read");
        }
    }

    public void print(){
        for (DataColor e : listRed){
            System.out.println(e);
        }
    }

    public void filterRGB(){
        Collections.sort(listRed);
        Collections.sort(listGreen);
        Collections.sort(listBlue);
        canonicalCodes(listRed);
    }

    private void canonicalCodes(List<DataColor> list) {
        int cumulCode = 0;
        int currLenght = list.get(0).getLenght();

        for (DataColor dc : list) {

            // si la lenght augmente, on allonge le code binaire
            while (currLenght < dc.getLenght()) {
                cumulCode = cumulCode << 1;
                currLenght++;
            }

            String binary = Integer.toBinaryString(cumulCode);
            while (binary.length() < dc.getLenght()) {
                binary = "0" + binary; // on comble le vide pour obtenir une bonne longueur
            }
            dc.setCode(binary);
            cumulCode++;
        }
    }
}
