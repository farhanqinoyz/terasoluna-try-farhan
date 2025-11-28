package com.pelatihan.terasoluna.domain.repository;

import com.pelatihan.terasoluna.domain.model.Stock;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockRepository {
  boolean isCodeBarangExist(String codeBarang);
  void insertStock(Stock stock);
  void updateStock(Stock stock);
  void batchInsert(List<Stock> stock);
  void batchUpdate(List<Stock> stock);
  List<Stock> findAllByItemCodeIn(List<String> codeBarangIds);
}
