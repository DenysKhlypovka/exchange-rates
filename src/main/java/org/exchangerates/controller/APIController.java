package org.exchangerates.controller;

import org.exchangerates.factory.BankServiceFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping("/test2/{type}")
  public String getData(@PathVariable String type) {
    return BankServiceFactory.getService(type).getData();
  }
}