package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.model.Stock;
import com.pelatihan.terasoluna.domain.repository.StockRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  StockRepository stockRepository;

  @Override
  public void uploadExcelFileToDatabase(List<Stock> stocks) {
    List<Stock> mergedStockQuantities = this.mergeStockQuantities(stocks);

    List<String> itemCodeId = mergedStockQuantities.stream()
        .map(Stock::getCodeBarang)
        .collect(Collectors.toList());

    List<Stock> existingStocksInDb = stockRepository.findAllByItemCodeIn(itemCodeId);

    Map<String, Stock> dbMap = existingStocksInDb.stream()
        .collect(Collectors.toMap(Stock::getCodeBarang, stock -> stock));

    List<Stock> toInsert = new ArrayList<>();
    List<Stock> toUpdate = new ArrayList<>();

    for (Stock incoming : mergedStockQuantities) {
      if (dbMap.containsKey(incoming.getCodeBarang())) {
        Stock dbStock = dbMap.get(incoming.getCodeBarang());
        dbStock.setJumlah(dbStock.getJumlah() + incoming.getJumlah());
        toUpdate.add(dbStock);
      } else {
        toInsert.add(incoming);
      }
    }

    if (!toInsert.isEmpty()) {
      stockRepository.batchInsert(toInsert);
    }
    if (!toUpdate.isEmpty()) {
      stockRepository.batchUpdate(toUpdate);
    }
  }

  private List<Stock> mergeStockQuantities(List<Stock> rawStocks) {
    Map<String, Stock> map = new HashMap<>();

    for (Stock stock : rawStocks) {
      map.merge(stock.getCodeBarang(), stock, (existing, incoming) -> {
        existing.setJumlah(existing.getJumlah() + incoming.getJumlah());

        return existing;
      });
    }

    return new ArrayList<>(map.values());
  }
}
