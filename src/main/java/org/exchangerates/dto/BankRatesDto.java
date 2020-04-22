package org.exchangerates.dto;

import org.exchangerates.model.BankType;

import java.math.BigDecimal;
import java.util.Map;

public class BankRatesDto {
  private Map<String, BigDecimal> rates;
  private BankType bankType;

  public BankRatesDto(Map<String, BigDecimal> rates, BankType bankType) {
    this.rates = rates;
    this.bankType = bankType;
  }

  public Map<String, BigDecimal> getRates() {
    return rates;
  }

  public void setRates(Map<String, BigDecimal> rates) {
    this.rates = rates;
  }

  public BankType getBankType() {
    return bankType;
  }

  public void setBankType(BankType bankType) {
    this.bankType = bankType;
  }
}
