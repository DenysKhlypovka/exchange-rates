package org.exchangerates.controller;

import org.exchangerates.factory.BankServiceFactory;
import org.exchangerates.model.BankType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping("/{type}")
  public String getData(@PathVariable BankType type) {
    return BankServiceFactory.getService(type).test().toString();
  }
}