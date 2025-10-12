package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import repositorio.Repositorio;

public class Apagar {

  public Apagar() {
    try {
      Veiculo veic = Repositorio.lerPlaca("JKL-3456");

      Bilhete bil = veic.getListaBilhete().getFirst();

      Estacionamento est = bil.getEstacionamento();

      System.out.println("Veiculo: " + veic);
      System.out.println("Estacionamento: " + est);
      System.out.println("Bilhete: " + bil);
      Repositorio.deletar(veic);
      Repositorio.deletar(est);

      bil.setEstacionamento(null);
      
      System.out.println("Estacionamento: " + bil.getEstacionamento());
      Repositorio.gravar(bil);
      System.out.println("Bilhete: " + bil);

      // fica orfão
      Repositorio.deletar(bil);      

      bil = Repositorio.lerId(bil.getId(), Bilhete.class);

      System.out.println("Bilhete: " + bil);
    } catch (Exception e) {
      System.out.println("--->" + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Apagar();
  }
}
