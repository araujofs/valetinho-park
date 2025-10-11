package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bilhete implements Identificavel {
  static Integer autoId = 1;
  private Estacionamento estacionamento;
  private Date data;
  private Double valorpago;
  private Integer id;
  
  public Bilhete(Estacionamento estacionamento, Date data, Double valorpago) {
    this.data = data;
    this.estacionamento = estacionamento;
    this.valorpago = valorpago;
    this.id = autoId;
    autoId++;
  }

  public Estacionamento getEstacionamento() {
    return estacionamento;
  }

  public void setEstacionamento(Estacionamento est) {
    this.estacionamento = est;
  }

  public Date getData() {
    return data;
  }

  public String getDataFormatada() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    return sdf.format(data);
  }

  public Double getValorpago() {
    return valorpago;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "[id=" + id + ", data=" + getDataFormatada() + ", valorpago=" + valorpago + "]";
  }
}