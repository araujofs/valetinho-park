package appconsole;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.Bilhete;
import modelo.Veiculo;
import requisito.Fachada;

/**
 * Classe para demonstrar consultas especiais no sistema via console.
 * Usa a Fachada para acessar os métodos de consulta.
 */
public class Consultar {

  public Consultar() {
    try {
      System.out.println("========== CONSULTA 1: Bilhetes com valor pago > 10 ==========");
      List<Bilhete> bilhetesCaros = Fachada.consultarBilhetesValorMaiorX(10.0);
      System.out.println("Encontrados " + bilhetesCaros.size() + " bilhete(s):");
      for (Bilhete b : bilhetesCaros) {
        System.out.println("   " + b);
      }

      System.out.println("\n========== CONSULTA 2: Veículos estacionados em uma data específica ==========");
      // Usar a data de hoje
      Date hoje = new Date();
      String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(hoje);
      System.out.println("Data da consulta: " + dataFormatada);
      System.out.println("Estacionamento: Shopping Center");
      
      List<Veiculo> veiculosEstacionados = Fachada.consultarVeiculoEstacionadoDataX(hoje, "Shopping Center");
      System.out.println("Encontrados " + veiculosEstacionados.size() + " veículo(s):");
      for (Veiculo v : veiculosEstacionados) {
        System.out.println("   " + v);
      }

      System.out.println("\n========== CONSULTA 3: Veículos com mais de 1 bilhete ==========");
      List<Veiculo> veiculosComMaisBilhetes = Fachada.consultarVeiculoMaisXBilhetes(1);
      System.out.println("Encontrados " + veiculosComMaisBilhetes.size() + " veículo(s):");
      for (Veiculo v : veiculosComMaisBilhetes) {
        System.out.println("   " + v + " - " + v.getBilhetes().size() + " bilhete(s)");
      }

      System.out.println("\n========== CONSULTAS CONCLUÍDAS ==========");
      
    } catch (Exception e) {
      System.out.println("ERRO: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Consultar();
  }
}
