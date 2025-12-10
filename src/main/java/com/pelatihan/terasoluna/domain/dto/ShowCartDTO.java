package com.pelatihan.terasoluna.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowCartDTO {
  private String itemId;
  private String itemName;
  private int quantity;
  private int price;
  private int subTotal;
}
