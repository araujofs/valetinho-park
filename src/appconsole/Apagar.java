package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import requisito.Fachada;

public class Apagar {

  public Apagar() {
    try {
      System.out.println("========== APAGANDO BILHETE ==========");
      
      System.out.println("Bilhetes antes da exclusão:");
      for (Bilhete b : Fachada.listarBilhete()) {
        System.out.println("   " + b);
      }
      
      Bilhete bilheteParaApagar = Fachada.listarBilhete().get(0);
      Integer idBilhete = bilheteParaApagar.getId();
      String placaVeiculo = bilheteParaApagar.getVeiculo().getPlaca();
      String nomeEstacionamento = bilheteParaApagar.getEstacionamento().getNome();
      
      System.out.println("\nApagando bilhete ID: " + idBilhete);
      System.out.println("   Veículo: " + placaVeiculo);
      System.out.println("   Estacionamento: " + nomeEstacionamento);
      
      Veiculo v = Fachada.localizarVeiculo(placaVeiculo);
      Estacionamento e = Fachada.localizarEstacionamento(nomeEstacionamento);
      System.out.println("\nANTES:");
      System.out.println("   Veículo " + placaVeiculo + " tem " + v.getBilhetes().size() + " bilhete(s)");
      System.out.println("   Estacionamento " + nomeEstacionamento + " tem " + e.getBilhetes().size() + " bilhete(s)");
      
      Fachada.apagarBilhete(idBilhete);
      System.out.println("\nBilhete apagado com sucesso!");
      
      v = Fachada.localizarVeiculo(placaVeiculo);
      e = Fachada.localizarEstacionamento(nomeEstacionamento);
      System.out.println("\nDEPOIS:");
      System.out.println("   Veículo " + placaVeiculo + " tem " + v.getBilhetes().size() + " bilhete(s)");
      System.out.println("   Estacionamento " + nomeEstacionamento + " tem " + e.getBilhetes().size() + " bilhete(s)");
      
      System.out.println("\n========== APAGANDO VEÍCULO ==========");
      
      System.out.println("\nCriando veículo temporário 'TEMP-0000'...");
      Fachada.criarVeiculo("TEMP-0000");
      
      System.out.println("Criando bilhete para o veículo temporário...");
      Fachada.criarBilhete("02/12/2025 18:00:00", 5.0, "TEMP-0000", "Centro");
      
      v = Fachada.localizarVeiculo("TEMP-0000");
      System.out.println("Veículo TEMP-0000 tem " + v.getBilhetes().size() + " bilhete(s)");
      
      System.out.println("\nApagando veículo TEMP-0000...");
      Fachada.apagarVeiculo("TEMP-0000");
      System.out.println("Veículo apagado com sucesso!");
      
      try {
        Fachada.localizarVeiculo("TEMP-0000");
        System.out.println("ERRO: Veículo ainda existe!");
      } catch (Exception ex) {
        System.out.println("Confirmado: " + ex.getMessage());
      }
      
      System.out.println("\n========== EXCLUSÕES CONCLUÍDAS ==========");
      
    } catch (Exception e) {
      System.out.println("ERRO: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Apagar();
  }
}
