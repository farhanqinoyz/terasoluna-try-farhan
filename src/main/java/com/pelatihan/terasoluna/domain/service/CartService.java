package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.dto.ShowCartDTO;
import java.util.List;

public interface CartService {
  Integer addItemToCart(String itemId, int quantityIncoming);
  List<ShowCartDTO> showCart();
  Integer totalAmountOfCart();
  String proceedPayment(Integer totalAmount);
}
