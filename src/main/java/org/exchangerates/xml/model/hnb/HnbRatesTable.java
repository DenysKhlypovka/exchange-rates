package org.exchangerates.xml.model.hnb;

import org.exchangerates.model.ExchangeRatesTable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "tecajna_lista")
public class HnbRatesTable implements ExchangeRatesTable {
  @XmlElement(name = "item")
  private ArrayList<HnbRate> rateList;

  public ArrayList<HnbRate> getRates() {
    return rateList;
  }

  public LocalDate getDate() {
    return rateList.stream().findAny().map(HnbRate::getDate).orElse(null);
  }
}
