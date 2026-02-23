package fr.iutfbleau.pif.visualizer;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
        
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
        try{
            FileTreatment treatment = new FileTreatment(file);
            CreateImage imgBuilder = new CreateImage(treatment.getDataInput());
            BufferedImage img = imgBuilder.buildImage(treatment.getWidth()
                                                    ,treatment.getHeigth()
                                                    ,treatment.getHashRed()
                                                    ,treatment.getHashGreen()
                                                    ,treatment.getHashBlue());

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel labelImg = new JLabel(new ImageIcon(img));
            frame.add(labelImg); 

            frame.pack(); 
            frame.setLocationRelativeTo(null);  
            frame.setVisible(true);

        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
