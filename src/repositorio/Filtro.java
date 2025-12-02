package repositorio;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

import modelo.Veiculo;

public class Filtro implements Evaluation {
  private Integer quantidade;

  public Filtro(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public void evaluate(Candidate candidate) {
    Veiculo veic = (Veiculo) candidate.getObject();
    
    if (veic.getBilhetes().size() > quantidade) {
      candidate.include(true);
    } else {
      candidate.include(false);
    }
  }
}
