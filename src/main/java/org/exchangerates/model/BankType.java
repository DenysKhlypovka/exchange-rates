package org.exchangerates.model;

import org.exchangerates.xml.model.cnb.CnbRatesTable;
import org.exchangerates.xml.model.hnb.HnbRatesTable;
import org.exchangerates.xml.model.nbp.NbpRatesTable;
import org.exchangerates.xml.model.nbu.NbuRatesTable;

public enum BankType {
  nbp(Currency.PLN, NbpRatesTable.class, "http://api.nbp.pl/api/exchangerates/tables/a?format=xml"),
  hnb(Currency.HRK, HnbRatesTable.class, "http://api.hnb.hr/tecajn/v2?format=xml"),
  nbu(Currency.UAH, NbuRatesTable.class, "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange"),
  cnb(Currency.CZK, CnbRatesTable.class, "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml");

  private Currency currency;
  private Class<? extends ExchangeRatesTable> tableClass;
  private String url;

  BankType(Currency currency) {
    this.currency = currency;
  }

  BankType(Currency currency, Class<? extends ExchangeRatesTable> tableClass, String url) {
    this.currency = currency;
    this.tableClass = tableClass;
    this.url = url;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Class<? extends ExchangeRatesTable> getTableClass() {
    return tableClass;
  }

  public String getUrl() {
    return url;
  }
}
