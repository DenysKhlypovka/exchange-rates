package org.exchangerates.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.exchangerates.util.DateFormat;
import org.exchangerates.xml.model.cnb.CnbRatesTable;
import org.exchangerates.xml.model.hnb.HnbRatesTable;
import org.exchangerates.xml.model.nbp.NbpRatesTable;
import org.exchangerates.xml.model.nbu.NbuRatesTable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BankType {
  NBP(Currency.PLN, NbpRatesTable.class, "http://api.nbp.pl/api/exchangerates/tables/a?format=xml",
      "http://api.nbp.pl/api/exchangerates/tables/a/%s?format=xml", DateFormat.YYYY_MM_DD_DASH),

  HNB(Currency.HRK, HnbRatesTable.class, "http://api.hnb.hr/tecajn/v2?format=xml",
      "http://api.hnb.hr/tecajn/v2?format=xml&datum-primjene=%s", DateFormat.YYYY_MM_DD_DASH),

  NBU(Currency.UAH, NbuRatesTable.class, "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange",
      "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=%s", DateFormat.YYYYMMDD),

  CNB(Currency.CZK, CnbRatesTable.class, "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml",
      "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.xml?date=%s", DateFormat.DD_MM_YYYY_DOT);

  private Currency currency;
  private Class<? extends ExchangeRatesTable> tableClass;
  private String currentRatesUrl;
  private String ratesForDateUrl;
  private DateFormat dateFormat;

  BankType(Currency currency) {
    this.currency = currency;
  }

  BankType(Currency currency, Class<? extends ExchangeRatesTable> tableClass, String currentRatesUrl, String ratesForDateUrl, DateFormat dateFormat) {
    this.currency = currency;
    this.tableClass = tableClass;
    this.currentRatesUrl = currentRatesUrl;
    this.ratesForDateUrl = ratesForDateUrl;
    this.dateFormat = dateFormat;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Class<? extends ExchangeRatesTable> getTableClass() {
    return tableClass;
  }

  public String getCurrentRatesUrl() {
    return currentRatesUrl;
  }

  public String getRatesForDateUrl() {
    return ratesForDateUrl;
  }

  public DateFormat getDateFormat() {
    return dateFormat;
  }
}
