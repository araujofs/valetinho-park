package modelo;

import java.util.ArrayList;

public class Veiculo {
  private String placa;
  private ArrayList<Bilhete> listaBilhete = new ArrayList<>();
  
  public Veiculo(String placa) {
    this.placa = placa;
  }

  public void addBilhete(Bilhete bilhete) {
    this.listaBilhete.add(bilhete);
  }

  public String getPlaca() {
    return placa;
  }

  public ArrayList<Bilhete> getListaBilhete() {
    return listaBilhete;
  }

  @Override
  public String toString() {
    return "[placa=" + placa + "]";
  }
}
