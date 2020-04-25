package org.exchangerates.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EncodingService {
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  private void init() {
    passwordEncoder = new BCryptPasswordEncoder();
  }

  public String encode(String input) {
    return passwordEncoder.encode(input);
  }

  public boolean checkPassword(String password, String hash) {
    return passwordEncoder.matches(password, hash);
  }
}
