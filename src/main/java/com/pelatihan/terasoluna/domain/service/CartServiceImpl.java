package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.dto.AddItemToCartOutput;
import com.pelatihan.terasoluna.domain.dto.ShowCartDTO;
import com.pelatihan.terasoluna.domain.repository.CartRepository;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
  @Autowired
  CartRepository cartRepository;
  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public AddItemToCartOutput addItemToCart(String itemId, int incomingQuantity){
    AddItemToCartOutput output = new AddItemToCartOutput();
    if(cartRepository.isIncomingQuantityExceedingStock(itemId, incomingQuantity)){
      output.setErrorList(Collections.singletonList("Stock is not enough!"));
      return output;
    }

    Integer totalQuantityAfterAdded = cartRepository.addItemToCart(itemId, incomingQuantity);
    output.setTotalQuantityAfterAdded(totalQuantityAfterAdded);

    return output;
  }

  @Override
  public List<ShowCartDTO> showCart() {
    return cartRepository.showCart();
  }

  @Override
  public Integer totalAmountOfCart() {
    return cartRepository.totalAmountOfCart();
  }

  @Override
  public String proceedPayment(Integer totalAmount) {
    log.info("proceed payment to kafka");
    try {
      kafkaTemplate.send("TERASOLUNA.PAYMENT", UUID.randomUUID().toString(), totalAmount.toString());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return "Failed";
    }

    return "Success";
  }
}
