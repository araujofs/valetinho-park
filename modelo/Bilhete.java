package modelo;

import java.util.Date;

class Bilhete {
  static Integer autoId = 1;
  private Estacionamento estacionamento;
  private Date data;
  private Double valorpago;
  private Integer id;
  
  Bilhete(Estacionamento estacionamento, Date data, Double valorpago) {
    this.data = data;
    this.estacionamento = estacionamento;
    this.valorpago = valorpago;
    this.id = autoId;
    autoId++;
  }

  public Estacionamento getEstacionamento() {
    return estacionamento;
  }

  public void setEstacionamento(Estacionamento estacionamento) {
    this.estacionamento = estacionamento;
  }

  public String getData() {
    return data.toString();
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Double getValorpago() {
    return valorpago;
  }

  public void setValorpago(Double valorpago) {
    this.valorpago = valorpago;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}