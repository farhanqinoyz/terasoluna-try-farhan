package com.pelatihan.terasoluna.app.cart;

import com.pelatihan.terasoluna.domain.dto.ShowCartDTO;
import com.pelatihan.terasoluna.domain.model.Cart;
import com.pelatihan.terasoluna.domain.service.CartService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart/")
public class CartController {

  @Autowired
  CartService cartService;

  @GetMapping("/")
  public String showCart(Model model) {
    List<ShowCartDTO> cartList = cartService.showCart();
    int amountTotal = cartList.stream()
        .mapToInt(ShowCartDTO::getSubTotal)
        .sum();

    model.addAttribute("cartList", cartList);
    model.addAttribute("amountTotal", amountTotal);

    return "cart/list";
  }
}
