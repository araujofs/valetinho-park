package br.com.valetinho.requisito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.valetinho.modelo.Bilhete;
import br.com.valetinho.modelo.Estacionamento;
import br.com.valetinho.modelo.Localizacao;
import br.com.valetinho.modelo.Veiculo;
import br.com.valetinho.repositorio.BilheteRepositorio;
import br.com.valetinho.repositorio.EstacionamentoRepositorio;
import br.com.valetinho.repositorio.VeiculoRepositorio;

public class Fachada {
  private Fachada() {
  }

  private static VeiculoRepositorio veiculoRep = new VeiculoRepositorio();
  private static EstacionamentoRepositorio estacionamentoRep = new EstacionamentoRepositorio();
  private static BilheteRepositorio bilheteRep = new BilheteRepositorio();

  public static Veiculo localizarVeiculo(String placa) {
    veiculoRep.conectar();

    try {
      Veiculo p = veiculoRep.ler(placa);

      if (p == null)
        throw new RuntimeException("Veículo com placa \"" + placa + "\" não existe!");

      return p;
    } catch (Exception e) {
      throw e;
    } finally {
      veiculoRep.desconectar();
    }
  }

  public static void criarVeiculo(String placa) {
    veiculoRep.conectar();

    try {
      veiculoRep.begin();

      Veiculo veiculo = new Veiculo(placa);
      veiculoRep.criar(veiculo);
      veiculoRep.commit();
    } catch (Exception e) {
      veiculoRep.rollback();
      throw e;
    } finally {
      veiculoRep.desconectar();
    }
  }

  public static void apagarVeiculo(String placa) {
    veiculoRep.conectar();

    try {
      veiculoRep.begin();

      Veiculo veiculo = veiculoRep.ler(placa);

      if (veiculo == null)
        throw new RuntimeException("Veículo com placa \"" + placa + "\" não existe, impossível apagar!");

      List<Bilhete> bilhetes = veiculo.getBilhetes();

      for (Bilhete bilhete : bilhetes) {
        bilhete.getEstacionamento().removeBilhete(bilhete);
      }

      veiculoRep.apagar(veiculo);
      veiculoRep.commit();
    } catch (Exception e) {
      veiculoRep.rollback();
      throw e;
    } finally {
      veiculoRep.desconectar();
    }
  }

  public static List<Veiculo> listarVeiculos() {
    veiculoRep.conectar();

    try {
      List<Veiculo> p = veiculoRep.listar();

      return p;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar por veículos!");
    } finally {
      veiculoRep.desconectar();
    }
  }

  public static void alterarPlacaVeiculo(String placaAntiga, String placaNova) {
    veiculoRep.conectar();

    try {
      veiculoRep.begin();

      Veiculo v = veiculoRep.ler(placaAntiga);

      if (v == null) {
        throw new RuntimeException("Veículo com placa \"" + placaAntiga + "\" não existe!");
      }

      v.setPlaca(placaNova);
      veiculoRep.atualizar(v);
      veiculoRep.commit();
    } catch (Exception e) {
      veiculoRep.rollback();
      throw e;
    } finally {
      veiculoRep.desconectar();
    }
  }

  public static Estacionamento localizarEstacionamento(String nome) {
    estacionamentoRep.conectar();

    try {
      Estacionamento e = estacionamentoRep.ler(nome);

      if (e == null)
        throw new RuntimeException("Estacionamento com nome \"" + nome + "\" não existe!");

      return e;
    } catch (Exception e) {
      throw e;
    } finally {
      estacionamentoRep.desconectar();
    }
  }

  public static void criarEstacionamento(String nome, Localizacao localizacao) {
    estacionamentoRep.conectar();

    try {
      estacionamentoRep.begin();

      Estacionamento estacionamento = new Estacionamento(localizacao, nome);
      estacionamentoRep.criar(estacionamento);
      estacionamentoRep.commit();
    } catch (Exception e) {
      estacionamentoRep.rollback();
      throw e;
    } finally {
      estacionamentoRep.desconectar();
    }
  }

  public static void alterarEstacionamento(Integer id, String nome, Localizacao localizacao)
      {
    estacionamentoRep.conectar();

    try {
      estacionamentoRep.begin();

      Estacionamento e = estacionamentoRep.ler(id);

      if (e == null) {
        throw new RuntimeException("Estacionamento com id \"" + id + "\" não existe!");
      }

      e.setNome(nome);
      e.setLocalizacao(localizacao);

      estacionamentoRep.atualizar(e);
      estacionamentoRep.commit();
    } catch (Exception e) {
      estacionamentoRep.rollback();
      throw e;
    } finally {
      estacionamentoRep.desconectar();
    }
  }

  public static void apagarEstacionamento(String nome) {
    estacionamentoRep.conectar();

    try {
      estacionamentoRep.begin();

      Estacionamento estacionamento = estacionamentoRep.ler(nome);

      if (estacionamento == null)
        throw new RuntimeException("Estacionamento com nome \"" + nome + "\" não existe, impossível apagar!");

      List<Bilhete> bilhetes = estacionamento.getBilhetes();

      for (Bilhete bilhete : bilhetes) {
        bilhete.getVeiculo().removeBilhete(bilhete);
      }

      estacionamentoRep.apagar(estacionamento);
      estacionamentoRep.commit();
    } catch (Exception e) {
      estacionamentoRep.rollback();
      throw e;
    } finally {
      estacionamentoRep.desconectar();
    }
  }

