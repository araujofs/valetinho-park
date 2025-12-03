/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package repositorio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.db4o.query.Query;

import modelo.Bilhete;
import modelo.Veiculo;
import util.Util;

public class BilheteRepositorio extends CRUDRepositorio<Bilhete>{

  public Bilhete ler(Object chave) {
    Integer id = (Integer) chave;
    Query q = Util.getManager().query();
    q.constrain(Bilhete.class);
    q.descend("id").constrain(id);
    
    List<Bilhete> resultado = q.execute();
    if (resultado.size() > 0)
      return resultado.getFirst();
    else
      return null;
  }

  public Bilhete lerBilhetePorVeiculoData(Veiculo veiculo, Date data) {
    Query q = Util.getManager().query();
    Calendar cal = Calendar.getInstance();
    cal.setTime(data);

    cal.add(Calendar.HOUR_OF_DAY, -1);
    Date dataUmaHoraMenos = cal.getTime();

    cal.add(Calendar.HOUR_OF_DAY, +2);
    Date dataUmaHoraMais = cal.getTime();

    q.constrain(Bilhete.class);
    q.descend("veiculo").constrain(veiculo);
    q.descend("data").constrain(dataUmaHoraMenos).greater();
    q.descend("data").constrain(dataUmaHoraMais).smaller();

    List<Bilhete> resultado = q.execute();
    if (resultado.size() > 0) {
      return resultado.getFirst();
    }

    return null;
  }

  public List<Bilhete> lerBilheteMaiorValorPago(Double valorpago) {
    Query query = Util.getManager().query();

    query.constrain(Bilhete.class);
    query.descend("valorpago").constrain(valorpago).greater();

    return query.execute();
  }
}
