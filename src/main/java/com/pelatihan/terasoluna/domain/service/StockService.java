package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.model.Stock;
import java.util.List;

public interface StockService {

  void uploadExcelFileToDatabase(List<Stock> stocks);
  List<Stock> findAll();

}
