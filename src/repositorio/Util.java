package repositorio;

import java.lang.reflect.Field;
import java.util.List;
import java.util.TreeMap;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.events.EventRegistry;
import com.db4o.events.EventRegistryFactory;
import com.db4o.query.Query;

import modelo.Bilhete;
import modelo.Estacionamento;
import modelo.Veiculo;

public class Util {
  private static ObjectContainer manager;

  public static ObjectContainer conectarBanco() {
    if (manager == null) {
      EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
      config.common().messageLevel(0);

      // cascadeDelete como false para que estacionamento e veiculo nao sejam excluidos ao excluir um bilhete
      config.common().objectClass(Bilhete.class).cascadeOnDelete(false);
      config.common().objectClass(Bilhete.class).cascadeOnUpdate(true);
      config.common().objectClass(Bilhete.class).cascadeOnActivate(true);

      // cascadeDelete como true para que bilhetes sejam excluidos tambem
      config.common().objectClass(Veiculo.class).cascadeOnDelete(true);
      config.common().objectClass(Veiculo.class).cascadeOnUpdate(true);
      config.common().objectClass(Veiculo.class).cascadeOnActivate(true);

      // cascadeDelete como true para que bilhetes sejam excluidos tambem
      config.common().objectClass(Estacionamento.class).cascadeOnDelete(true);
      config.common().objectClass(Estacionamento.class).cascadeOnUpdate(true);
      config.common().objectClass(Estacionamento.class).cascadeOnActivate(true);

      manager = Db4oEmbedded.openFile(config, "banco.db4o");
    }

    ControleID.ativar(manager);

    return manager;
  }

  public static void desconectar() {
    if (manager != null) {
      manager.close();
      manager = null;
    }
  }
}

class ControleID {
  private static EmbeddedObjectContainer bancoSequencias;
  private static TreeMap<String, SequenciaID> memoriaSequencias = new TreeMap<>();
  private static boolean salvar;

  public static void ativar(ObjectContainer manager) {
    bancoSequencias = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "sequencias.db4o");

    lerSequenciasID();

    EventRegistry events = EventRegistryFactory.forObjectContainer(manager);

    events.creating().addListener((_, args) -> {
      try {
        Object objeto = args.object();

        Field campo = objeto.getClass().getDeclaredField("id");

        if (campo != null) {
          String nomeClasse = objeto.getClass().getName();
          SequenciaID sequencia = obterSequenciaID(nomeClasse);

          sequencia.incrementarID();
          campo.setAccessible(true);
          campo.setInt(objeto, sequencia.getUltimoId());
          memoriaSequencias.put(nomeClasse, sequencia);
          salvar = true;
        }


      } catch (Exception e) {
      }
    });

    events.created().addListener((_, _) -> {
      salvarSequenciasID();
    });

    events.closing().addListener((_, _) -> {
      if (bancoSequencias != null && !bancoSequencias.ext().isClosed()) {
        bancoSequencias.close();
      }
    });
  }

  public static void lerSequenciasID() {
    Query query = bancoSequencias.query();
    query.constrain(SequenciaID.class);

    List<SequenciaID> resultado = query.execute();

    for (SequenciaID seq : resultado) {
      memoriaSequencias.put(seq.getNomeClasse(), seq);
    }

    salvar = false;
  }

  public static SequenciaID obterSequenciaID(String nomeClasse) {
    return memoriaSequencias.containsKey(nomeClasse) ? memoriaSequencias.get(nomeClasse) : new SequenciaID(nomeClasse);
  }

  public static void salvarSequenciasID() {
    if (!salvar)
      return;

    for (SequenciaID seq : memoriaSequencias.values()) {
      if (seq.isModificado()) {
        bancoSequencias.store(seq);
        seq.setModificado(false);
      }
    }

    bancoSequencias.commit();
  }

}

class SequenciaID {
  private String nomeClasse;
  private Integer ultimoId = 0;
  transient private boolean modificado = false;

  public SequenciaID(String nomeClasse) {
    this.nomeClasse = nomeClasse;
  }

  public String getNomeClasse() {
    return nomeClasse;
  }

  public Integer getUltimoId() {
    return ultimoId;
  }

  public boolean isModificado() {
    return modificado;
  }

  public void setModificado(boolean modificado) {
    this.modificado = modificado;
  }

  public void incrementarID() {
    ultimoId++;
    setModificado(true);
  }
}
