package modelo;

import java.util.ArrayList;

public class Estacionamento implements Identificavel {
  static Integer autoId = 1;
  private Integer id;
  private ArrayList<Bilhete> listaBilhete = new ArrayList<>();
  private Localizacao localizacao;

  public Estacionamento(Localizacao localizacao) {
    this.localizacao = localizacao;
    this.id = autoId;
    autoId++;
  }

  public void addBilhete(Bilhete bilhete) throws Exception {
    if (!bilhete.getEstacionamento().equals(this))
      throw new Exception("Bilhete não pertence a esse estacionamento");
    this.listaBilhete.add(bilhete);
  }

  public Integer getId() {
    return id;
  }

  public ArrayList<Bilhete> getListaBilhete() {
    return listaBilhete;
  }

  public Localizacao getLocalizacao() {
    return localizacao;
  }

  @Override
  public String toString() {
    return "[id=" + id + ", localizacao=" + localizacao + "]";
  }
}