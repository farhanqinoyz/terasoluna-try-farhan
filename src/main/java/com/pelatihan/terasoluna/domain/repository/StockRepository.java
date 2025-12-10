package com.pelatihan.terasoluna.domain.repository;

import com.pelatihan.terasoluna.domain.dto.CartDecreaseQuantityDTO;
import com.pelatihan.terasoluna.domain.model.Stock;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockRepository {
  boolean isCodeBarangExist(String codeBarang);
  void insertStock(Stock stock);
  void updateStock(Stock stock);
  void batchInsert(List<Stock> stock);
  void batchUpdate(List<Stock> stock);
  List<Stock> findAllByItemCodeIn(@Param("codeBarangIds") List<String> codeBarangIds);
  List<Stock> findAll();
  void decreaseStockByCart(List<CartDecreaseQuantityDTO> cartDecreaseQuantityDTOList);
}
