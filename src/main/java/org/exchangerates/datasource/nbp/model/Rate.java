package org.exchangerates.datasource.nbp.model;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class Rate {
  @XmlElement(name="Code")
  private String currency;
  @XmlElement(name="Mid")
  private BigDecimal rate;

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate;
  }
}
