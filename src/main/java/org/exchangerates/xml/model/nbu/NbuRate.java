package org.exchangerates.xml.model.nbu;

import org.exchangerates.model.ExchangeRate;
import org.exchangerates.xml.adapter.DateAdapterDot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NbuRate implements ExchangeRate {
  @XmlElement(name = "cc")
  private String currency;
  @XmlElement(name = "rate")
  private BigDecimal rate;
  @XmlElement(name = "exchangedate")
  @XmlJavaTypeAdapter(DateAdapterDot.class)
  private LocalDate date;

  public String getCurrencyCode() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public LocalDate getDate() {
    return date;
  }
}
