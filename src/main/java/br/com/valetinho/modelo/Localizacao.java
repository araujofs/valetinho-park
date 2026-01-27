package br.com.valetinho.modelo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Localizacao {
  private Double x;
  private Double y;

  public Localizacao() {
  }

  public Localizacao(Double x, Double y) {
    this.x = x;
    this.y = y;
  }

  public Double x() {
    return x;
  }

  public Double y() {
    return y;
  }

  @Override
  public String toString() {
    return "[x=" + x + ", y=" + y + "]";
  }
}