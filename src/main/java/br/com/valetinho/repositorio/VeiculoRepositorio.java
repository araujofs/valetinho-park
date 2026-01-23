package br.com.valetinho.repositorio;

import java.time.LocalDate;
import java.util.List;

import br.com.valetinho.modelo.Veiculo;
import br.com.valetinho.util.Util;
import jakarta.persistence.TypedQuery;

public class VeiculoRepositorio extends CRUDRepositorio<Veiculo> {

  @Override
  public Veiculo ler(Object chave) {
    TypedQuery<Veiculo> query = Util.getManager().createQuery("SELECT v FROM Veiculo v WHERE v.placa = :chave",
        Veiculo.class);
    query.setParameter("placa", chave);
    return query.getSingleResult();
  }

  @Override
  public List<Veiculo> listar() {
    return Util.getManager().createQuery("SELECT v FROM Veiculo v", Veiculo.class).getResultList();
  }

  public List<Veiculo> lerVeiculoEstacionadoData(String estacionamentoNome, LocalDate data) {
    TypedQuery<Veiculo> query = Util.getManager().createQuery(
        "SELECT DISTINCT v FROM Veiculo v JOIN v.bilhetes b WHERE b.data = :data AND b.estacionamento.nome = :nome",
        Veiculo.class);
    query.setParameter("nome", estacionamentoNome);
    query.setParameter("data", data);
    return query.getResultList();
  }

  public List<Veiculo> lerVeiculoMaisBilhetes(Integer quantidadeBilhetes) {
    TypedQuery<Veiculo> query = Util.getManager().createQuery("SELECT v FROM Veiculo v WHERE SIZE(v.bilhetes) > :qtd",
        Veiculo.class);
    query.setParameter("qtd", quantidadeBilhetes);
    return query.getResultList();
  }
}