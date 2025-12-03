package requisito;

import java.text.SimpleDateFormat;

/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.Estacionamento;
import modelo.Localizacao;
import modelo.Veiculo;
import modelo.Bilhete;
import repositorio.EstacionamentoRepositorio;
import repositorio.VeiculoRepositorio;
import repositorio.BilheteRepositorio;

public class Fachada {
  private Fachada() {
  }

  private static VeiculoRepositorio veiculoRep = new VeiculoRepositorio();
  private static EstacionamentoRepositorio estacionamentoRep = new EstacionamentoRepositorio();
  private static BilheteRepositorio bilheteRep = new BilheteRepositorio();

  public static Veiculo localizarVeiculo(String placa) throws Exception {
    veiculoRep.conectar();
    Veiculo p = veiculoRep.ler(placa);
    if (p == null) {
      veiculoRep.desconectar();
      throw new Exception("veiculo inexistente: " + placa);
    }
    veiculoRep.desconectar();
    return p;
  }

  public static void criarVeiculo(String placa) throws Exception {
    veiculoRep.conectar();
    Veiculo p = veiculoRep.ler(placa);
    if (p != null) {
      veiculoRep.desconectar();
      throw new Exception("criar veiculo - veiculo ja existe com placa :" + placa);
    }
    p = new Veiculo(placa);
    veiculoRep.criar(p);
    veiculoRep.commit();
    veiculoRep.desconectar();
  }

  public static void apagarVeiculo(String nome) throws Exception {
    veiculoRep.conectar();
    Veiculo p = veiculoRep.ler(nome);
    if (p == null) {
      veiculoRep.rollback();
      throw new Exception("excluir veiculo - veiculo inexistente:" + nome);
    }

    for (Bilhete t : p.getBilhetes()) {
      estacionamentoRep.removeBilhete(t.getEstacionamento(), t);
      bilheteRep.apagar(t); // deletar o bilhete orfao
    }

    veiculoRep.apagar(p); // apagar a veiculo
    veiculoRep.commit();
    veiculoRep.desconectar();
  }

  public static List<Veiculo> listarVeiculos() {
    veiculoRep.conectar();
    List<Veiculo> lista = veiculoRep.listar();
    veiculoRep.desconectar();
    return lista;
  }

  public static void alterarPlacaVeiculo(String placaAntiga, String placaNova) throws Exception {
    veiculoRep.conectar();
    
    // Verifica se o veículo com a placa antiga existe
    Veiculo v = veiculoRep.ler(placaAntiga);
    if (v == null) {
      veiculoRep.rollback();
      throw new Exception("alterar placa - veiculo inexistente: " + placaAntiga);
    }
    
    // Verifica se já existe um veículo com a nova placa
    Veiculo existente = veiculoRep.ler(placaNova);
    if (existente != null) {
      veiculoRep.rollback();
      throw new Exception("alterar placa - ja existe veiculo com placa: " + placaNova);
    }
    
    // Atualiza a placa
    v.setPlaca(placaNova);
    veiculoRep.atualizar(v);
    veiculoRep.commit();
    veiculoRep.desconectar();
  }

  public static Estacionamento localizarEstacionamento(String nome) throws Exception {
    estacionamentoRep.conectar();
    Estacionamento p = estacionamentoRep.ler(nome);
    if (p == null) {
      estacionamentoRep.desconectar();
      throw new Exception("estacionamento inexistente: " + nome);
    }
    estacionamentoRep.desconectar();
    return p;
  }

  public static void criarEstacionamento(String nome, Localizacao localizacao) throws Exception {
    estacionamentoRep.conectar();
    Estacionamento p = estacionamentoRep.ler(nome);
    if (p != null) {
      estacionamentoRep.desconectar();
      throw new Exception("criar estacionamento - estacionamento ja existe com nome: " + nome);
    }
    p = new Estacionamento(localizacao, nome);
    estacionamentoRep.criar(p);
    estacionamentoRep.commit();
    estacionamentoRep.desconectar();
  }

  public static void alterarEstacionamento(Integer id, String nome, Localizacao localizacao)
      throws Exception {
    estacionamentoRep.conectar();
    Estacionamento p = estacionamentoRep.ler(id);
    if (p == null) {
      estacionamentoRep.rollback();
      throw new Exception("alterar estacionamento - estacionamento inexistente:" + id);
    }

    // Não mexer na lista de bilhetes - ela é gerenciada por criarBilhete/apagarBilhete
    p.setLocalizacao(localizacao);
    p.setNome(nome);

    estacionamentoRep.atualizar(p);
    estacionamentoRep.commit();
    estacionamentoRep.desconectar();
  }

