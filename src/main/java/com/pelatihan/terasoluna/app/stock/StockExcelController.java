package com.pelatihan.terasoluna.app.stock;

import com.pelatihan.terasoluna.app.common.excel.ExcelHelper;
import com.pelatihan.terasoluna.app.common.excel.OutputExcelMapDTO;
import com.pelatihan.terasoluna.app.stock.dto.StockExcelDto;
import com.pelatihan.terasoluna.domain.model.Stock;
import com.pelatihan.terasoluna.domain.service.StockService;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  @Autowired
  StockExportExcelCellCreator stockImportExcelCellCreator;

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

  @GetMapping("export/")
  public ResponseEntity<InputStreamResource> exportToExcel(){
    List<Stock> stockList = stockService.findAll();
    List<OutputExcelMapDTO> outputExcelMapDTOS = stockList.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());

    ByteArrayInputStream generatedExcel =
        excelHelper.generateExcel(outputExcelMapDTOS, stockImportExcelCellCreator);
    String filename = "users_report.xlsx";
    InputStreamResource file = new InputStreamResource(generatedExcel);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
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

  private StockExcelDto convertToDto(Stock stock){
    //TODO: separate import and upload DTO
    return new StockExcelDto(stock.getCodeBarang(), stock.getJumlah(), 0);
  }
}
