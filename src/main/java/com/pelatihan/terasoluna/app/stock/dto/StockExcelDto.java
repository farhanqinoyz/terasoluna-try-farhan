package com.pelatihan.terasoluna.app.stock.dto;

import com.pelatihan.terasoluna.app.common.excel.OutputExcelMapDTO;

public class StockExcelDto implements OutputExcelMapDTO {

  private String itemCode;
  private int quantity;
  private int rowNum;

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getRowNum() {
    return rowNum;
  }

  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }

  public StockExcelDto(String itemCode, int quantity, int rowNum) {
    this.itemCode = itemCode;
    this.quantity = quantity;
    this.rowNum = rowNum;
  }
}