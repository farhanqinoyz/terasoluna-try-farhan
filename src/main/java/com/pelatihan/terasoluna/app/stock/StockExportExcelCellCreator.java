package com.pelatihan.terasoluna.app.stock;

import com.pelatihan.terasoluna.app.common.excel.OutputExcelMapDTO;
import com.pelatihan.terasoluna.app.common.excel.ExportExcelCellCreator;
import com.pelatihan.terasoluna.app.stock.dto.StockExcelDto;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public class StockExportExcelCellCreator implements ExportExcelCellCreator {

  @Override
  public void insertCells(Row row, OutputExcelMapDTO dto) {
    boolean isCorrectInstance = dto instanceof StockExcelDto;
    if(!isCorrectInstance){
      return;
    }

    StockExcelDto stockExcelDto = (StockExcelDto) dto;

    Cell cellCode = row.createCell(0);
    cellCode.setCellValue(stockExcelDto.getItemCode());

    Cell cellAmount = row.createCell(1);
    cellAmount.setCellValue(stockExcelDto.getQuantity());
  }

  @Override
  public List<String> getHeaders() {
    return Arrays.asList("Code Barang", "Jumlah");
  }
}
