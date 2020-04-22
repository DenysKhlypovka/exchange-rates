package org.exchangerates.xml.model.hnb;

import org.exchangerates.model.ExchangeRate;
import org.exchangerates.xml.adapter.DateAdapterDash;
import org.exchangerates.xml.adapter.NumberWithCommaAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class HnbRate implements ExchangeRate {
  @XmlElement(name="valuta")
  private String currency;
  @XmlElement(name="jedinica")
  private BigDecimal divider;
  @XmlElement(name="srednji_tecaj")
  @XmlJavaTypeAdapter(NumberWithCommaAdapter.class)
  private BigDecimal rate;
  @XmlAttribute(name = "datum_primjene")
  @XmlJavaTypeAdapter(DateAdapterDash.class)
  private Date date;

  public String getCurrencyCode() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate.divide(divider, 5, RoundingMode.HALF_UP);
  }

  public Date getDate() {
    return date;
  }
}
