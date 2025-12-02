package modelo;

import java.util.ArrayList;

public class Estacionamento implements Identificavel {
  private int id;
  private ArrayList<Bilhete> bilhetes = new ArrayList<>();
  private Localizacao localizacao;
  private String nome;  

  public Estacionamento(Localizacao localizacao, String nome) {
    this.localizacao = localizacao;
    this.nome = nome;
  }

  public void addBilhete(Bilhete bilhete) throws Exception {
    if (!bilhete.getEstacionamento().equals(this))
      throw new Exception("Bilhete não pertence a esse estacionamento");
    this.bilhetes.add(bilhete);
  }

  public Integer getId() {
    return id;
  }

  public ArrayList<Bilhete> getBilhetes() {
    return bilhetes;
  }

  public void setBilhetes(ArrayList<Bilhete> bilhetes) {
    this.bilhetes = bilhetes;
  }

  public Localizacao getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(Localizacao localizacao) {
    this.localizacao = localizacao;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "[id=" + id + ", localizacao=" + localizacao + "]";
  }
}