package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import requisito.Fachada;

public class Listar {

  public Listar() {
    try {
      System.out.println("\n========== LISTAGEM DE ESTACIONAMENTOS ==========");
      for (Estacionamento e : Fachada.listarEstacionamento()) {
        System.out.println(e);
        System.out.println("   Bilhetes (" + e.getBilhetes().size() + "):");
        for (Bilhete b : e.getBilhetes()) {
          System.out.println("      " + b);
        }
        System.out.println();
      }

      System.out.println("\n========== LISTAGEM DE VEÍCULOS ==========");
      for (Veiculo v : Fachada.listarVeiculos()) {
        System.out.println(v);
        System.out.println("   Bilhetes (" + v.getBilhetes().size() + "):");
        for (Bilhete b : v.getBilhetes()) {
          System.out.println("      " + b);
        }
        System.out.println();
      }

      System.out.println("\n========== LISTAGEM DE BILHETES ==========");
      for (Bilhete b : Fachada.listarBilhete()) {
        System.out.println(b);
      }
      
    } catch (Exception e) {
      System.out.println("ERRO: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Listar();
  }
}
