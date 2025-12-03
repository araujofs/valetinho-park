package appconsole;

import requisito.Fachada;
import modelo.Localizacao;

/**
 * Classe para cadastrar dados de exemplo no sistema via console.
 * Usa a Fachada para garantir as regras de negócio.
 */
public class Cadastrar {

  public Cadastrar() {
    try {
      System.out.println("========== CADASTRANDO ESTACIONAMENTOS ==========");
      
      Fachada.criarEstacionamento("Shopping Center", new Localizacao(10.0, 20.0));
      System.out.println("Estacionamento 'Shopping Center' criado");
      
      Fachada.criarEstacionamento("Aeroporto", new Localizacao(11.5, 21.5));
      System.out.println("Estacionamento 'Aeroporto' criado");
      
      Fachada.criarEstacionamento("Centro", new Localizacao(12.0, 22.0));
      System.out.println("Estacionamento 'Centro' criado");
      
      Fachada.criarEstacionamento("Praia", new Localizacao(13.2, 23.4));
      System.out.println("Estacionamento 'Praia' criado");
      
      Fachada.criarEstacionamento("Hospital", new Localizacao(9.9, 19.8));
      System.out.println("Estacionamento 'Hospital' criado");
      
      Fachada.criarEstacionamento("Universidade", new Localizacao(14.0, 24.0));
      System.out.println("Estacionamento 'Universidade' criado");

      System.out.println("\n========== CADASTRANDO VEÍCULOS ==========");
      
      Fachada.criarVeiculo("ABC-1234");
      System.out.println("Veículo 'ABC-1234' criado");
      
      Fachada.criarVeiculo("DEF-5678");
      System.out.println("Veículo 'DEF-5678' criado");
      
      Fachada.criarVeiculo("GHI-9012");
      System.out.println("Veículo 'GHI-9012' criado");
      
      Fachada.criarVeiculo("JKL-3456");
      System.out.println("Veículo 'JKL-3456' criado");
      
      Fachada.criarVeiculo("MNO-7890");
      System.out.println("Veículo 'MNO-7890' criado");
      
      Fachada.criarVeiculo("PQR-1122");
      System.out.println("Veículo 'PQR-1122' criado");

      System.out.println("\n========== CADASTRANDO BILHETES ==========");
      
      // Bilhetes para diferentes veículos em diferentes estacionamentos
      Fachada.criarBilhete("01/12/2025 08:00:00", 10.0, "ABC-1234", "Shopping Center");
      System.out.println("Bilhete criado: ABC-1234 no Shopping Center");
      
      Fachada.criarBilhete("01/12/2025 09:30:00", 12.5, "DEF-5678", "Shopping Center");
      System.out.println("Bilhete criado: DEF-5678 no Shopping Center");
      
      Fachada.criarBilhete("01/12/2025 10:00:00", 8.0, "DEF-5678", "Aeroporto");
      System.out.println("Bilhete criado: DEF-5678 no Aeroporto");
      
      Fachada.criarBilhete("30/11/2025 14:00:00", 20.0, "GHI-9012", "Centro");
      System.out.println("Bilhete criado: GHI-9012 no Centro");
      
      Fachada.criarBilhete("30/11/2025 16:00:00", 5.0, "ABC-1234", "Aeroporto");
      System.out.println("Bilhete criado: ABC-1234 no Aeroporto");
      
      Fachada.criarBilhete("29/11/2025 11:00:00", 7.5, "JKL-3456", "Praia");
      System.out.println("Bilhete criado: JKL-3456 na Praia");
      
      Fachada.criarBilhete("28/11/2025 09:00:00", 15.0, "MNO-7890", "Hospital");
      System.out.println("Bilhete criado: MNO-7890 no Hospital");
      
      Fachada.criarBilhete("27/11/2025 13:00:00", 3.0, "PQR-1122", "Universidade");
      System.out.println("Bilhete criado: PQR-1122 na Universidade");
      
      Fachada.criarBilhete("02/12/2025 08:00:00", 6.0, "JKL-3456", "Praia");
      System.out.println("Bilhete criado: JKL-3456 na Praia");
      
      Fachada.criarBilhete("02/12/2025 10:00:00", 9.0, "MNO-7890", "Hospital");
      System.out.println("Bilhete criado: MNO-7890 no Hospital");
      
      Fachada.criarBilhete("02/12/2025 11:00:00", 11.0, "PQR-1122", "Universidade");
      System.out.println("Bilhete criado: PQR-1122 na Universidade");
      
      Fachada.criarBilhete("02/12/2025 14:00:00", 18.0, "GHI-9012", "Centro");
      System.out.println("Bilhete criado: GHI-9012 no Centro");

      System.out.println("\n========== CADASTRO CONCLUÍDO ==========");
      
    } catch (Exception e) {
      System.out.println("ERRO: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Cadastrar();
  }
}
