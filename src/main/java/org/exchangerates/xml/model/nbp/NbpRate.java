package org.exchangerates.xml.model.nbp;

import org.exchangerates.model.ExchangeRate;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class NbpRate implements ExchangeRate {
  @XmlElement(name="Code")
  private String currency;

  @XmlElement(name="Mid")
  private BigDecimal rate;

  public String getCurrencyCode() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate;
  }
}
