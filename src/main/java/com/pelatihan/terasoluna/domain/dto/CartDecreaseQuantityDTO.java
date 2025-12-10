package com.pelatihan.terasoluna.domain.dto;

import lombok.Data;

@Data
public class CartDecreaseQuantityDTO {
  private String itemId;
  private int quantity;
}
