package modelo;

public class Bilhete implements Identificavel {
  private Estacionamento estacionamento;
  private Veiculo veiculo;
  private String data;
  private Double valorpago;
  private int id;
  
  public Bilhete(Estacionamento estacionamento, Veiculo veiculo, String data, Double valorpago) {
    this.data = data;
    System.out.println(data);
    this.estacionamento = estacionamento;
    this.veiculo = veiculo;
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
    return "[id=" + id + ", data=" + data + ", valorpago=" + valorpago + ", estacionamentoID=" + estacionamento.getId() + ", veiculoPlaca=" + veiculo.getPlaca() + "]";
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