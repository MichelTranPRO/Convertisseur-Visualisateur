package fr.iutfbleau.pif.visualizer;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTreatment {
    
    private File file;
    private DataInputStream dataInput;
    public short[] headerShort;
    public byte[] bodyByte;
    public int[] tabRed;
    public int[] tabGreen;
    public int[] tabBlue;
    public FileTreatment (File file){
        this.file=file;
        this.headerShort = new short[2];
        this.bodyByte = new byte[1024];
        this.tabRed = new int[256];
        this.tabGreen = new int[256];
        this.tabBlue = new int[256];
        try{
            this.dataInput = new DataInputStream(new FileInputStream(this.file));            
        }catch(FileNotFoundException e){
            System.out.println("No file selected.");
        }
        headerTreatment();
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

    public int[] readBody(){
        try{
            dataInput.read(bodyByte,0,800);
        }catch(IOException e){
            System.err.println("The first byte cannot be read");
        }
        for (int i=0 ; i<256 ; i++){
            tabRed[i]=bodyByte[i];
            // System.err.print(tabRed[i]);
        }
        System.err.println();
        for (int i=0 ; i<256 ; i++){
            tabGreen[i]=bodyByte[256+i];
            System.err.println(tabGreen[i]);
        }
        System.err.println();
        for (int i=0 ; i<256 ; i++){
            tabBlue[i]=bodyByte[512+i];
            // System.err.print(tabBlue[i]);
        }

        return tabGreen;
    }

}
