package com.pelatihan.terasoluna.app.cart;

import com.pelatihan.terasoluna.domain.dto.AddItemToCartOutput;
import com.pelatihan.terasoluna.domain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart/")
public class CartRestController {
  @Autowired
  CartService cartService;

  @PostMapping("add-to-cart/{itemId}")
  public AddItemToCartOutput addToCart(@PathVariable("itemId") String itemId) throws Exception {
    AddItemToCartOutput output = cartService.addItemToCart(itemId, 1);
    if(!output.getErrorList().isEmpty()){
      throw new Exception("Quantity is exceeding limit");
    }

    return output;
  }

  @PostMapping("payment/")
  public String payment() {
    Integer totalAmount = cartService.totalAmountOfCart();

    return cartService.proceedPayment(totalAmount);
  }
}
