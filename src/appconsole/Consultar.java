package appconsole;

import repositorio.Repositorio;

public class Consultar {

  public Consultar() {
    try {
      // bilhetes com valorpago > 10 reais
      System.out.println("--------- bilhetes com valor pago > 10");
      System.out.println(Repositorio.lerBilheteMaiorValorPago(10.0));
      System.out.println();

      // veiculos estacionados na data x no estacionamento y
      System.out.println("--------- veiculos estacionados na data x no estacionamento y");
      System.out.println(Repositorio.lerVeiculoEstacionadoData(1, "13/10/2025"));
      System.out.println();

      // veiculos com mais de n bilhetes
      System.out.println("--------- veiculos com mais de n bilhetes");
      System.out.println(Repositorio.lerVeiculoMaisBilhetes(1));
      System.out.println();
    } catch (Exception e) {
      System.out.println("--->" + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Consultar();
  }
}
