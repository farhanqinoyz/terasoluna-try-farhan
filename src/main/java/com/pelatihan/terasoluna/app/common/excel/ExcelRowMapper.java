package com.pelatihan.terasoluna.app.common.excel;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelRowMapper {
  OutputExcelMapDTO mapRow(Row row);
}
