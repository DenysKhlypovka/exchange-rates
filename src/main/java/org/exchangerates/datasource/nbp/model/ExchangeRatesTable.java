package org.exchangerates.datasource.nbp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;

public class ExchangeRatesTable {
  @XmlElement(name = "EffectiveDate")
  private Date effectiveDate;
  @XmlElementWrapper(name = "Rates")
  @XmlElement(name = "Rate")
  private ArrayList<Rate> rateList;

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public ArrayList<Rate> getRateList() {
    return rateList;
  }
}
