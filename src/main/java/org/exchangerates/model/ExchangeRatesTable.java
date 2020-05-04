package org.exchangerates.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExchangeRatesTable {
  LocalDate getDate();
  List<? extends ExchangeRate> getRates();
  default BigDecimal getRateOfCurrency(Currency currency) {
    return getRates().stream().filter(rate -> rate.getCurrencyCode().equals(currency.name())).map(ExchangeRate::getRate).findAny().orElse(BigDecimal.ZERO);
  }
}
