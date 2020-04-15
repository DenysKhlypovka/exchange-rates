package org.exchangerates.controller;

import org.exchangerates.factory.BankServiceFactory;
import org.exchangerates.model.BankType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping("/bank-data/{bankType}")
  public String getData(@PathVariable String bankType) {
    return BankServiceFactory.getService(Enum.valueOf(BankType.class, bankType)).test().toString();
  }
}