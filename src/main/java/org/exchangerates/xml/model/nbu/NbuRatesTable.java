package org.exchangerates.xml.model.nbu;

import org.exchangerates.model.ExchangeRatesTable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "exchange")
public class NbuRatesTable implements ExchangeRatesTable {
  @XmlElement(name = "currency")
  private List<NbuRate> rateList;

  public List<NbuRate> getRates() {
    return rateList;
  }

  public LocalDate getDate() {
    return rateList.stream().findAny().map(NbuRate::getDate).orElse(null);
  }
}
