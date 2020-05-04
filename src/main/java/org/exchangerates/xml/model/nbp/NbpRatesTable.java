package org.exchangerates.xml.model.nbp;

import org.exchangerates.model.ExchangeRatesTable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "ArrayOfExchangeRatesTable")
public class NbpRatesTable implements ExchangeRatesTable {
  @XmlElement(name = "ExchangeRatesTable")
  private NbpXmlRatesTable exchangeRatesTable;

  public List<NbpRate> getRates() {
    return exchangeRatesTable.getRates();
  }

  public LocalDate getDate() {
    return exchangeRatesTable.getDate();
  }
}