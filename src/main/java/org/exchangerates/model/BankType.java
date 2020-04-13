package org.exchangerates.model;

public enum BankType {
  nbp(Currency.PLN), hnb(Currency.HRK), nbu(Currency.UAH), cnb(Currency.CZK);

  private Currency currency;

  BankType(Currency currency) {
    this.currency = currency;
  }

  public Currency getCurrency() {
    return currency;
  }
}
