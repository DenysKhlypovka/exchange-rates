package org.exchangerates.exception;

public class BankNotFoundException extends Exception {
  public BankNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}