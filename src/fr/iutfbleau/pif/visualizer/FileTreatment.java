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

/**
 * The <code>FileTreatment</code> class is responsible for reading and processing the data from a file. 
 * It extracts the header and the body of the file, organizes the color data into lists and hash maps, and provides methods to access this data.
 * The class handles the file input stream and ensures that the data is correctly read and processed so that we can use later in the application.
 */
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

    /**
     * Constructor for the <code>FileTreatment</code> class. It initializes the file and sets up the header and body arrays, as well as the lists and hash maps for the color data. 
     * @param file the file to be processed
     */
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

    /**
     * This method reads the header as short of the file and stores the width and height in the headerShort array. It handles any IOException that may occur during the reading process.
     */
    private void headerTreatment(){
        try{
            headerShort[0] = dataInput.readShort(); // largeur
            headerShort[1] = dataInput.readShort(); // hauteur
        }catch(IOException e){
            System.err.println("The stream has been closed");
        }
    }

    /**
     * This method reads the body of the file, byte by byte, and organizes the color data into three separate lists for red, green, and blue. 
     * It checks the length of each color data and only adds it to the respective list if the length is not zero. 
     * It handles any IOException that may occur during the reading process.
     */
    private void tablesRGB(){ 
        try{
            dataInput.read(bodyByte,0,768);
            for (int i=0 ; i<256 ; i++){
                int length = bodyByte[i];
                if (length != 0){
                    listRed.add(new DataColor(i, length));
                }
            }
            for (int i=0 ; i<256 ; i++){
                int length = bodyByte[256+i];
                if (length != 0){
                    listGreen.add(new DataColor(i, length));
                }
            }
            for (int i=0 ; i<256 ; i++){
                int length = bodyByte[512+i];
                if (length != 0){
                    listBlue.add(new DataColor(i, length));
                }
            }
        }catch(IOException e){
            System.err.println("The first byte cannot be read : "+e);
        }
    }

    /**
     * This method sorts the lists of red, green, and blue color data based on their intensity. 
     * It uses the Collections.sort() method to sort each list.
     */
    private void filterRGB(){
        Collections.sort(listRed);
        Collections.sort(listGreen);
        Collections.sort(listBlue);
    }

    /**
     * This method generates the canonical codes for each color data in the lists and puts them into the respective hash maps. 
     * It calls the <code>canonicalCodes</code> method to make the canonical codes.
     */
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

    /**
     * This method generates the canonical codes for a list of color data that was put into its parameters.
     * It iterates through the list and assigns a binary code to each color data based on its length.
     * @param list the list of color data for which the canonical codes will be generated.
     */
    private void canonicalCodes(List<DataColor> list) {

        int cumulCode = 0;
        int currLength = list.get(0).getLength();

        for (DataColor element : list) {
            // si la length augmente, on allonge le code binaire
            while (currLength < element.getLength()) {
                cumulCode = cumulCode << 1;
                currLength++;
            }

            String binary = Integer.toBinaryString(cumulCode);
            while (binary.length() < element.getLength()) {
                binary = "0" + binary; // on comble le vide pour obtenir une bonne longueur
            }
            element.setCode(binary);
            cumulCode++;
        }
    }

    /**
     * Getter for the height of the header.
     * @return the height of the image as an integer
     */
    public int getHeigth(){
        return (int)this.headerShort[1];
    }

    /**
     * Getter for the width of the header.
     * @return the width of the image as an integer
     */
    public int getWidth(){
        return (int)this.headerShort[0];
    }

    /**
     * Getter for the HashMap of the red color data.
     * @return the HashMap containing the canonical codes and the intensity values for the red color data
     */
    public HashMap<String,Integer> getHashRed(){
        return this.hashRed;
    }
    
    /**
     * Getter for the HashMap of the green color data.
     * @return the HashMap containing the canonical codes and the intensity values for the green color data
     */
    public HashMap<String,Integer> getHashGreen(){
        return this.hashGreen;
    }
    
    /**
     * Getter for the HashMap of the blue color data.
     * @return the HashMap containing the canonical codes and the intensity values for the blue color data
     */
    public HashMap<String,Integer> getHashBlue(){
        return this.hashBlue;
    }

    /**
     * Getter for the DataInputStream of the file.
     * @return the DataInputStream used to read the file data.
     */
    public DataInputStream getDataInput(){
        return this.dataInput;
    }
}
