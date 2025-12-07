package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  CartRepository cartRepository;

  @Override
  public Integer addItemToCart(String itemId, int incomingQuantity){
    return cartRepository.addItemToCart(itemId, incomingQuantity);
  }
}
