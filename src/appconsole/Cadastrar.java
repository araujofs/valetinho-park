package appconsole;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Localizacao;
import modelo.Veiculo;
import repositorio.Repositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cadastrar {

  public Cadastrar() {
    try {
      // Localizacoes
      Localizacao l1 = new Localizacao(10.0, 20.0);
      Localizacao l2 = new Localizacao(11.5, 21.5);
      Localizacao l3 = new Localizacao(12.0, 22.0);
      Localizacao l4 = new Localizacao(13.2, 23.4);
      Localizacao l5 = new Localizacao(9.9, 19.8);
      Localizacao l6 = new Localizacao(14.0, 24.0);
      Localizacao l7 = new Localizacao(15.1, 25.2);
      Localizacao l8 = new Localizacao(16.3, 26.4);
      Localizacao l9 = new Localizacao(17.5, 27.6);
      Localizacao l10 = new Localizacao(18.7, 28.8);
      Localizacao l11 = new Localizacao(19.9, 29.0);
      Localizacao l12 = new Localizacao(20.2, 30.3);

      // Estacionamentos
      Estacionamento e1 = new Estacionamento(l1);
      Estacionamento e2 = new Estacionamento(l2);
      Estacionamento e3 = new Estacionamento(l3);
      Estacionamento e4 = new Estacionamento(l4);
      Estacionamento e5 = new Estacionamento(l5);
      Estacionamento e6 = new Estacionamento(l6);
      Estacionamento e7 = new Estacionamento(l7);
      Estacionamento e8 = new Estacionamento(l8);
      Estacionamento e9 = new Estacionamento(l9);
      Estacionamento e10 = new Estacionamento(l10);
      Estacionamento e11 = new Estacionamento(l11);
      Estacionamento e12 = new Estacionamento(l12);

      // Veículos
      Veiculo v1 = new Veiculo("ABC-1234");
      Veiculo v2 = new Veiculo("DEF-5678");
      Veiculo v3 = new Veiculo("GHI-9012");
      Veiculo v4 = new Veiculo("JKL-3456");
      Veiculo v5 = new Veiculo("MNO-7890");
      Veiculo v6 = new Veiculo("PQR-1122");
      Veiculo v7 = new Veiculo("STU-3344");
      Veiculo v8 = new Veiculo("VWX-5566");
      Veiculo v9 = new Veiculo("YZA-7788");
      Veiculo v10 = new Veiculo("BCD-9900");
      Veiculo v11 = new Veiculo("EFG-1112");
      Veiculo v12 = new Veiculo("HIJ-1314");

      // Bilhetes (associados a estacionamento e veículo)
      LocalDate today = LocalDate.now();
      Bilhete b1 = new Bilhete(e1, today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 10.0);
      Bilhete b2 = new Bilhete(e1, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 12.5);
      Bilhete b3 = new Bilhete(e2, today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 8.0);
      Bilhete b4 = new Bilhete(e3, today.minusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20.0);
      Bilhete b5 = new Bilhete(e2, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 5.0);
      Bilhete b6 = new Bilhete(e4, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 7.5);
      Bilhete b7 = new Bilhete(e5, today.minusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 15.0);
      Bilhete b8 = new Bilhete(e6, today.minusDays(4).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 3.0);
      Bilhete b9 = new Bilhete(e4, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 6.0);
      Bilhete b10 = new Bilhete(e5, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 9.0);
      Bilhete b11 = new Bilhete(e6, today.minusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 11.0);
      Bilhete b12 = new Bilhete(e3, today.minusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 18.0);

      Bilhete b13 = new Bilhete(e7, today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 13.0);
      Bilhete b14 = new Bilhete(e8, today.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 14.5);
      Bilhete b15 = new Bilhete(e9, today.minusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 16.0);
      Bilhete b16 = new Bilhete(e10, today.minusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 17.5);
      Bilhete b17 = new Bilhete(e11, today.minusDays(4).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 19.0);
      Bilhete b18 = new Bilhete(e12, today.minusDays(5).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 21.0);

      // ligar bilhetes às listas internas
      e1.addBilhete(b1);
      e1.addBilhete(b2);
      e2.addBilhete(b3);
      e2.addBilhete(b5);
      e3.addBilhete(b4);
      e4.addBilhete(b6);
      e4.addBilhete(b9);
      e5.addBilhete(b7);
      e5.addBilhete(b10);
      e6.addBilhete(b8);
      e6.addBilhete(b11);
      e3.addBilhete(b12);

      e7.addBilhete(b13);
      e8.addBilhete(b14);
      e9.addBilhete(b15);
      e10.addBilhete(b16);
      e11.addBilhete(b17);
      e12.addBilhete(b18);

      v1.addBilhete(b1);
      v2.addBilhete(b2);
      v2.addBilhete(b3);
      v3.addBilhete(b4);
      v1.addBilhete(b5);
      v4.addBilhete(b6);
      v5.addBilhete(b7);
      v6.addBilhete(b8);
      v4.addBilhete(b9);
      v5.addBilhete(b10);
      v6.addBilhete(b11);
      v3.addBilhete(b12);

      v7.addBilhete(b13);
      v8.addBilhete(b14);
      v9.addBilhete(b15);
      v10.addBilhete(b16);
      v11.addBilhete(b17);
      v12.addBilhete(b18);

      // Persistir no repositório
      Repositorio.gravar(l1);
      Repositorio.gravar(l2);
      Repositorio.gravar(l3);
      Repositorio.gravar(l4);
      Repositorio.gravar(l5);
      Repositorio.gravar(l6);
      Repositorio.gravar(l7);
      Repositorio.gravar(l8);
      Repositorio.gravar(l9);
      Repositorio.gravar(l10);
      Repositorio.gravar(l11);
      Repositorio.gravar(l12);

      Repositorio.gravar(e1);
      Repositorio.gravar(e2);
      Repositorio.gravar(e3);
      Repositorio.gravar(e4);
      Repositorio.gravar(e5);
      Repositorio.gravar(e6);
      Repositorio.gravar(e7);
      Repositorio.gravar(e8);
      Repositorio.gravar(e9);
      Repositorio.gravar(e10);
      Repositorio.gravar(e11);
      Repositorio.gravar(e12);

      Repositorio.gravar(v1);
      Repositorio.gravar(v2);
      Repositorio.gravar(v3);
      Repositorio.gravar(v4);
      Repositorio.gravar(v5);
      Repositorio.gravar(v6);
      Repositorio.gravar(v7);
      Repositorio.gravar(v8);
      Repositorio.gravar(v9);
      Repositorio.gravar(v10);
      Repositorio.gravar(v11);
      Repositorio.gravar(v12);

      Repositorio.gravar(b1);
      Repositorio.gravar(b2);
      Repositorio.gravar(b3);
      Repositorio.gravar(b4);
      Repositorio.gravar(b5);
      Repositorio.gravar(b6);
      Repositorio.gravar(b7);
      Repositorio.gravar(b8);
      Repositorio.gravar(b9);
      Repositorio.gravar(b10);
      Repositorio.gravar(b11);
      Repositorio.gravar(b12);
      Repositorio.gravar(b13);
      Repositorio.gravar(b14);
      Repositorio.gravar(b15);
      Repositorio.gravar(b16);
      Repositorio.gravar(b17);
      Repositorio.gravar(b18);

      System.out.println("Cadastrou dados de estacionamentos, veículos e bilhetes.");
    } catch (Exception e) {
      System.out.println("--->" + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Cadastrar();
  }

  // Intencionalmente mantém chamadas diretas a Repositorio.gravar(...) para você
  // implementar depois.
}
