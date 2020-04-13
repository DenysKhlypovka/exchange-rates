package org.exchangerates.service;

import org.exchangerates.model.BankType;

public abstract class AbstractBankService {
  protected BankType type;
  protected String baseUrl;
  protected AbstractParser parser;

  public abstract String getData();

  public BankType getType() {
    return type;
  }
}
