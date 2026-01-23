package br.com.valetinho.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "estacionamento_20241370031")
@Entity
public class Estacionamento implements Identificavel {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(mappedBy = "estacionamento", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Bilhete> bilhetes = new ArrayList<>();

  @Embedded
  private Localizacao localizacao;

  private String nome;

  public Estacionamento(Localizacao localizacao, String nome) {
    this.localizacao = localizacao;
    this.nome = nome;
  }

  public void addBilhete(Bilhete bilhete) {
    bilhete.setEstacionamento(this);
    this.bilhetes.add(bilhete);
  }

  public void removeBilhete(Bilhete bilhete) {
    bilhete.setEstacionamento(null);
    this.bilhetes.remove(bilhete);
  }

  public Integer getId() {
    return id;
  }

  public List<Bilhete> getBilhetes() {
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