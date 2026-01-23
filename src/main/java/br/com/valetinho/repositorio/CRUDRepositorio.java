/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persist�ncia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package br.com.valetinho.repositorio;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import br.com.valetinho.util.Util;

public abstract class CRUDRepositorio<T> {
  public void conectar() {
    Util.conectarBanco();
  }

  public void desconectar() {
    Util.desconectar();
  }

  public void criar(T objeto) {
    Util.getManager().persist(objeto);
  }

  public void atualizar(T objeto) {
    Util.getManager().merge(objeto);
  }

  public abstract T ler(Object chave);

  public abstract List<T> listar();

  public void apagar(T objeto) {
    Util.getManager().remove(objeto);
  }

  public void begin() {
    if (!Util.getManager().getTransaction().isActive()) {
      Util.getManager().getTransaction().begin();
    }
  }

  public void commit() {
    if (!Util.getManager().getTransaction().isActive()) {
      Util.getManager().getTransaction().commit();
    }
  }

  public void rollback() {
    if (!Util.getManager().getTransaction().isActive()) {
      Util.getManager().getTransaction().rollback();
    }
  }

  public void resetID() {
    // resetar coluna id em 1
    // deve ser chamado de dentro de uma transação, antes de um commit
    @SuppressWarnings("unchecked")
    Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    String classe = type.getSimpleName().toLowerCase();

    try {
      String nomesgbd = "";
      Connection conn = getConnection();

      if (conn == null)
        throw new SQLException("resetar id - falha ao obter conexao");

      // sql para resetar depende do SGBD
      if (nomesgbd.equalsIgnoreCase("postgresql"))
        Util.getManager().createNativeQuery("ALTER SEQUENCE " + classe + "_id_seq RESTART WITH 1").executeUpdate();
      else if (nomesgbd.equalsIgnoreCase("mysql"))
        Util.getManager().createNativeQuery("ALTER TABLE " + classe + " AUTO_INCREMENT = 1").executeUpdate();
      else
        throw new SQLException("resetar id - sgbd desconhecido " + nomesgbd);

    } catch (SQLException e) {
      throw new RuntimeException("resetar id - " + e.getMessage());
    }
  }

  public static Connection getConnection() {
    try {
      Session session = Util.getManager().unwrap(Session.class);
      SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
      Connection conn = sfi.getJdbcServices().getBootstrapJdbcConnectionAccess().obtainConnection();
      return conn;
    } catch (Exception ex) {
      return null;
    }
  }
}
