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
    public short[] headerByte;
    public FileTreatment (File file){
        this.file=file;
        headerByte = new short[2];
        try{
            this.dataInput = new DataInputStream(new FileInputStream(this.file));            
        }catch(FileNotFoundException e){
            System.out.println("No file selected.");
        }
        headerTreatment();
    }

    private void headerTreatment(){
        try{
            headerByte[0] = dataInput.readShort();
            headerByte[1] = dataInput.readShort();
        }catch(IOException e){
            System.err.println("The stream has been closed");
        }
        
    }
    public Dimension getDimensionFile(){
        return new Dimension((int)headerByte[0],(int)headerByte[1]);
    }
}
