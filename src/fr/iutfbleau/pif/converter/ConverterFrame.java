package fr.iutfbleau.pif.converter;

public class ConverterFrame extends JFrame {
	
	private BufferedImage bufferedImage;
	private FrequencyTable frequencyTable;

	public ConverterFrame(BufferedImage bufferedImage, FrequencyTable frequencyTable) {
		this.bufferedImage = bufferedImage;
		this.frequencyTable = frequencyTable;
	}
}