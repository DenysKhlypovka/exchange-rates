package org.exchangerates.service;

import org.exchangerates.model.BankType;

import java.math.BigDecimal;

public abstract class BankService {
  protected String baseUrl;
  protected BankType type;
  protected String URL_XML_FORMAT = "?format=xml";

  public BankType getType() {
    return type;
  }

  public abstract BigDecimal test();
}
