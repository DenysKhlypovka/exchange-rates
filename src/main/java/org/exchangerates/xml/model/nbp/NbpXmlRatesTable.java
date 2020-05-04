package org.exchangerates.xml.model.nbp;

import org.exchangerates.model.ExchangeRatesTable;
import org.exchangerates.xml.adapter.DateAdapterDot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NbpXmlRatesTable implements ExchangeRatesTable {
  @XmlElement(name = "EffectiveDate")
  @XmlJavaTypeAdapter(DateAdapterDot.class)
  private LocalDate effectiveDate;
  @XmlElementWrapper(name = "Rates")
  @XmlElement(name = "Rate")
  private ArrayList<NbpRate> rateList;

  public LocalDate getDate() {
    return effectiveDate;
  }

  public List<NbpRate> getRates() {
    return rateList;
  }
}
