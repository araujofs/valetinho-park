package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import repositorio.Repositorio;

public class Alterar {
  public Alterar() {
    try {
      Estacionamento est = Repositorio.lerId(1, Estacionamento.class);

      Bilhete bil = est.getListaBilhete().removeFirst();

      bil.setEstacionamento(null);

      Repositorio.gravar(est);
      Repositorio.gravar(bil);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    new Alterar();
  }
}
