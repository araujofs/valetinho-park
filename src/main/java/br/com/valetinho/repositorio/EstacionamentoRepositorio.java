package br.com.valetinho.repositorio;

import java.util.List;

import br.com.valetinho.modelo.Estacionamento;
import br.com.valetinho.util.Util;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class EstacionamentoRepositorio extends CRUDRepositorio<Estacionamento> {

  @Override
  public Estacionamento ler(Object chave) {
    try {
      TypedQuery<Estacionamento> query = Util.getManager().createQuery(
          "SELECT e FROM Estacionamento e WHERE e.nome = :chave",
          Estacionamento.class);
      query.setParameter("chave", chave);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public List<Estacionamento> listar() {
    return Util.getManager().createQuery("SELECT e FROM Estacionamento e", Estacionamento.class).getResultList();
  }

  public Estacionamento ler(Integer id) {
    try {
      TypedQuery<Estacionamento> query = Util.getManager().createQuery("SELECT e FROM Estacionamento e WHERE e.id = :id",
          Estacionamento.class);
      query.setParameter("id", id);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
