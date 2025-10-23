package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import repositorio.Repositorio;

public class Alterar {
  public Alterar() {
    try {
      Estacionamento est = Repositorio.lerId(1, Estacionamento.class);
      System.out.println("---------------------------- antes da alteração -------------------------------");
      System.out.println("Estacionamento: " + est + " com os bilhetes: " + est.getListaBilhete());

      Bilhete bil = est.getListaBilhete().removeLast();
      Veiculo veic = bil.getVeiculo();
      
      Repositorio.gravar(est);

      System.out.println("Veiculo: " + veic + " com os bilhetes: " + veic.getListaBilhete());
      System.out.println("Bilhete: " + bil);
      System.out.println();

      veic.getListaBilhete().remove(bil);
      Repositorio.gravar(veic);

      Repositorio.deletar(bil);

      veic = Repositorio.lerPlaca(veic.getPlaca());
      est = Repositorio.lerId(est.getId(), est.getClass());

      System.out.println("---------------------------- depois da alteração -------------------------------");
      System.out.println("Estacionamento: " + est + " com os bilhetes: " + est.getListaBilhete());
      System.out.println("Veiculo: " + veic + " com os bilhetes: " + veic.getListaBilhete());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    new Alterar();
  }
}
