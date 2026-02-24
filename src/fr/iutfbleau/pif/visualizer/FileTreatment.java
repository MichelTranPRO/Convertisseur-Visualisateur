package fr.iutfbleau.pif.visualizer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTreatment {
    
    private File file;
    private DataInputStream dataInput;
    
    private short[] headerShort;
    private byte[] bodyByte;

    private List<DataColor> listRed;
    private List<DataColor> listGreen;
    private List<DataColor> listBlue;

    private HashMap<String, Integer> hashRed;
    private HashMap<String, Integer> hashGreen;
    private HashMap<String, Integer> hashBlue;

    
    public FileTreatment (File file){
        this.file=file;
        this.headerShort = new short[2];
        this.bodyByte = new byte[768];
        
        this.listRed = new ArrayList<DataColor>();
        this.listGreen = new ArrayList<DataColor>();
        this.listBlue = new ArrayList<DataColor>();

        this.hashRed = new HashMap<>();
        this.hashGreen = new HashMap<>();
        this.hashBlue = new HashMap<>();

        try{
            this.dataInput = new DataInputStream(new FileInputStream(this.file));
            headerTreatment();
            tablesRGB();
            filterRGB();
            finalData();
        }catch(FileNotFoundException e){
            System.out.println("No file selected.");
        }
        
    }

    private void headerTreatment(){
        try{
            headerShort[0] = dataInput.readShort(); // largeur
            headerShort[1] = dataInput.readShort(); // hauteur
        }catch(IOException e){
            System.err.println("The stream has been closed");
        }
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

    private void filterRGB(){
        Collections.sort(listRed);
        Collections.sort(listGreen);
        Collections.sort(listBlue);
    }

    private void finalData(){
        canonicalCodes(listRed);
        for (DataColor element : listRed){
            hashRed.put(element.getCode(), element.getIntensity());
        }
        canonicalCodes(listGreen);
        for (DataColor element : listGreen){
            hashGreen.put(element.getCode(), element.getIntensity());
        }
        canonicalCodes(listBlue);
        for (DataColor element : listBlue){
            hashBlue.put(element.getCode(), element.getIntensity());
        }
    }

    private void canonicalCodes(List<DataColor> list) {

        int cumulCode = 0;
        int currLenght = list.get(0).getLenght();

        for (DataColor element : list) {
            // si la lenght augmente, on allonge le code binaire
            while (currLenght < element.getLenght()) {
                cumulCode = cumulCode << 1;
                currLenght++;
            }

            String binary = Integer.toBinaryString(cumulCode);
            while (binary.length() < element.getLenght()) {
                binary = "0" + binary; // on comble le vide pour obtenir une bonne longueur
            }
            element.setCode(binary);
            cumulCode++;
        }
    }

    public int getHeigth(){
        return (int)this.headerShort[1];
    }

    public int getWidth(){
        return (int)this.headerShort[0];
    }

    public HashMap<String,Integer> getHashRed(){
        return this.hashRed;
    }
    
    public HashMap<String,Integer> getHashGreen(){
        return this.hashGreen;
    }
    
    public HashMap<String,Integer> getHashBlue(){
        return this.hashBlue;
    }

    public DataInputStream getDataInput(){
        return this.dataInput;
    }
}
