package org.exchangerates.xml.model.hnb;

import org.exchangerates.model.ExchangeRate;
import org.exchangerates.xml.adapter.DateAdapterDash;
import org.exchangerates.xml.adapter.NumberWithCommaAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class HnbRate implements ExchangeRate {
  @XmlElement(name="valuta")
  private String currency;

  @XmlElement(name="jedinica")
  private BigDecimal divider;

  @XmlElement(name="srednji_tecaj")
  @XmlJavaTypeAdapter(NumberWithCommaAdapter.class)
  private BigDecimal rate;

  @XmlElement(name = "datum_primjene")
  @XmlJavaTypeAdapter(DateAdapterDash.class)
  private LocalDate date;

  public String getCurrencyCode() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate.divide(divider, 5, RoundingMode.HALF_UP);
  }

  public LocalDate getDate() {
    return date;
  }
}
