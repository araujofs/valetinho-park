package appconsole;

import java.util.List;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import repositorio.Repositorio;

public class Apagar {

  public Apagar() {
    try {
      System.out.println("------------------------ removendo veiculo -----------------------");
      Veiculo veic = Repositorio.lerPlaca("JKL-3456");
      List<Bilhete> bilhetes = veic.getListaBilhete();
      System.out.println("Veiculo: " + veic);
      System.out.println();

      System.out.println("------------------------ removendo bilhetes dos respectivos estacionamentos -----------------------");
      for (Bilhete bil : bilhetes) {
        System.out.println("bilhete atual");
        System.out.println("Bilhete: " + bil);
        System.out.println();

        Estacionamento est = bil.getEstacionamento();
        System.out.println("------------------ estacionamento velho ---------------------");
        System.out.println("Estacionamento: " + est + " com bilhetes: " + est.getListaBilhete());
        System.out.println();

        est.getListaBilhete().remove(bil);
        Repositorio.gravar(est);

        est = Repositorio.lerId(est.getId(), est.getClass());
        System.out.println("------------------ estacionamento atualizado ---------------------");
        System.out.println("Estacionamento: " + est + " com bilhetes: " + est.getListaBilhete());
        System.out.println("------------------------------------------------------------------");
        System.out.println();
      }

      System.out.println();

      Repositorio.deletar(veic);
      
      System.out.println("------------------- tentando procurar pelos veiculo e bilhetes deletados -----------------");

      veic = Repositorio.lerPlaca("JKL-3456");
      System.out.println("Veiculo: " + veic);
      System.out.println();

      for (Bilhete bil : bilhetes) {
        System.out.println("bilhete atual");
        System.out.println("Bilhete: " + bil);
        System.out.println("resultado: " + Repositorio.lerId(bil.getId(), bil.getClass()));
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("--->" + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Apagar();
  }
}
