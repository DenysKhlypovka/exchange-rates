package org.exchangerates.controller;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping("/bank-data/{bankType}")
  public BankRatesDto getData(@PathVariable String bankType) {
    return BankService.getDataFromBank(bankType);
  }
}