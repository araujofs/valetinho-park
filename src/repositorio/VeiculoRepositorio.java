/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package repositorio;

import java.util.Date;
import java.util.List;

import com.db4o.query.Query;

import modelo.Bilhete;
import modelo.Veiculo;
import util.Util;

public class VeiculoRepositorio extends CRUDRepositorio<Veiculo> {

  public Veiculo ler(Object chave) {
    String placa = (String) chave;
    Query q = Util.getManager().query();
    q.constrain(Veiculo.class);
    q.descend("placa").constrain(placa);
    List<Veiculo> resultado = q.execute();
    if (resultado.size() > 0)
      return resultado.getFirst();
    else
      return null;
  }

  public void removeBilhete(Veiculo veiculo, Bilhete bilhete) {
    veiculo.getBilhetes().remove(bilhete);

    this.atualizar(veiculo);
  }

  public List<Veiculo> lerVeiculoEstacionadoData(String estacionamentoNome, Date inicioDia, Date fimDia) {
    Query query = Util.getManager().query();

    query.constrain(Veiculo.class);
    query.descend("listaBilhete").descend("estacionamento").descend("nome").constrain(estacionamentoNome);
    query.descend("listaBilhete").descend("data").constrain(inicioDia).greater();
    query.descend("listaBilhete").descend("data").constrain(fimDia).smaller();

    return query.execute();
  }

  public List<Veiculo> lerVeiculoMaisBilhetes(Integer quantidadeBilhetes) {
    Query query = Util.getManager().query();

    query.constrain(Veiculo.class);
    query.constrain(new Filtro(quantidadeBilhetes));

    return query.execute();
  }


}