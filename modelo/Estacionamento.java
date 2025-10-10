package modelo;

import java.util.ArrayList;

class Estacionamento {
  static Integer autoId = 1;
  private Integer id;
  private ArrayList<Bilhete> listaBilhete = new ArrayList<>();
  private Localizacao localizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ArrayList<Bilhete> getListaBilhete() {
    return listaBilhete;
  }

  public void setListaBilhete(ArrayList<Bilhete> listaBilhete) {
    this.listaBilhete = listaBilhete;
  }

  public Localizacao getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(Localizacao localizacao) {
    this.localizacao = localizacao;
  }

  Estacionamento(Localizacao localizacao) {
    this.localizacao = localizacao;
    this.id = autoId;
    autoId++;
  }
}