  public static List<Estacionamento> listarEstacionamento() {
    estacionamentoRep.conectar();

    try {
      List<Estacionamento> e = estacionamentoRep.listar();

      return e;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar por estacionamentos!");
    } finally {
      estacionamentoRep.desconectar();
    }
  }

  public static Bilhete localizarBilhete(Integer id) {
    bilheteRep.conectar();

    try {
      Bilhete e = bilheteRep.ler(id);

      if (e == null)
        return null;

      return e;
    } catch (Exception e) {
      throw new RuntimeException("Bilhete com id \"" + id + "\" não existe!");
    } finally {
      bilheteRep.desconectar();
    }
  }

  public static void criarBilhete(LocalDate data, LocalTime hora, Double valorPago, String placaVeiculo,
      String nomeEstacionamento)
      {
    bilheteRep.conectar();

    try {
      bilheteRep.begin();

      Veiculo veiculo = veiculoRep.ler(placaVeiculo);
      if (veiculo == null) {
        throw new RuntimeException("Erro na criação do bilhete -> Veiculo com placa \"" + placaVeiculo + "\" não existe!");
      }

      Estacionamento estacionamento = estacionamentoRep.ler(nomeEstacionamento);
      if (estacionamento == null) {
        bilheteRep.rollback();
        throw new RuntimeException(
            "Erro na criação do bilhete -> Estacionamento com nome \"" + nomeEstacionamento + "\" não existe!");
      }

      List<Bilhete> p = bilheteRep.lerBilhetePorVeiculoDataHora(veiculo, data, hora);
      if (p != null && p.size() > 0) {
        throw new RuntimeException("Bilhete não pode ser criado antes de bilhetes mais recentes!");
      }

      Bilhete b = new Bilhete(estacionamento, veiculo, data, hora, valorPago);

      veiculo.addBilhete(b);
      estacionamento.addBilhete(b);

      bilheteRep.criar(b);

      bilheteRep.commit();
    } catch (Exception e) {
      bilheteRep.rollback();
      throw e;
    } finally {
      bilheteRep.desconectar();
    }

  }

  // Não faz sentido poder alterar bilhete
  // public static void alterarBilhete(Integer id, LocalDate data, LocalTime hora,
  // Double valorPago, Veiculo veiculo,
  // Estacionamento estacionamento) {
  // bilheteRep.conectar();

  // try {
  // Bilhete p = bilheteRep.ler(id);
  // if (p == null) {
  // throw new RuntimeException("alterar bilhete - bilhete inexistente:" + id);
  // }

  // p.setVeiculo(veiculo);
  // p.setEstacionamento(estacionamento);
  // p.setData(data);
  // p.setValorpago(valorPago);

  // bilheteRep.atualizar(p);
  // bilheteRep.commit();
  // bilheteRep.desconectar();

  // } catch (Exception e) {
  // bilheteRep.rollback();
  // throw e;
  // }
  // }

  public static void apagarBilhete(Integer id) {
    bilheteRep.conectar();

    try {
      bilheteRep.begin();
      Bilhete p = bilheteRep.ler(id);

      if (p == null) {
        throw new RuntimeException("Bilhete com id: " + id + " não existe, impossível deletar");
      }

      p.getVeiculo().removeBilhete(p);
      p.getEstacionamento().removeBilhete(p);

      bilheteRep.apagar(p);
      bilheteRep.commit();
    } catch (Exception e) {
      bilheteRep.rollback();
      throw e;
    } finally {
      bilheteRep.desconectar();
    }

  }

  public static List<Bilhete> listarBilhete() {
    bilheteRep.conectar();
    try {
      List<Bilhete> bilhetes = bilheteRep.listar();

      return bilhetes;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar por bilhetes!");
    } finally {
      bilheteRep.desconectar();

    }
  }

  /**********************************************************
   * 
   * CONSULTAS IMPLEMENTADAS NOS DAO
   * 
   **********************************************************/

  public static List<Bilhete> consultarBilhetesValorMaiorX(Double x) {
    bilheteRep.conectar();

    List<Bilhete> bilhetes = bilheteRep.lerBilheteMaiorValorPago(x);
    bilheteRep.desconectar();
    return bilhetes;
  }

  public static List<Veiculo> consultarVeiculoEstacionadoDataX(LocalDate x, String nomeEstacionamento) {
    veiculoRep.conectar();
    List<Veiculo> veiculos = veiculoRep.lerVeiculoEstacionadoData(nomeEstacionamento, x);
    veiculoRep.desconectar();
    return veiculos;
  }

  public static List<Veiculo> consultarVeiculoMaisXBilhetes(Integer x) {
    veiculoRep.conectar();
    List<Veiculo> veiculos = veiculoRep.lerVeiculoMaisBilhetes(x);
    veiculoRep.desconectar();
    return veiculos;
  }
}
