package src.modelo;

import java.util.ArrayList;

public class Veiculo {
  private String placa;
  private ArrayList<Bilhete> listaBilhete = new ArrayList<>();
  
  public Veiculo(String placa) {
    this.placa = placa;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public ArrayList<Bilhete> getListaBilhete() {
    return listaBilhete;
  }

  public void setListaBilhete(ArrayList<Bilhete> listaBilhete) {
    this.listaBilhete = listaBilhete;
  }  
}
