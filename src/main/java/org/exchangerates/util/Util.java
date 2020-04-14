package org.exchangerates.util;

import org.exchangerates.model.Currency;

import java.util.Objects;

public class Util {
  public String currencyToString(Currency currency) {
    return Objects.nonNull(currency) ? currency.name() : "";
  }
  public Currency stringToCurrency(String currencyStr) throws Exception {
    try {
      return Currency.valueOf(currencyStr);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
