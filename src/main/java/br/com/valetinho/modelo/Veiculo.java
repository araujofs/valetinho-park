package br.com.valetinho.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "veiculo_20241370031")
@Entity
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  private String placa;

  @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Bilhete> bilhetes = new ArrayList<>();

  public Veiculo() {
  }

  public Veiculo(String placa) {
    this.placa = placa;
  }

  public void addBilhete(Bilhete bilhete) {
    bilhete.setVeiculo(this);
    this.bilhetes.add(bilhete);
  }

  public void removeBilhete(Bilhete bilhete) {
    bilhete.setVeiculo(null);
    this.bilhetes.remove(bilhete);
  }

  public void setBilhetes(List<Bilhete> bilhetes) {
    this.bilhetes = bilhetes;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public List<Bilhete> getBilhetes() {
    return bilhetes;
  }

  @Override
  public String toString() {
    return "[placa=" + placa + "]";
  }
}
