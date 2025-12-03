package appconsole;

import modelo.Estacionamento;
import modelo.Localizacao;
import modelo.Veiculo;
import requisito.Fachada;

public class Alterar {
  
  public Alterar() {
    try {
      System.out.println("========== ALTERANDO VEÍCULO ==========");
      
      Veiculo v = Fachada.localizarVeiculo("ABC-1234");
      System.out.println("ANTES: " + v);
      System.out.println("   Bilhetes: " + v.getBilhetes().size());
      
      Fachada.alterarPlacaVeiculo("ABC-1234", "ZZZ-9999");
      System.out.println("\nPlaca alterada de ABC-1234 para ZZZ-9999");
      
      v = Fachada.localizarVeiculo("ZZZ-9999");
      System.out.println("DEPOIS: " + v);
      System.out.println("   Bilhetes: " + v.getBilhetes().size());
      
      Fachada.alterarPlacaVeiculo("ZZZ-9999", "ABC-1234");
      System.out.println("\nPlaca revertida para ABC-1234");
      
      System.out.println("\n========== ALTERANDO ESTACIONAMENTO ==========");
      
      Estacionamento est = Fachada.localizarEstacionamento("Shopping Center");
      System.out.println("ANTES: " + est);
      System.out.println("   Localização: " + est.getLocalizacao());
      System.out.println("   Bilhetes: " + est.getBilhetes().size());
      
      Fachada.alterarEstacionamento(est.getId(), "Shopping Novo", new Localizacao(99.0, 99.0));
      System.out.println("\nEstacionamento alterado");
      
      est = Fachada.localizarEstacionamento("Shopping Novo");
      System.out.println("DEPOIS: " + est);
      System.out.println("   Localização: " + est.getLocalizacao());
      System.out.println("   Bilhetes: " + est.getBilhetes().size());
      
      Fachada.alterarEstacionamento(est.getId(), "Shopping Center", new Localizacao(10.0, 20.0));
      System.out.println("\nEstacionamento revertido para 'Shopping Center'");
      
      System.out.println("\n========== ALTERAÇÕES CONCLUÍDAS ==========");
      
    } catch (Exception e) {
      System.out.println("ERRO: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Alterar();
  }
}
