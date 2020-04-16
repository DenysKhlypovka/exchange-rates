package org.exchangerates.model;

import java.util.Date;
import java.util.List;

public interface ExchangeRatesTable {
  Date getDate();
  List<? extends ExchangeRate> getRates();
}
