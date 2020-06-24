package org.exchangerates.dto;

import org.exchangerates.model.BankType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class BankRatesDto {
  private Map<String, BigDecimal> rates;
  private BankType bankType;
  private LocalDate date;

  public BankRatesDto(Map<String, BigDecimal> rates, BankType bankType, LocalDate date) {
    this.rates = rates;
    this.bankType = bankType;
    this.date = date;
  }

  public static BankRatesDto getEmptyInstance(LocalDate date, BankType bankType) {
    return new BankRatesDto(null, bankType, date);
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
