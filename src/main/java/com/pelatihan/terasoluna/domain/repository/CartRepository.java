package com.pelatihan.terasoluna.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartRepository {
  Integer addItemToCart(@Param("itemId") String itemId, @Param("incomingQuantity") int incomingQuantity);
}
