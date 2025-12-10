package com.pelatihan.terasoluna.domain.service;

import com.pelatihan.terasoluna.domain.dto.CartDecreaseQuantityDTO;
import com.pelatihan.terasoluna.domain.repository.CartRepository;
import com.pelatihan.terasoluna.domain.repository.StockRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Configuration
@EnableKafka
public class PaymentListenerService {
  @Autowired
  CartRepository cartRepository;
  @Autowired
  private StockRepository stockRepository;

  @KafkaListener(topics = "TERASOLUNA.PAYMENT", group = "terasoluna.group.id")
  @Transactional
  public void handleTaskEvent(String message) {
    log.info("====================================");
    log.info("EVENT DITERIMA: Pembayaran Berhasil!");
    log.info("Total Amount: {}", Integer.valueOf(message));
    log.info("====================================");

    List<CartDecreaseQuantityDTO> decreaseQuantityDTOList = cartRepository.getDecreaseQuantityDTO();
    cartRepository.deleteAll();
    stockRepository.decreaseStockByCart(decreaseQuantityDTOList);
  }

}
