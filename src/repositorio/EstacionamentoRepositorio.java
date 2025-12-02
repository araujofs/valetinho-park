/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package repositorio;

import java.util.List;

import com.db4o.query.Query;

import modelo.Bilhete;
import modelo.Estacionamento;
import util.Util;

public class EstacionamentoRepositorio extends CRUDRepositorio<Estacionamento>{

  public Estacionamento ler(Object chave) {
    String nome = (String) chave;
    Query q = Util.getManager().query();
    q.constrain(Estacionamento.class);
    q.descend("nome").constrain(nome);
    List<Estacionamento> resultado = q.execute();
    if (resultado.size() > 0)
      return resultado.getFirst();
    else
      return null;
  }

  public Estacionamento ler(Integer id) {
    Query q = Util.getManager().query();
    q.constrain(Estacionamento.class);
    q.descend("id").constrain(id);
    List<Estacionamento> resultado = q.execute();
    if (resultado.size() > 0)
      return resultado.getFirst();
    else
      return null;
  }
  
  public void removeBilhete(Estacionamento estacionamento, Bilhete bilhete) {
    estacionamento.getBilhetes().remove(bilhete);

    this.atualizar(estacionamento);
  }
}
