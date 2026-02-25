package fr.iutfbleau.pif.visualizer;

/**
 * The <code>DataColor</code> class represents the data of a color, its intensity, its length and its code. 
 */
public class DataColor implements Comparable<DataColor>{
    private int intensity;
    private int length;
    private String code;

    /**
     * Constructor of the class <code>DataColor</code>.
     * @param intensity implements the intensity of the color.
     * @param length implements the length of the color.
     */
    public DataColor(int intensity, int length){
        this.intensity=intensity;
        this.length=length;
        this.code="";
    }

    /**
     * Getter of the intensity of the color.
     * @return an Integer that represents the intensity of the color.
     */
    public int getIntensity(){
        return this.intensity;
    }

    /**
     * Getter of the length of the color.
     * @return an Integer that represents the length of the color.
     */
    public int getLength(){
        return this.length;
    }

    /**
     * Getter of the code of the color.
     * @return a String that represents the code of the color.
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Setter of the code of the color.
     * @param s a String that represents the code of the color.
     */
    public void setCode(String s){
        this.code=s;
    }

    /**
     * Override of the method <code>compareTo</code> of the interface <code>Comparable</code>. 
     * This method is used here to compare two <code>DataColor</code> objects. The length of the element and the intensity of the element are compared to the length and the intensity of the current object.
     * @return an Integer that represents the result of the comparison. 1 ou -1.
     */
    @Override
    public int compareTo(DataColor element){

        if (this.length != element.getLength()){
            if (this.length>element.getLength()){
                return 1;
            }else{
                return -1;
            }
        }

        if (this.intensity > element.getIntensity()) {
            return 1;
        } else if (this.intensity < element.getIntensity()) {
            return -1;
        }

        return 0;
    }

    /**
     * Override of the method <code>toString</code> of the class <code>Object</code>. This method is used to display the data of the color in a readable way.
     * @return a String that represents the data of the color. The intensity, the length and the code of the color are displayed.
     */
    @Override
    public String toString(){
        return "intensity : " + intensity + ", length : " + length + ", code : "+ code;
    }
}
