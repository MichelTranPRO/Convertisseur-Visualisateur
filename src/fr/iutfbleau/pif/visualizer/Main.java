package fr.iutfbleau.pif.visualizer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * La classe <code>Main</code> est le point d'entrée de l'application.
 * 
 * @version 1.1
 * @author Emmanuel Srivastava-Tiamzon, Rayan Bisson et Michel Tran
 */

public class Main {

    /**
     * Constructeur par défaut de la classe Main.
     */
    public Main() {
    }
    /**
     * Méthode principale qui lance l'application.
     * @param args les arguments de la ligne de commande
     */
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
        try{
            FileTreatment treatment = new FileTreatment(file);
            CreateImage imgBuilder = new CreateImage(treatment.getDataInput());
            BufferedImage img = imgBuilder.buildImage(treatment.getWidth()
                                                    ,treatment.getHeigth()
                                                    ,treatment.getHashRed()
                                                    ,treatment.getHashGreen()
                                                    ,treatment.getHashBlue());

            JFrame frame = new JFrame("Visualiseur PIF");
            

            ImageVisualizer visualizer = new ImageVisualizer(img);
            frame.add(visualizer);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.min(img.getWidth(), (int)(screen.width * 0.95));
            int h = Math.min(img.getHeight(), (int)(screen.height * 0.95));

            visualizer.setPreferredSize(new Dimension(w, h));

            frame.pack();
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
