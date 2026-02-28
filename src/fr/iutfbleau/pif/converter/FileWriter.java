package fr.iutfbleau.pif.converter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriter{
  static void writeFile(String name, 
      HashMap<Integer, Code> redtable, 
      HashMap<Integer, Code> greentable, 
      HashMap<Integer, Code> bluetable,
      int height,
      int width){

    short headheight = Integer.shortValue(height);
    short headwidth = Integer.shortValue(width);



    try {
      FileWriter myWriter = new FileWriter();
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();  // must close manually
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
