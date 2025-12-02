package com.pelatihan.terasoluna.app.common.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelHelper {

  public List<OutputExcelMapDTO> readExcel(MultipartFile file, ExcelRowMapper excelRowMapper) {
    List<OutputExcelMapDTO> dataList = new ArrayList<>();

    try (InputStream is = file.getInputStream()) {
      // Create Workbook instance (Supports .xlsx)
      Workbook workbook = new XSSFWorkbook(is);

      // Get first sheet
      Sheet sheet = workbook.getSheetAt(0);

      // Iterate through rows
      for (Row row : sheet) {
        OutputExcelMapDTO mapResult = excelRowMapper.mapRow(row);
        dataList.add(mapResult);
      }
    } catch (IOException e) {
      throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
    }

    return dataList;
  }

  public ByteArrayInputStream generateExcel(
      List<OutputExcelMapDTO> dtoList, ExportExcelCellCreator exportExcelCellCreator
  ) {
    try (
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream()
    ) {
    Sheet sheet = workbook.createSheet("Import Result");
    Row headerRow = sheet.createRow(0);
      List<String> headers = exportExcelCellCreator.getHeaders();
      for (int i = 0; i < headers.size(); i++) {
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(headers.get(i));
    }

    int rowNumber = 1;
    for (OutputExcelMapDTO outputExcelMapDTO : dtoList){
      Row row = sheet.createRow(rowNumber);
      exportExcelCellCreator.insertCells(row, outputExcelMapDTO);
      rowNumber++;
    }

    workbook.write(out);

    return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}