package org.exchangerates.xml.model.nbp;

import org.exchangerates.model.ExchangeRatesTable;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ArrayOfExchangeRatesTable")
public class NbpRatesTable implements ExchangeRatesTable {
  @XmlElement(name = "ExchangeRatesTable")
  private NbpXmlRatesTable exchangeRatesTable;

  public List<NbpRate> getRates() {
    return exchangeRatesTable.getRates();
  }

  public Date getDate() {
    return exchangeRatesTable.getDate();
  }
}