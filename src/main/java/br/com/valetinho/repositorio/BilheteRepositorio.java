/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package br.com.valetinho.repositorio;

import java.time.LocalDate;
import java.util.List;

import br.com.valetinho.modelo.Bilhete;
import br.com.valetinho.modelo.Veiculo;
import br.com.valetinho.util.Util;
import jakarta.persistence.TypedQuery;

public class BilheteRepositorio extends CRUDRepositorio<Bilhete> {

  @Override
  public Bilhete ler(Object chave) {
    TypedQuery<Bilhete> query = Util.getManager().createQuery("SELECT b FROM Bilhete b WHERE b.id = :chave",
        Bilhete.class);
    query.setParameter("chave", chave);
    return query.getSingleResult();
  }

  @Override
  public List<Bilhete> listar() {
    return Util.getManager().createQuery("SELECT b FROM Bilhete b", Bilhete.class).getResultList();
  }

  public List<Bilhete> lerBilhetePorVeiculoData(Veiculo veiculo, LocalDate data) {
    TypedQuery<Bilhete> query = Util.getManager().createQuery("SELECT b FROM Bilhete b WHERE b.veiculo = :veiculo AND b.data = :data",
        Bilhete.class);

    query.setParameter("veiculo", veiculo);
    query.setParameter("data", data);

    return query.getResultList();
  }

  public List<Bilhete> lerBilheteMaiorValorPago(Double valorpago) {
    TypedQuery<Bilhete> query = Util.getManager().createQuery("SELECT b FROM Bilhete b WHERE b.valorpago = :valorpago",
        Bilhete.class);

    query.setParameter("valorpago", valorpago);

    return query.getResultList();
  }

}
