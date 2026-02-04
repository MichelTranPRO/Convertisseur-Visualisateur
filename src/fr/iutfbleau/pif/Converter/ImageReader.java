import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;

public class ImageReader {
	
	private File fileInput;
	private BufferedImage image;

	public ImgReader(File fileInput) {
		this.fileInput = fileInput;
		readFileInput(this.fileInput);
	}

	public void readFileInput(File file) {
		try {
			this.image = ImageIO.read(file);
		} catch(IOException e) {
			System.err.println("Error while reading the input file : "+e);
		}
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
		readFileInput(this.fileInput);
	}

	public File getFileInput() {
		return this.fileInput;
	}

	public String toString() {
		String aRenvoyer = "";
		if(this.fileInput == null) {

		} else {
			this.image.getWidth();
		}

		return aRenvoyer;
	}

	public verifyInputLength(String[] input) {
		if(input.length > 3) {
			
		} else {
			openJFileChooser();
		}
	}

	public static void main(String[] args) {
		File file = new File(args[0]);
		ImageReader myImage = new ImageReader(file);
	}
}