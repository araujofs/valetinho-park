package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;
import requisito.Fachada;

/**
 * Classe para demonstrar exclusões no sistema via console.
 * Usa a Fachada para garantir as regras de negócio.
 */
public class Apagar {

  public Apagar() {
    try {
      System.out.println("========== APAGANDO BILHETE ==========");
      
      // Listar bilhetes antes
      System.out.println("Bilhetes antes da exclusão:");
      for (Bilhete b : Fachada.listarBilhete()) {
        System.out.println("   " + b);
      }
      
      // Pegar o primeiro bilhete para apagar
      Bilhete bilheteParaApagar = Fachada.listarBilhete().get(0);
      Integer idBilhete = bilheteParaApagar.getId();
      String placaVeiculo = bilheteParaApagar.getVeiculo().getPlaca();
      String nomeEstacionamento = bilheteParaApagar.getEstacionamento().getNome();
      
      System.out.println("\nApagando bilhete ID: " + idBilhete);
      System.out.println("   Veículo: " + placaVeiculo);
      System.out.println("   Estacionamento: " + nomeEstacionamento);
      
      // Mostrar quantidade de bilhetes do veículo e estacionamento antes
      Veiculo v = Fachada.localizarVeiculo(placaVeiculo);
      Estacionamento e = Fachada.localizarEstacionamento(nomeEstacionamento);
      System.out.println("\nANTES:");
      System.out.println("   Veículo " + placaVeiculo + " tem " + v.getBilhetes().size() + " bilhete(s)");
      System.out.println("   Estacionamento " + nomeEstacionamento + " tem " + e.getBilhetes().size() + " bilhete(s)");
      
      // Apagar o bilhete
      Fachada.apagarBilhete(idBilhete);
      System.out.println("\nBilhete apagado com sucesso!");
      
      // Mostrar quantidade de bilhetes do veículo e estacionamento depois
      v = Fachada.localizarVeiculo(placaVeiculo);
      e = Fachada.localizarEstacionamento(nomeEstacionamento);
      System.out.println("\nDEPOIS:");
      System.out.println("   Veículo " + placaVeiculo + " tem " + v.getBilhetes().size() + " bilhete(s)");
      System.out.println("   Estacionamento " + nomeEstacionamento + " tem " + e.getBilhetes().size() + " bilhete(s)");
      
      System.out.println("\n========== APAGANDO VEÍCULO ==========");
      
      // Criar um veículo temporário para demonstrar exclusão
      System.out.println("\nCriando veículo temporário 'TEMP-0000'...");
      Fachada.criarVeiculo("TEMP-0000");
      
      // Criar um bilhete para o veículo temporário
      System.out.println("Criando bilhete para o veículo temporário...");
      Fachada.criarBilhete("02/12/2025 18:00:00", 5.0, "TEMP-0000", "Centro");
      
      v = Fachada.localizarVeiculo("TEMP-0000");
      System.out.println("Veículo TEMP-0000 tem " + v.getBilhetes().size() + " bilhete(s)");
      
      // Apagar o veículo (deve apagar os bilhetes associados também)
      System.out.println("\nApagando veículo TEMP-0000...");
      Fachada.apagarVeiculo("TEMP-0000");
      System.out.println("Veículo apagado com sucesso!");
      
      // Tentar localizar o veículo apagado
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
