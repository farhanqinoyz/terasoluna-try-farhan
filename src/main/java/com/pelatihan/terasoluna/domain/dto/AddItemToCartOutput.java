package com.pelatihan.terasoluna.domain.dto;

import java.util.List;
import lombok.Data;

@Data
public class AddItemToCartOutput {
  private Integer totalQuantityAfterAdded;
  private List<String> errorList;
}
