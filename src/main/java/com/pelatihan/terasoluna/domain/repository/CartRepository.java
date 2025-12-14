package com.pelatihan.terasoluna.domain.repository;

import com.pelatihan.terasoluna.domain.dto.CartDecreaseQuantityDTO;
import com.pelatihan.terasoluna.domain.dto.ShowCartDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartRepository {
  Integer addItemToCart(@Param("itemId") String itemId, @Param("incomingQuantity") int incomingQuantity);
  List<ShowCartDTO> showCart();
  Integer totalAmountOfCart();
  List<CartDecreaseQuantityDTO> getDecreaseQuantityDTO();
  void deleteAll();
  Boolean isIncomingQuantityExceedingStock(@Param("itemId") String itemId, @Param("incomingQuantity") int incomingQuantity);
}
