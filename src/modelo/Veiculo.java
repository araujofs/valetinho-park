package modelo;

import java.util.ArrayList;

public class Veiculo {
  private String placa;
  private ArrayList<Bilhete> bilhetes = new ArrayList<>();
  
  public Veiculo(String placa) {
    this.placa = placa;
  }

  public void addBilhete(Bilhete bilhete) {
    this.bilhetes.add(bilhete);
  }

  public void setBilhetes(ArrayList<Bilhete> bilhetes) {
    this.bilhetes = bilhetes;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public ArrayList<Bilhete> getBilhetes() {
    return bilhetes;
  }

  @Override
  public String toString() {
    return "[placa=" + placa + "]";
  }
}
