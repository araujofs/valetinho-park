package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import repositorio.Repositorio;

public class Listar {

  public Listar() {
    try {
      System.out.println("\n---------listagem de estacionamentos-----");
      for (Estacionamento e : Repositorio.lerTodos(Estacionamento.class)) {
        System.out.println(e);
        for (Bilhete b : e.getListaBilhete()) {
          System.out.println("   " + b);
        }
      }

      System.out.println("\n---------listagem de veiculos-----");
      for (Veiculo v : Repositorio.lerTodos(Veiculo.class)) {
        System.out.println(v);
        for (Bilhete b : v.getListaBilhete()) {
          System.out.println("   " + b);
        }
      }

      System.out.println("\n---------listagem de bilhetes-----");
      for (Bilhete b : Repositorio.lerTodos(Bilhete.class)) {
        System.out.println(b);
      }
    } catch (Exception e) {
      System.out.println("--->" + e);
    }
  }

  public static void main(String[] args) {
    new Listar();
  }
}
