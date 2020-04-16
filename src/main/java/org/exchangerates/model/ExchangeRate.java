package org.exchangerates.model;

import java.math.BigDecimal;

public interface ExchangeRate {
  String getCurrencyCode();
  BigDecimal getRate();
}
