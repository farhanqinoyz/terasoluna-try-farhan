package com.pelatihan.terasoluna.domain.model;

public class Stock {
  private String codeBarang;
  private int jumlah;

  public Stock() {
  }

  public String getCodeBarang() {
    return codeBarang;
  }

  public void setCodeBarang(String codeBarang) {
    this.codeBarang = codeBarang;
  }

  public int getJumlah() {
    return jumlah;
  }

  public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
  }

  @Override
  public String toString() {
    return "Stock{" +
        "codeBarang='" + codeBarang + '\'' +
        ", jumlah=" + jumlah +
        '}';
  }
}
