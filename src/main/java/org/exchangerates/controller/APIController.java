package org.exchangerates.controller;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.dto.UserDto;
import org.exchangerates.service.BankService;
import org.exchangerates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class APIController {
  @Autowired
  private UserService userService;
  @Autowired
  private BankService bankService;

  @PostMapping("/bank-data/{bankType}")
  public BankRatesDto getData(@PathVariable String bankType, @RequestBody(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
    bankService.setBankType(bankType);
    return bankService.getRatesForDate(localDate);
  }

  @GetMapping("/password-check/{username}/{password}")
  public boolean checkPassword(@PathVariable String username, @PathVariable String password) {
    return userService.checkPassword(new UserDto(username, password));
  }
}