package org.exchangerates.datasource.nbp.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ArrayOfExchangeRatesTable")
public class ArrayOfExchangeRatesTable {
  @XmlElement(name = "ExchangeRatesTable")
  private ExchangeRatesTable exchangeRatesTable;

  public ExchangeRatesTable getExchangeRatesTable() {
    return exchangeRatesTable;
  }
}