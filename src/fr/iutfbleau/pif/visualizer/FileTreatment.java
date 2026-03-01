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
 * La classe <code>FileTreatment</code> est responsable pour lire et prendre en compte les données du fichier donné en paramêtre de celui-ci.
 * Il extrait le header et le body du fichier, organise les données des couleurs en listes et en hash maps, et fournit des méthodes pour accéder à ces données.
 * Cette classe s'occupe du flux d'entrée du fichier and fais en sorte à ce que ces données soient correctement lu pour pouvoir les utilier plus tard.
 * @version 1.1
 * @author Michel TRAN, Rayan BISSON, Emmanuel SRIVASTAVA-TIAMZON
 */
public class FileTreatment {
    
    /**
     * Ces attributs sont : le fichier qui est pris en compte, le flux d'entrée de données pour lire les données du fichier.
     */
    private File file;
    private DataInputStream dataInput;
    
    /**
     * L'attribut headerShort est un tableau qui stocke la largeur et la hauteur du header. 
     * Tandis que le tableau bodyByte est utilisé pour stocké les données des couleurs rouge, verte et bleu.
     */
    private short[] headerShort;
    private byte[] bodyByte;

    /**
     * Ces attributs sont des listes pour les données de couleur rouge, vert et bleu, elles sont utilisées pour organiser les données de couleur basé sur leur intensité et leur longueur.
     */
    private List<DataColor> listRed;
    private List<DataColor> listGreen;
    private List<DataColor> listBlue;

    /**
     * Ces attributs sont les Hash Maps pour les données de couleur rouge, vert, et bleu, elles sont utilisées pour stockées le code canonique en tant que clé et la valeur de l'intensité en tant que leur valeur respectives. 
     * The hash maps for red, green, and blue color data are used to store the canonical codes as keys and their corresponding intensity values as values.
     */
    private HashMap<String, Integer> hashRed;
    private HashMap<String, Integer> hashGreen;
    private HashMap<String, Integer> hashBlue;

    /**
     * Ceci est le constructeur de la classe <code>FileTreatment</code>. Il initialise le fichier et met en place des tableaux pour l'entête et le corps de celui-ci,
     * ainsi que les listes et hash map pour les données de couleur.
     * @param file le fichier qui sera traité.
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
     * Cette méthode lit l'entête en tant que short du fichier et stock la largeur et la hauteur dans l'attribut tableau headerShort. Il s'occupe aussi toutes IOException qui risque d'arriver durant le process de lecture.
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
     * Cette méthode lit le corps du fichier bit par bit et organise les données des couleurs en trois listes séparées, rouge, verte et bleu.
     * Il vérifie la longueur de chaque donnée de couleur et l'ajoute seulement à sa liste respective si sa longueur n'est pas zéro. 
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
     * Cette méthode trie les listes de données de couleur basés sur leur intensités. 
     * Elle utilise la méthode Collections.sort() pour trier chaque liste. 
     */
    private void filterRGB(){
        Collections.sort(listRed);
        Collections.sort(listGreen);
        Collections.sort(listBlue);
    }

    /**
     * Cette méthode génère le code canonique de chaque donnée de couleur dans chacune de leurs listes respectives et les rajoute dans leur hashmaps respectives.
     * Il fait appel à la méthode <code>canonicalCodes</code> pour générer le code canonique.
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
     * Cette méthode génère le code canonique pour la liste de données de couleur qui a été mis en paramètre.
     * Il itère dans la liste et assigne un code binaire pour chaque donnée de couleur basé sur leur longueur.
     * @param list La liste de donnée de couleur sur lequel le code canonique sera généré.
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
     * Getter de la hauteur de l'entête.
     * @return la hauteur de l'entête en tant qu'int
     */
    public int getHeigth(){
        return (int)this.headerShort[1];
    }

    /**
     * Getter pour la largeur de l'entête.
     * @return la largeur de l'entête en tant qu'int
     */
    public int getWidth(){
        return (int)this.headerShort[0];
    }

    /**
     * Getter pour le HashMap de les données de couleur rouge.
     * @return Le HashMap qui contient le code canonique et la valeur de l'intensité pour les données de couleur rouge.
     */
    public HashMap<String,Integer> getHashRed(){
        return this.hashRed;
    }
    
    /**
     * Getter pour le HashMap de kes données de couleur verte.
     * @return Le HashMap qui contient le code canonique et la valeur de l'intensité pour les données de couleur verte.
     */
    public HashMap<String,Integer> getHashGreen(){
        return this.hashGreen;
    }
    
    /**
     * Getter pour le HashMap de la donnée de couleur bleu.
     * @return Le HashMap qui contient le code canonique et la valeur de l'intensité pour les données de couleur bleu.
     */
    public HashMap<String,Integer> getHashBlue(){
        return this.hashBlue;
    }

    /**
     * Getter pour le flux d'entrée de donnée du fichier.
     * @return Le flux d'entrée de donnée utilisé pour lire les données du fichier.
     */
    public DataInputStream getDataInput(){
        return this.dataInput;
    }
}
