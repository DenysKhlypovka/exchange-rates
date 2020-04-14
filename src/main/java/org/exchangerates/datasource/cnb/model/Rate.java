package org.exchangerates.datasource.cnb.model;

import org.exchangerates.util.Converter;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rate {
  @XmlAttribute(name="kod")
  private String currency;
  @XmlAttribute(name="mnozstvi")
  private BigDecimal divider;
  @XmlAttribute(name="kurz")
  private String rate;

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getRate() {
    return Converter.stringToBigDecimal(rate).divide(divider, 5, RoundingMode.HALF_UP);
  }
}
