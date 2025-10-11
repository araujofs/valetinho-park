package repositorio;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;

public class Util {
  private static ObjectContainer manager;

  public static ObjectContainer conectarBanco() {
    if (manager != null)
      return manager; 

    EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
    config.common().messageLevel(0);    

    config.common().objectClass(Bilhete.class).cascadeOnDelete(false);
    config.common().objectClass(Bilhete.class).cascadeOnUpdate(true);
    config.common().objectClass(Bilhete.class).cascadeOnActivate(true);

    config.common().objectClass(Veiculo.class).cascadeOnDelete(false);
    config.common().objectClass(Veiculo.class).cascadeOnDelete(false);
    config.common().objectClass(Veiculo.class).cascadeOnUpdate(true);
    
    config.common().objectClass(Estacionamento.class).cascadeOnActivate(true);
    config.common().objectClass(Estacionamento.class).cascadeOnUpdate(true);
    config.common().objectClass(Estacionamento.class).cascadeOnActivate(true);

    manager = Db4oEmbedded.openFile(config, "banco.db4o");

    return manager;
  }
      
  public static void desconectar() {
    if (manager != null) {
      manager.close();
      manager = null;
    }
  }
}
