package modelo;

public class Bilhete implements Identificavel {
  private Estacionamento estacionamento;
  private String data;
  private Double valorpago;
  private int id;
  
  public Bilhete(Estacionamento estacionamento, String data, Double valorpago) {
    this.data = data;
    System.out.println(data);
    this.estacionamento = estacionamento;
    this.valorpago = valorpago;
  }

  public Estacionamento getEstacionamento() {
    return estacionamento;
  }

  public void setEstacionamento(Estacionamento est) {
    this.estacionamento = est;
  }

  public String getData() {
    return data;
  }

  public Double getValorpago() {
    return valorpago;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "[id=" + id + ", data=" + data + ", valorpago=" + valorpago + "]";
  }
}