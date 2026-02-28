package fr.iutfbleau.pif.converter;

public class Code{
  private int bits;
  private int length;

  public Code(int bits, int length){
    this.bits = bits;
    this.length = length;
  }

  public int getBits(){
    return this.bits;
  }

  public int getLength(){
    return this.length;
  }

  @Override
  public String toString() {
    return Integer.toBinaryString(this.bits);
  }
}
