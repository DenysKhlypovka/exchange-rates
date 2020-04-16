package org.exchangerates.controller;

import org.exchangerates.service.BankService;
import org.exchangerates.model.BankType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping("/bank-data/{bankType}")
  public String getData(@PathVariable String bankType) {
    return BankService.getDataFromBank(Enum.valueOf(BankType.class, bankType)).toString();
  }
}