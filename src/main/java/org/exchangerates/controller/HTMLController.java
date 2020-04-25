package org.exchangerates.controller;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.model.BankType;
import org.exchangerates.model.Currency;
import org.exchangerates.model.User;
import org.exchangerates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HTMLController {
  @Autowired
  UserService userService;

  @GetMapping("/home")
  public String getIndex(Model model) {
    model.addAttribute("banks", BankType.values());
    model.addAttribute("currencies", Currency.values());
    return "index";
  }

  @GetMapping("/user/registration")
  public String getData(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/user/create-user")
  public String getData(@ModelAttribute User user) throws InvalidLoginDataException {
    userService.createUser(user);
    return "create-user";
  }
}
