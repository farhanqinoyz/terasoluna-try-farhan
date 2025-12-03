package com.pelatihan.terasoluna.app.stock;

import com.pelatihan.terasoluna.app.stock.dto.StockMenuDto;
import com.pelatihan.terasoluna.domain.model.Stock;
import com.pelatihan.terasoluna.domain.service.StockService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stock/menu")
public class StockMenuController {
  @Autowired
  StockService stockService;

  private static final Logger logger = LoggerFactory
      .getLogger(StockMenuController.class);
  @GetMapping("/")
  public String home(Model model) {
    List<Stock> stockList = stockService.findAll();
    List<StockMenuDto> stockMenuDtoList = stockList.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
    model.addAttribute("stockMenuDtoList", stockMenuDtoList);

    return "stock/menu";
  }

  private StockMenuDto convertToDto(Stock stock){
    return new StockMenuDto(stock.getCodeBarang(), stock.getNama(), stock.getJumlah(), stock.getHarga());
  }
}
