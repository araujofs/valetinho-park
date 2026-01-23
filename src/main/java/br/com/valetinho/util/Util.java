package br.com.valetinho.util;

/**********************************
 * IFPB - Curso Superior de Sistemas para Internet
 * Persistência de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
  private static EntityManagerFactory factory;
  private static EntityManager manager;

  public static void conectarBanco() {
    getManager();
  }

  public static EntityManager getManager() {
    if (manager != null && manager.isOpen())
      return manager;

    if (factory == null) {
      factory = Persistence.createEntityManagerFactory("hibernate-postgresql");
    }

    manager = factory.createEntityManager();

    return manager;
  }

  public static void desconectar() {
    if (manager != null && manager.isOpen()) {
      manager.close();
      manager = null;
    }

    if (factory != null && factory.isOpen()) {
      factory.close();
      factory = null;
    }
  }
}