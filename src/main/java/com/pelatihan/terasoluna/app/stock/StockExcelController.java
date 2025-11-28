package com.pelatihan.terasoluna.app.stock;

import com.pelatihan.terasoluna.app.common.excel.ExcelHelper;
import com.pelatihan.terasoluna.app.common.excel.ExcelRowMapper;
import com.pelatihan.terasoluna.app.common.excel.OutputExcelMapDTO;
import com.pelatihan.terasoluna.app.stock.dto.StockExcelDto;
import com.pelatihan.terasoluna.domain.model.Stock;
import com.pelatihan.terasoluna.domain.service.StockService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("stock/excel")
public class StockExcelController {

  @Autowired
  StockService stockService;

  @Autowired
  ExcelHelper excelHelper;

  @Autowired
  StockExcelRowMapper stockExcelRowMapper;

  private static final Logger logger = LoggerFactory
      .getLogger(StockExcelController.class);
  @GetMapping("/")
  public String home() {
    return "stock/page";
  }

  @PostMapping("upload/")
  public String upload(@RequestParam("excelFile") MultipartFile file){
    List<OutputExcelMapDTO> outputExcelMapDTOS = excelHelper.readExcel(file, stockExcelRowMapper);
    List<Stock> domainList = outputExcelMapDTOS.stream()
        .map(this::convertToDomain)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    stockService.uploadExcelFileToDatabase(domainList);

    return "stock/page";
  }

  private Stock convertToDomain(OutputExcelMapDTO dto){
    if(dto instanceof StockExcelDto){
      StockExcelDto stockExcelDto = (StockExcelDto) dto;
      Stock stock = new Stock();

      stock.setCodeBarang(stockExcelDto.getItemCode());
      stock.setJumlah(stockExcelDto.getQuantity());

      return stock;
    }

    return null;
  }
}
