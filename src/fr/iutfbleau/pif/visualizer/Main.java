package fr.iutfbleau.pif.visualizer;

import java.io.File;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) {
        File file = null;

        if(args.length == 1) {
            file = new File(args[0]);
        }else{
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) { 
                file = chooser.getSelectedFile();
            } else {
                System.out.println("No file selected.");
                System.exit(1);
            }
        }

        FileTreatment treatment = new FileTreatment(file);
        Frame frame = new Frame(treatment.getDimensionFile());
        
    }
}
