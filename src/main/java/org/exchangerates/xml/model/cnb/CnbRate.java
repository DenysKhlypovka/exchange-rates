package org.exchangerates.xml.model.cnb;

import org.exchangerates.model.ExchangeRate;
import org.exchangerates.xml.adapter.NumberWithCommaAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CnbRate implements ExchangeRate {
  @XmlAttribute(name="kod")
  private String currency;
  @XmlAttribute(name="mnozstvi")
  private BigDecimal divider;
  @XmlAttribute(name="kurz")
  @XmlJavaTypeAdapter(NumberWithCommaAdapter.class)
  private BigDecimal rate;

  public String getCurrencyCode() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate.divide(divider, 5, RoundingMode.HALF_UP);
  }
}
