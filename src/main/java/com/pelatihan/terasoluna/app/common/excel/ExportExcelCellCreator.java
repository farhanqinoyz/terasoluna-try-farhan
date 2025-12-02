package com.pelatihan.terasoluna.app.common.excel;

import java.util.List;
import org.apache.poi.ss.usermodel.Row;

public interface ExportExcelCellCreator {
  void insertCells(Row row, OutputExcelMapDTO dto);
  List<String> getHeaders();
}
