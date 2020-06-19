package org.exchangerates.controller;

import org.exchangerates.dto.UserDto;
import org.exchangerates.model.BankType;
import org.exchangerates.model.Currency;
import org.exchangerates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

  @GetMapping("/user/register")
  public String getData(Model model) {
    model.addAttribute("userDto", new UserDto());
    return "register";
  }

  @PostMapping("/user/create-user")
  public String getData(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "register";
    }
    if (userService.create(userDto)) {
      return "create-user";
    } else {
      return "create-user-error";
    }
  }
}
