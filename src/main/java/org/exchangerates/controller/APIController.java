package org.exchangerates.controller;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.User;
import org.exchangerates.service.BankService;
import org.exchangerates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
  @Autowired
  private UserService userService;
  @Autowired
  private BankService bankService;

  @GetMapping("/bank-data/{bankType}")
  public BankRatesDto getData(@PathVariable String bankType) {
    return bankService.getDataFromBank(bankType);
  }

  @GetMapping("/password-check/{login}/{password}")
  public boolean test(@PathVariable String login, @PathVariable String password) {
    return userService.checkPassword(new User(login, password));
  }
}