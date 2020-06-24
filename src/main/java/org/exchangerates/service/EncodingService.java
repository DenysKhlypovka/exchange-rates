package org.exchangerates.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodingService {
  private final PasswordEncoder passwordEncoder;

  public EncodingService() {
    passwordEncoder = new BCryptPasswordEncoder();
  }

  public PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public String encode(String input) {
    return passwordEncoder.encode(input);
  }

  public boolean checkPassword(String password, String hash) {
    return passwordEncoder.matches(password, hash);
  }
}
