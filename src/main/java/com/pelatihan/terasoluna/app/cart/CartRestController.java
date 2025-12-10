package com.pelatihan.terasoluna.app.cart;

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
  public Integer addToCart(@PathVariable("itemId") String itemId) {
    /*
   TODO:
    add validation, receives a itemId, does it exist or not
    check is it exist or not
     */


    return cartService.addItemToCart(itemId, 1);
  }

  @PostMapping("payment/")
  public String payment() {
    Integer totalAmount = cartService.totalAmountOfCart();

    return cartService.proceedPayment(totalAmount);
  }
}
