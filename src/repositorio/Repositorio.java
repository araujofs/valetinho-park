package src.repositorio;

import com.db4o.ObjectContainer;

public class Repositorio {
  private static ObjectContainer manager;

  static {
    manager = Util.conectarBanco();

    Thread fecharConexao = new Thread(() -> Util.desconectar());
    Runtime.getRuntime().addShutdownHook(fecharConexao);
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
