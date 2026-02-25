package fr.iutfbleau.pif.visualizer;

/**
 * La classe <code>DataColor</code> représente les données d'une, son intensité, sa longueur et son code.
 */
public class DataColor implements Comparable<DataColor>{
    private int intensity;
    private int length;
    private String code;

    /**
     * Constructeur de la classe <code>DataColor</code>.
     * @param intensity initialise l'intensité de la couleur.
     * @param length initialise la longueur de la couleur.
     */
    public DataColor(int intensity, int length){
        this.intensity=intensity;
        this.length=length;
        this.code="";
    }

    /**
     * Getter de l'intensité de la couleur.
     * @return un entier qui represénte l'intensité de la couleur.
     */
    public int getIntensity(){
        return this.intensity;
    }

    /**
     * Getter de la longueur de la couleur.
     * @return un entier qui représente la longueur de la couleur.
     */
    public int getLength(){
        return this.length;
    }

    /**
     * Getter du code de la couleur.
     * @return un String qui représente le code de la couleur.
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Setter du code de la couleur.
     * @param s Un String qui représente le code de la couleur.
     */
    public void setCode(String s){
        this.code=s;
    }

    /**
     * Override de la méthode <code>compareTo</code> de l'interface <code>Comparable</code>. 
     * Cette méthode est utilisée pour comparer deux objets <code>DataColor</code>. La longueur de l'élément et l'intensité de celui-ci est comparé à la longueur et l'intensité de l'objet courrant.
     * @return Un entier qui représente le résultat d'une comparaison. 1 ou -1.
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
     * Override de la méthode <code>toString</code> de la classe <code>Object</code>. Cette méthode est utilisée pour montrer les données de la couleur d'une façon à pouvoir le lire.
     * @return Un entier qui représente les données de couleur. L'intensité, la longueur et le code de la couleur est affichée.
     */
    @Override
    public String toString(){
        return "intensity : " + intensity + ", length : " + length + ", code : "+ code;
    }
}
