package org.exchangerates.controller;

import org.exchangerates.model.BankType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {

  @GetMapping("/home")
  public String getIndex(Model model) {
    model.addAttribute("banksList", BankType.values());
    return "index";
  }
}
