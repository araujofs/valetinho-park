package br.com.valetinho.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "bilhete_20241370031")
@Entity
public class Bilhete implements Identificavel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private LocalDate data;

  private LocalTime hora;

  private Double valorpago;

  @ManyToOne(optional = false)
  @JoinColumn(name = "estacionamento_id", nullable = false)
  private Estacionamento estacionamento;

  @ManyToOne(optional = false)
  @JoinColumn(name = "veiculo_id", nullable = false)
  private Veiculo veiculo;

  public Bilhete(Estacionamento estacionamento, Veiculo veiculo, LocalDate data, LocalTime hora, Double valorpago) {
    this.data = data;
    this.hora = hora;
    this.estacionamento = estacionamento;
    this.veiculo = veiculo;
    this.valorpago = valorpago;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public void setValorpago(Double valorpago) {
    this.valorpago = valorpago;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veic) {
    this.veiculo = veic;
  }

  public Estacionamento getEstacionamento() {
    return estacionamento;
  }

  public void setEstacionamento(Estacionamento est) {
    this.estacionamento = est;
  }

  public LocalDate getData() {
    return data;
  }

  public String getDataFormatada() {
    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.data);
  }

  public Double getValorpago() {
    return valorpago;
  }

  public Integer getId() {
    return id;
  }

  public LocalTime getHora() {
    return hora;
  }

  public void setHora(LocalTime hora) {
    this.hora = hora;
  }

  @Override
  public String toString() {
    return "[id=" + id + ", data=" + getDataFormatada() + ", valorpago=" + valorpago + ", estacionamentoID="
        + estacionamento.getId() + ", veiculoPlaca=" + veiculo.getPlaca() + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bilhete other = (Bilhete) obj;
    if (id != other.getId())
      return false;
    return true;
  }
}