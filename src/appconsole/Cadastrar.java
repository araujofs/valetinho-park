package src.appconsole;

import src.modelo.Bilhete;
import src.modelo.Estacionamento;
import src.modelo.Localizacao;
import src.modelo.Veiculo;
import src.repositorio.Repositorio;

import java.util.Date;

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

      // Estacionamentos
      Estacionamento e1 = new Estacionamento(l1);
      Estacionamento e2 = new Estacionamento(l2);
      Estacionamento e3 = new Estacionamento(l3);
      Estacionamento e4 = new Estacionamento(l4);
      Estacionamento e5 = new Estacionamento(l5);
      Estacionamento e6 = new Estacionamento(l6);

      // Veículos
      Veiculo v1 = new Veiculo("ABC-1234");
      Veiculo v2 = new Veiculo("DEF-5678");
      Veiculo v3 = new Veiculo("GHI-9012");
      Veiculo v4 = new Veiculo("JKL-3456");
      Veiculo v5 = new Veiculo("MNO-7890");
      Veiculo v6 = new Veiculo("PQR-1122");

      // Bilhetes (associados a estacionamento e veículo)
      Date now = new Date();
      Bilhete b1 = new Bilhete(e1, now, 10.0);
      Bilhete b2 = new Bilhete(e1, new Date(now.getTime() - 60_000L), 12.5);
      Bilhete b3 = new Bilhete(e2, now, 8.0);
      Bilhete b4 = new Bilhete(e3, new Date(now.getTime() - 120_000L), 20.0);
      Bilhete b5 = new Bilhete(e2, new Date(now.getTime() - 30_000L), 5.0);
      Bilhete b6 = new Bilhete(e4, new Date(now.getTime() - 5_000L), 7.5);
      Bilhete b7 = new Bilhete(e5, new Date(now.getTime() - 200_000L), 15.0);
      Bilhete b8 = new Bilhete(e6, new Date(now.getTime() - 300_000L), 3.0);
      Bilhete b9 = new Bilhete(e4, new Date(now.getTime() - 15_000L), 6.0);
      Bilhete b10 = new Bilhete(e5, new Date(now.getTime() - 45_000L), 9.0);
      Bilhete b11 = new Bilhete(e6, new Date(now.getTime() - 90_000L), 11.0);
      Bilhete b12 = new Bilhete(e3, new Date(now.getTime() - 250_000L), 18.0);

      // ligar bilhetes às listas internas
      e1.getListaBilhete().add(b1);
      e1.getListaBilhete().add(b2);
      e2.getListaBilhete().add(b3);
      e2.getListaBilhete().add(b5);
      e3.getListaBilhete().add(b4);
      e4.getListaBilhete().add(b6);
      e4.getListaBilhete().add(b9);
      e5.getListaBilhete().add(b7);
      e5.getListaBilhete().add(b10);
      e6.getListaBilhete().add(b8);
      e6.getListaBilhete().add(b11);
      e3.getListaBilhete().add(b12);

      v1.getListaBilhete().add(b1);
      v2.getListaBilhete().add(b2);
      v2.getListaBilhete().add(b3);
      v3.getListaBilhete().add(b4);
      v1.getListaBilhete().add(b5);
      v4.getListaBilhete().add(b6);
      v5.getListaBilhete().add(b7);
      v6.getListaBilhete().add(b8);
      v4.getListaBilhete().add(b9);
      v5.getListaBilhete().add(b10);
      v6.getListaBilhete().add(b11);
      v3.getListaBilhete().add(b12);

      // Persistir no repositório
      Repositorio.gravar(l1);
      Repositorio.gravar(l2);
      Repositorio.gravar(l3);
      Repositorio.gravar(l4);
      Repositorio.gravar(l5);
      Repositorio.gravar(l6);

      Repositorio.gravar(e1);
      Repositorio.gravar(e2);
      Repositorio.gravar(e3);
      Repositorio.gravar(e4);
      Repositorio.gravar(e5);
      Repositorio.gravar(e6);

      Repositorio.gravar(v1);
      Repositorio.gravar(v2);
      Repositorio.gravar(v3);
      Repositorio.gravar(v4);
      Repositorio.gravar(v5);
      Repositorio.gravar(v6);

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

      System.out.println("Cadastrou dados de estacionamento e bilhetes.");
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
