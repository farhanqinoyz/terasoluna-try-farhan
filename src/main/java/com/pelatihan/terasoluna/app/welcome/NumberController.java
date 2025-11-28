package com.pelatihan.terasoluna.app.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("number")
public class NumberController {
  private static final Logger logger = LoggerFactory
      .getLogger(NumberController.class);

  /**
   * Simply selects the home view to render by returning its name.
   */
  @GetMapping("/")
  public String number() {
    logger.info("You are triggering Number Controller");

    return "number/number";
  }
}
