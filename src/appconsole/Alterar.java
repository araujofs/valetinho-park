package appconsole;

import modelo.Estacionamento;
import modelo.Localizacao;
import modelo.Veiculo;
import requisito.Fachada;

/**
 * Classe para demonstrar alterações no sistema via console.
 * Usa a Fachada para garantir as regras de negócio.
 */
public class Alterar {
  
  public Alterar() {
    try {
      System.out.println("========== ALTERANDO VEÍCULO ==========");
      
      // Mostrar veículo antes da alteração
      Veiculo v = Fachada.localizarVeiculo("ABC-1234");
      System.out.println("ANTES: " + v);
      System.out.println("   Bilhetes: " + v.getBilhetes().size());
      
      // Alterar placa do veículo
      Fachada.alterarPlacaVeiculo("ABC-1234", "ZZZ-9999");
      System.out.println("\nPlaca alterada de ABC-1234 para ZZZ-9999");
      
      // Mostrar veículo depois da alteração
      v = Fachada.localizarVeiculo("ZZZ-9999");
      System.out.println("DEPOIS: " + v);
      System.out.println("   Bilhetes: " + v.getBilhetes().size());
      
      // Reverter para manter os dados consistentes
      Fachada.alterarPlacaVeiculo("ZZZ-9999", "ABC-1234");
      System.out.println("\nPlaca revertida para ABC-1234");
      
      System.out.println("\n========== ALTERANDO ESTACIONAMENTO ==========");
      
      // Mostrar estacionamento antes da alteração
      Estacionamento est = Fachada.localizarEstacionamento("Shopping Center");
      System.out.println("ANTES: " + est);
      System.out.println("   Localização: " + est.getLocalizacao());
      System.out.println("   Bilhetes: " + est.getBilhetes().size());
      
      // Alterar nome e localização do estacionamento
      Fachada.alterarEstacionamento(est.getId(), "Shopping Novo", new Localizacao(99.0, 99.0));
      System.out.println("\nEstacionamento alterado");
      
      // Mostrar estacionamento depois da alteração
      est = Fachada.localizarEstacionamento("Shopping Novo");
      System.out.println("DEPOIS: " + est);
      System.out.println("   Localização: " + est.getLocalizacao());
      System.out.println("   Bilhetes: " + est.getBilhetes().size());
      
      // Reverter para manter os dados consistentes
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
