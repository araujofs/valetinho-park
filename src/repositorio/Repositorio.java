package repositorio;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Identificavel;
import modelo.Veiculo;

public class Repositorio {
  private static ObjectContainer manager;

  static {
    manager = Util.conectarBanco();

    Thread fecharConexao = new Thread(() -> Util.desconectar());
    Runtime.getRuntime().addShutdownHook(fecharConexao);
  }

  public static Veiculo lerPlaca(String placa) {
    Query query = manager.query();

    query.constrain(Veiculo.class);
    query.descend("placa").constrain(placa);

    List<Veiculo> resultado = query.execute();
    return !resultado.isEmpty() ? resultado.getFirst() : null;
  }

  public static <T extends Identificavel> T lerId(Integer id, Class<T> classe) {
    Query query = manager.query();

    query.constrain(classe);
    query.descend("id").constrain(id);

    List<T> resultado = query.execute();

    return !resultado.isEmpty() ? resultado.getFirst() : null;
  }

  public static <T> List<T> lerTodos(Class<T> classe) {
    Query query = manager.query();
    query.constrain(classe);

    return query.execute();
  }

  public static <T> void gravar(T object) {
    manager.store(object);
    manager.commit();
  }

  public static <T> void deletar(T object) {
    manager.delete(object);
    manager.commit();
  }
}
