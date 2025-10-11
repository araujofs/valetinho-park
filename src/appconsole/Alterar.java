package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import repositorio.Repositorio;

public class Alterar {
  public Alterar() {
    try {
      Estacionamento est = Repositorio.lerId(1, Estacionamento.class);

      System.out.println("Estacionamento: " + est + " com os bilhetes: " + est.getListaBilhete());
      Bilhete bil = est.getListaBilhete().removeFirst();


      System.out.println("Bilhete: " + bil + " com estacionamento: " + bil.getEstacionamento());
      bil.setEstacionamento(null);

      Repositorio.gravar(est);
      Repositorio.gravar(bil);

      
      System.out.println("Estacionamento: " + est + " com os bilhetes: " + est.getListaBilhete());
      System.out.println("Bilhete: " + bil + " com estacionamento: " + bil.getEstacionamento());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    new Alterar();
  }
}
