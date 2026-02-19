package fr.iutfbleau.pif.visualizer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
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
        }catch(FileNotFoundException e){
            System.out.println("No file selected.");
        }
        headerTreatment();
        tablesRGB();
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
        }catch(IOException e){
            System.err.println("The first byte cannot be read");
        }
        for (int i=0 ; i<256 ; i++){
            int lenght = bodyByte[i] & 0xFF;
            if (lenght != 0){
                listRed.add(new DataColor(i, lenght));
            }
        }
        System.err.println();
        for (int i=0 ; i<256 ; i++){
            int lenght = bodyByte[256+i] & 0xFF;
            if (lenght != 0){
                listGreen.add(new DataColor(i, lenght));
            }
        }
        System.err.println();
        for (int i=0 ; i<256 ; i++){
            int lenght = bodyByte[512+i] & 0xFF;
            if (lenght != 0){
                listBlue.add(new DataColor(i, lenght));
            }
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
    }



    // public int[] tablesRGB(){ 
    //     try{
    //         dataInput.read(bodyByte,0,800);
    //     }catch(IOException e){
    //         System.err.println("The first byte cannot be read");
    //     }
    //     for (int i=0 ; i<256 ; i++){
    //         tabRed[i]=bodyByte[i];
    //     }
    //     System.err.println();
    //     for (int i=0 ; i<256 ; i++){
    //         tabGreen[i]=bodyByte[256+i];
    //         System.err.println(tabGreen[i]);
    //     }
    //     System.err.println();
    //     for (int i=0 ; i<256 ; i++){
    //         tabBlue[i]=bodyByte[512+i];
    //     }
    //     return tabBlue;
    // }
}