  public static void apagarEstacionamento(String nome) throws Exception {
    estacionamentoRep.conectar();
    Estacionamento p = estacionamentoRep.ler(nome);
    if (p == null) {
      estacionamentoRep.rollback();
      throw new Exception("excluir veiculo - veiculo inexistente:" + nome);
    }

    for (Bilhete t : p.getBilhetes()) {
      veiculoRep.removeBilhete(t.getVeiculo(), t);
      bilheteRep.apagar(t); // deletar o bilhete orfao
    }

    estacionamentoRep.apagar(p); // apagar a veiculo
    estacionamentoRep.commit();
    estacionamentoRep.desconectar();
  }

  public static List<Estacionamento> listarEstacionamento() {
    estacionamentoRep.conectar();
    List<Estacionamento> lista = estacionamentoRep.listar();
    estacionamentoRep.desconectar();
    return lista;
  }

  public static Bilhete localizarBilhete(Integer id) throws Exception {
    bilheteRep.conectar();
    Bilhete p = bilheteRep.ler(id);
    if (p == null) {
      bilheteRep.desconectar();
      throw new Exception("bilhete inexistente: " + id);
    }
    bilheteRep.desconectar();
    return p;
  }

  public static void criarBilhete(String data, Double valorPago, String placaVeiculo, String nomeEstacionamento)
      throws Exception {
    Date dataFormatada;
    try {
      dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data);
    } catch (Exception e) {
      throw new Exception("criar bilhete - erro ao parsear data (formato invalido): " + data);
    }
    
    bilheteRep.conectar();
    
    // Buscar veículo e estacionamento DENTRO da mesma conexão
    Veiculo veiculo = veiculoRep.ler(placaVeiculo);
    if (veiculo == null) {
      bilheteRep.rollback();
      throw new Exception("criar bilhete - veiculo inexistente: " + placaVeiculo);
    }
    
    Estacionamento estacionamento = estacionamentoRep.ler(nomeEstacionamento);
    if (estacionamento == null) {
      bilheteRep.rollback();
      throw new Exception("criar bilhete - estacionamento inexistente: " + nomeEstacionamento);
    }
    
    Bilhete p = bilheteRep.lerBilhetePorVeiculoData(veiculo, dataFormatada);
    if (p != null) {
      bilheteRep.rollback();
      throw new Exception(
          "criar bilhete - bilhete já existe ou existe um bilhete associado ao mesmo veiculo há menos de uma hora de diferença:"
              + placaVeiculo + ", " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataFormatada));
    }
    p = new Bilhete(estacionamento, veiculo, dataFormatada, valorPago);
    
    // Adicionar bilhete às listas do veículo e estacionamento
    veiculo.addBilhete(p);
    estacionamento.addBilhete(p);
    
    // Atualizar veículo e estacionamento no banco
    veiculoRep.atualizar(veiculo);
    estacionamentoRep.atualizar(estacionamento);
    
    bilheteRep.criar(p);
    bilheteRep.commit();
    bilheteRep.desconectar();
  }

  public static void alterarBilhete(Integer id, Date data, Double valorPago, Veiculo veiculo,
      Estacionamento estacionamento) throws Exception {
    bilheteRep.conectar();
    Bilhete p = bilheteRep.ler(id);
    if (p == null) {
      throw new Exception("alterar bilhete - bilhete inexistente:" + id);
    }

    p.setVeiculo(veiculo);
    p.setEstacionamento(estacionamento);
    p.setData(data);
    p.setValorpago(valorPago);

    bilheteRep.atualizar(p);
    bilheteRep.commit();
    bilheteRep.desconectar();
  }

  public static void apagarBilhete(Integer id) throws Exception {
    bilheteRep.conectar();
    Bilhete p = bilheteRep.ler(id);
    if (p == null) {
      bilheteRep.rollback();
      throw new Exception("excluir bilhete - bilhete inexistente:" + id);
    }

    veiculoRep.removeBilhete(p.getVeiculo(), p);
    estacionamentoRep.removeBilhete(p.getEstacionamento(), p);
    bilheteRep.apagar(p);

    bilheteRep.commit();
    bilheteRep.desconectar();
  }

  public static List<Bilhete> listarBilhete() {
    bilheteRep.conectar();
    List<Bilhete> lista = bilheteRep.listar();
    bilheteRep.desconectar();
    return lista;
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

  public static List<Veiculo> consultarVeiculoEstacionadoDataX(Date x, String nomeEstacionamento) {
    veiculoRep.conectar();
    List<Veiculo> veiculos = veiculoRep.lerVeiculoEstacionadoData(nomeEstacionamento, Fachada.inicioDoDia(x), Fachada.fimDoDia(x));
    veiculoRep.desconectar();
    return veiculos;
  }

  public static List<Veiculo> consultarVeiculoMaisXBilhetes(Integer x) {
    veiculoRep.conectar();

    List<Veiculo> veiculos = veiculoRep.lerVeiculoMaisBilhetes(x);
    veiculoRep.desconectar();
    return veiculos;
  }

  private static Date inicioDoDia(Date d) {
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

  private static Date fimDoDia(Date d) {
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);
    c.set(Calendar.MILLISECOND, 999);
    return c.getTime();
  }
  
}
