package fr.iutfbleau.pif.visualizer;

public class DataColor implements Comparable<DataColor>{
    public int intensity;
    public int lenght;
    public String code;

    public DataColor(int intensity, int lenght){
        this.intensity=intensity;
        this.lenght=lenght;
        this.code="";
    }

    public int getIntensity(){
        return this.intensity;
    }

    public int getLenght(){
        return this.lenght;
    }

    public void setCode(String s){
        this.code=s;
    }

    @Override
    public int compareTo(DataColor element){

        if (this.lenght != element.getLenght()){
            if (this.lenght>element.getLenght()){
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

    @Override
    public String toString(){
        return "intensity : " + intensity + ", lenght : " + lenght + ", code : "+ code;
    }
}
