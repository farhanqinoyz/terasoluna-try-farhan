package com.pelatihan.terasoluna.app.stock.dto;

import java.math.BigInteger;

public class StockMenuDto {
  private String codeBarang;
  private String nama;
  private int jumlah;
  private BigInteger harga;

  public String getCodeBarang() {
    return codeBarang;
  }

  public void setCodeBarang(String codeBarang) {
    this.codeBarang = codeBarang;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public int getJumlah() {
    return jumlah;
  }

  public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
  }

  public BigInteger getHarga() {
    return harga;
  }

  public void setHarga(BigInteger harga) {
    this.harga = harga;
  }

  public StockMenuDto(String codeBarang, String nama, int jumlah, BigInteger harga) {
    this.codeBarang = codeBarang;
    this.nama = nama;
    this.jumlah = jumlah;
    this.harga = harga;
  }
}
