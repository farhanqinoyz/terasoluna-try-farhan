package com.pelatihan.terasoluna.app.stock;

import com.pelatihan.terasoluna.app.common.excel.ExcelRowMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import com.pelatihan.terasoluna.app.stock.dto.StockExcelDto;
import org.springframework.stereotype.Component;

@Component
public class StockExcelRowMapper implements ExcelRowMapper {

  public StockExcelDto mapRow(Row row) {
    int rowNum = row.getRowNum();

    Cell codeBarangCell = row.getCell(0);
    Cell quantityCell = row.getCell(1);

    String codeBarangStringValue = codeBarangCell.getStringCellValue();
    int quantity = (int) quantityCell.getNumericCellValue();

    return new StockExcelDto(codeBarangStringValue, quantity, rowNum);
  }
}